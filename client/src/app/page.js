'use client'
import { useState } from "react";
import Image from "next/image";
import styles from "./page.module.css";
import InstagramTextLogo from "../assets/instagram-text.png";
import Button from "@/components/Button/Button";
import DisplayCard from "@/components/DisplayCard/DisplayCard";
import Modal from "@/components/Modal/Modal";



export default function Home() {
  const [open, setOpen] = useState(false);
  const handleOpen=()=>setOpen(true);
  const handleClose=()=>setOpen(false);
  return (
    <>
      <div className={styles.navbar}>
        <Image src={InstagramTextLogo} width={200} height={'auto'} alt="Instagram logo"/>
      </div>
      <div className={styles.main}>
      <DisplayCard/>
      </div>
      <div className={styles.footer}>
        <Button className={styles.button} onClick={handleOpen}>Create a Post</Button>
      </div>
      <Modal open={open} handleClose={handleClose} handleOpen={handleOpen}></Modal>
    </>
  );
}
