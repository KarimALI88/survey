import React, { useContext } from 'react'
import './style/end.css'
import { AppContext } from '../../context/appContext'

const End = () => {
  const { language } = useContext(AppContext);
  return (
    <div className='end-container'>
      <h1>{language === "AR" ? "شكرا لملئ الاستمارة" : "Thanks for your submission"}</h1>
    </div>
  )
}

export default End
