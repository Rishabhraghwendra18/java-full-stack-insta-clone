import React from 'react'
import styles from "./inputBox.module.css";

function InputBox({className,type,placeholder}) {
  return (
    <input className={`${className} ${styles.input}`} type={type} placeholder={placeholder}/>
  )
}

export default InputBox