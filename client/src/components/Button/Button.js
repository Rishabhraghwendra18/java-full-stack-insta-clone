import React from 'react'
import styles from "./button.module.css"

function Button({className,children}) {
  return (
    <button className={`${className} ${styles.button}`}>{children}</button>
  )
}

export default Button