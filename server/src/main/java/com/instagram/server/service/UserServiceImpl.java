package com.instagram.server.service;

import com.instagram.server.collection.Post;
import com.instagram.server.collection.User;
import com.instagram.server.repository.PostRepo;
import io.jsonwebtoken.Claims;
import com.instagram.server.exceptions.AlreadyExistsException;
import com.instagram.server.exceptions.MissingFieldException;
import com.instagram.server.repository.UserRepo;
import com.instagram.server.requestResponse.CommonResponse;
import com.instagram.server.requestResponse.JwtRequest;
import com.instagram.server.requestResponse.JwtResponse;
import com.instagram.server.requestResponse.SignUpRequest;
import com.instagram.server.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private JwtUtil jwtUtil;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PostRepo postRepo;
    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(UserRepo userRepo,JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder, PostRepo postRepo) {

        this.userRepo = userRepo;
        this.jwtUtil=jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.postRepo = postRepo;
    }

    @Override
    public ResponseEntity<CommonResponse> signUp(SignUpRequest user) {
        if(user.getUsername()==null){
            throw new MissingFieldException("Username is missing");
        }
        if(user.getEmail()==null){
            throw new MissingFieldException("Email is missing");
        }
        if(user.getPassword() == null){
            throw new MissingFieldException("Password is missing");
        }
        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser !=null){
            throw new AlreadyExistsException("User already exists");
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        User newUser = new User(user.getUsername(),user.getEmail(),user.getPassword(),user.getProfilePhoto(),null);
        generateRefreshTokenAndSaveToDatabase(newUser);
        return new ResponseEntity<>(new CommonResponse("Account Created Successfully",false,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JwtResponse> signIn(JwtRequest jwtRequest) {
        if(jwtRequest.getEmail() == null){
            throw new MissingFieldException("Email is missing");
        }
        if(jwtRequest.getPassword() == null){
            throw new MissingFieldException("Password is missing");
        }
        User loggedInUser = loadUser(jwtRequest.getEmail());
        if(loggedInUser != null){
            if(bCryptPasswordEncoder.matches(jwtRequest.getPassword(),loggedInUser.getPassword())){
                String token = jwtUtil.generateToken(loggedInUser);
                generateRefreshTokenAndSaveToDatabase(loggedInUser);
                JwtResponse response = new JwtResponse(token,loggedInUser.getUsername(),null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {
                JwtResponse response = new JwtResponse(null,null,"Password don't match");
                return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
            }
        }
        JwtResponse response = new JwtResponse(null,null,"User not found");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    private void generateRefreshTokenAndSaveToDatabase(User user){
        String token =jwtUtil.generateRefreshToken(user);
        user.setRefreshToken(token);
        userRepo.save(user);
    }

    private User loadUser(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public ResponseEntity<CommonResponse> post(Post post, String token) {
        String jwtToken = jwtUtil.getTokenFromRequest(token);
        if(jwtToken == null){
            return new ResponseEntity<>(new CommonResponse("Failed to upload post",true,HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
        System.out.println("Id is: "+claims.get("id"));

        String userId = claims.get("id").toString();
        post.setUserId(userId);
        postRepo.save(post);
        return new ResponseEntity<>(new CommonResponse("Post Uploaded Successfully",false,HttpStatus.OK.value()),HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(email.isEmpty()){
            throw new UsernameNotFoundException("Provide email");
        }
        User loggedUser = loadUser(email);
        if (loggedUser == null){
            throw new UsernameNotFoundException("User not found - 404");
        }
        return new UserDetailsWrapperImpl(loggedUser);
    }
}
