import React from "react";
import Image from "next/image";
import Link from "next/link";
import InstagramLogo from "../../assets/instagram-logo.png";
import InputBox from "@/components/InputBox/InputBox";
import Button from "@/components/Button/Button";
import styles from "./page.module.css";

function SignUp() {
  return (
    <div className={styles.login_container}>
      <Image
        src={InstagramLogo}
        alt="Instagram logo"
        width={300}
        height={"auto"}
      />
      <div className={styles.inputs_container}>
        <InputBox type="text" placeholder="Enter Username" />
        <InputBox type="email" placeholder="Enter Email" />
        <InputBox type="password" placeholder="Enter Password" />
        <Button className={styles.button}>Sign Up</Button>
      </div>
      <hr></hr>
      <p style={{textAlign:'center'}}>
        Have an account? <Link href="/login">Log In here!</Link>
      </p>
    </div>
  );
}

export default SignUp;
