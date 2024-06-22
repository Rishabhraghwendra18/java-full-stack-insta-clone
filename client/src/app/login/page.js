'use client'
import {useState} from 'react'
import {Snackbar} from "@mui/material"
import Image from "next/image";
import Link from "next/link";
import InstagramLogo from "../../assets/instagram-logo.png";
import InputBox from '@/components/InputBox/InputBox';
import Button from '@/components/Button/Button';
import { signIn } from '@/service/userService';
import { setCookie } from 'cookies-next';
import styles from "./page.module.css"


function Login() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [openSnackBar, setOpenSnackBar] = useState(false);
  const [snackBarMessage, setSnackBarMessage] = useState();

  const handleLogin = async () =>{
    try {
      const userData ={
        email,
        password
      }
      const res = await signIn(userData);
      console.log("res data: ",res.data);
      const jwtToken = res.data?.token;
      const expirationDate = res.data?.expirationDate;
      console.log("token: ",jwtToken);
      setCookie('Authorization', `Bearer ${jwtToken}`,{
        // httpOnly:true,
        expires:new Date(expirationDate)
      });
      setOpenSnackBar(true);
      setSnackBarMessage("Account logged in");
      // cookies.set('Authorization',`Bearer ${jwtToken}`,{path:'/',httpOnly:true,sameSite:'strict'});
    } catch (error) {
      console.log("Error while login in: ",error)
      setOpenSnackBar(true);
      setSnackBarMessage("Error while logging in");
    }
  }
  return (
    <div className={styles.login_container}>
        <Image src={InstagramLogo} alt='Instagram logo' width={300} height={'auto'}/>
        <div className={styles.inputs_container}>
            <InputBox type='email' placeholder='Enter Email' onChange={(e)=>setEmail(e.target.value)}/>
            <InputBox type='password' placeholder='Enter Password' onChange={(e)=>setPassword(e.target.value)}/>
            <Button className={styles.button} onClick={handleLogin}>Log in</Button>
        </div>
        <hr></hr>
        <p>Don't have an account? <Link href='/signup'>Sign Up here!</Link></p>
        <Snackbar
        anchorOrigin={{ vertical:'top', horizontal:'right' }}
        open={openSnackBar}
        onClose={()=>{
          setOpenSnackBar(false)
          setSnackBarMessage()
        }}
        message={snackBarMessage}
        key={'top' + 'right'}
      />
    </div>
  )
}

export default Login;