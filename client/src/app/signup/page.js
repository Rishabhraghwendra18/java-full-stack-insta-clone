'use client'
import {useState} from "react";
import Image from "next/image";
import {Snackbar} from "@mui/material"
import Link from "next/link";
import InstagramLogo from "../../assets/instagram-logo.png";
import InputBox from "@/components/InputBox/InputBox";
import Button from "@/components/Button/Button";
import { signUp } from "@/service/userService";
import styles from "./page.module.css";

function SignUp() {
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [openSnackBar, setOpenSnackBar] = useState(false);
  const [snackBarMessage, setSnackBarMessage] = useState();

  const handleSignUp = async ()=>{
    try {
      let userData={
        username,
        email,
        password,
        profilePhoto:null
      }
      const res = await signUp(userData);
      console.log("response on signUp: ",res.data);
      setOpenSnackBar(true);
      setSnackBarMessage(res.data.message);
    } catch (error) {
      console.log("Error while sign up: ",error);
      setOpenSnackBar(true);
      setSnackBarMessage("Error while creating account");
    }
  }
  return (
    <div className={styles.login_container}>
      <Image
        src={InstagramLogo}
        alt="Instagram logo"
        width={300}
        height={"auto"}
      />
      <div className={styles.inputs_container}>
        <InputBox type="text" placeholder="Enter Username" onChange={(e)=>setUsername(e.target.value)} />
        <InputBox type="email" placeholder="Enter Email" onChange={(e)=>setEmail(e.target.value)}/>
        <InputBox type="password" placeholder="Enter Password" onChange={(e)=>setPassword(e.target.value)}/>
        <Button className={styles.button} onClick={handleSignUp}>Sign Up</Button>
      </div>
      <hr></hr>
      <p style={{textAlign:'center'}}>
        Have an account? <Link href="/login">Log In here!</Link>
      </p>
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
  );
}

export default SignUp;
