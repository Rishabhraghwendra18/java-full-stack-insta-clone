'use client'
import { useState,useEffect } from "react";
import Image from "next/image";
import styles from "./page.module.css";
import InstagramTextLogo from "../assets/instagram-text.png";
import Button from "@/components/Button/Button";
import DisplayCard from "@/components/DisplayCard/DisplayCard";
import Modal from "@/components/Modal/Modal";
import { getFeed } from "@/service/homeService";

export default function Home() {
  const [open, setOpen] = useState(false);
  const handleOpen=()=>setOpen(true);
  const handleClose=()=>setOpen(false);
  useEffect(()=>{
    getFeed().then(response=>console.log("feed: ",response.data)).catch(error=>console.log("Error while fetching feed: ",error));
  },[])
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
