import React, { useContext, useState, useEffect } from "react";
import "./style/survey.css";
import Question from "../../components/question/Question";
import Input from "../../components/input/Input";
import LanguageToggle from "../../components/language/LanguageToggle";
import { AppContext } from "../../context/appContext";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Survey = () => {
  const { language } = useContext(AppContext);
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});
  const navigate = useNavigate()

  useEffect(() => {
    getQuestions(); // Fetch questions when component mounts
  }, [language]);

  // Fetch questions from API
  const getQuestions = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/surveys/1?language=${language}`);
      console.log(response);
      setQuestions(response.data.questions); // Assuming the response contains a 'questions' field
    } catch (error) {
      console.error("Error fetching questions:", error);
    }
  };

  // Handle input change for text or textarea
  const handleInputChange = (questionId, value) => {
    setAnswers((prevAnswers) => ({
      ...prevAnswers,
      [questionId]: value,
    }));
  };

  // Handle radio change
  const handleRadioChange = (questionId, answerId) => {
    setAnswers((prevAnswers) => ({
      ...prevAnswers,
      [questionId]: answerId,
    }));
  };

  // Send answers to API
  const sendAnswer = async () => {
    try {
      const formattedAnswers = Object.entries(answers).map(([questionId, answer]) => ({
        questionId: parseInt(questionId, 10),
        answerText: Array.isArray(answer) ? answer.join(', ') : answer,
      }));

      const payload = {
        language,
        channel: "Mobile",
        answers: formattedAnswers,
      };

      const response = await axios.post("http://localhost:8080/api/surveys/answer", payload);
      if (response) {
        navigate("/end")
      }
      console.log("Response:", response);
    } catch (error) {
      console.error("Error sending answers:", error);
    }
  };

  return (
    <div className="surveyContainer">
      <div className="image">
        <img
          src="https://img.freepik.com/free-photo/hand-holding-writing-checklist-application-form-document-clipboard-white-background-3d-illustration_56104-1551.jpg?t=st=1724366252~exp=1724369852~hmac=93a163d76c13b07c885efb41e8714a7c7bd9287cae86704375a197b09d0a22c5&w=740"
          alt="logo"
        />
      </div>

      <div className="languages">
        <LanguageToggle />
      </div>

      {questions.map((ques) => (
        <div className="questionAndAnswer" key={ques.questionId}>
          <div className="questionContainer">
            <Question question={ques.questionText} />
          </div>
          <div className="inputContainer">
            {ques.questionType === "3" && (
              <Input
                type="text"
                value={answers[ques.questionId] || ""}
                onChange={(e) => handleInputChange(ques.questionId, e.target.value)}
                placeholder="Enter your answer"
              />
            )}
            {ques.questionType === "2" && (
              <textarea
                value={answers[ques.questionId] || ""}
                onChange={(e) => handleInputChange(ques.questionId, e.target.value)}
                placeholder="Enter your answer"
              />
            )}
            {ques.questionType === "1" &&
              ques.acceptableAnswers.map((radio) => (
                <div className="radio" key={radio.id}>
                  <input
                    type="radio"
                    name={`question_${ques.questionId}`} // Ensure only one selection per question
                    value={radio.id}
                    checked={answers[ques.questionId] === radio.id}
                    onChange={() => handleRadioChange(ques.questionId, radio.id)}
                  />
                  <label>{radio.text}</label>
                </div>
              ))}
          </div>
        </div>
      ))}

      <div className="submit">
        <button onClick={sendAnswer}>
          {language === "AR" ? "ارسال" : "Submit"}
        </button>
      </div>
    </div>
  );
};

export default Survey;
