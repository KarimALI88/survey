import React from 'react'
import './style/question.css'

const Question = ({question}) => {
  return (
    <div className='question'>
      <h4>{question}</h4>
    </div>
  )
}

export default Question
