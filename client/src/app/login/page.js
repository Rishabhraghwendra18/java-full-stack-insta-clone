import React from 'react'
import Image from "next/image";
import Link from "next/link";
import InstagramLogo from "../../assets/instagram-logo.png";
import InputBox from '@/components/InputBox/InputBox';
import Button from '@/components/Button/Button';
import styles from "./page.module.css"

function Login() {
  return (
    <div className={styles.login_container}>
        <Image src={InstagramLogo} alt='Instagram logo' width={300} height={'auto'}/>
        <div className={styles.inputs_container}>
            <InputBox type='email' placeholder='Enter Email'/>
            <InputBox type='password' placeholder='Enter Password'/>
            <Button className={styles.button}>Log in</Button>
        </div>
        <hr></hr>
        <p>Don't have an account? <Link href='/signup'>Sign Up here!</Link></p>
    </div>
  )
}

export default Login;