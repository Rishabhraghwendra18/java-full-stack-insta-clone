import React from 'react'
import styles from "./inputBox.module.css";

function InputBox({className,type,placeholder, onChange=(e)=>{}}) {
  return (
    <input className={`${className} ${styles.input}`} type={type} placeholder={placeholder} onChange={onChange}/>
  )
}

export default InputBox