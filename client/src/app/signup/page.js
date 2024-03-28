'use client'
import {useState} from "react";
import Image from "next/image";
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
    } catch (error) {
      console.log("Error while sign up: ",error);
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
    </div>
  );
}

export default SignUp;
