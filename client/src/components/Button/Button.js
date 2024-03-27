import React from 'react'
import styles from "./button.module.css"

function Button({className,children,onClick=()=>{},type}) {
  return (
    <button type={type} className={`${className} ${styles.button}`} onClick={onClick}>{children}</button>
  )
}

export default Button