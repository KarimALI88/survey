import React from 'react'
import './style/input.css'

const Input = ({type, value, placeholder, onchange}) => {
  return (
    <div className='input'>
      <input type={type} value={value} placeholder={placeholder} onChange={onchange} />
    </div>
  )
}

export default Input
