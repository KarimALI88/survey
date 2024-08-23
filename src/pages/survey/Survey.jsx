import React, { useContext, useState, useEffect } from "react";
import "./style/survey.css";
import Question from "../../components/question/Question";
import Input from "../../components/input/Input";
import LanguageToggle from "../../components/language/LanguageToggle";
import { AppContext } from "../../context/appContext";
import axios from "axios";

const Survey = () => {
  const { language } = useContext(AppContext);
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    getQuestions(); // Fetch questions when component mounts
  }, []);

  // Fetch questions from API
  const getQuestions = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/surveys/questions");
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

  // Handle checkbox change
  const handleCheckboxChange = (questionId, answerId, isChecked) => {
    setAnswers((prevAnswers) => {
      const currentAnswers = prevAnswers[questionId] || [];
      const updatedAnswers = isChecked
        ? [...currentAnswers, answerId]
        : currentAnswers.filter((id) => id !== answerId);

      return {
        ...prevAnswers,
        [questionId]: updatedAnswers,
      };
    });
  };

  // Send answers to API
  const sendAnswer = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/surveys/answer", {
        language,
        answers,
      });
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
            {ques.questionType === "t" && (
              <Input
                type="text"
                value={answers[ques.questionId] || ""}
                onchange={(e) => handleInputChange(ques.questionId, e.target.value)}
                placeholder="Enter your answer"
              />
            )}
            {ques.questionType === "s" && (
              <textarea
                value={answers[ques.questionId] || ""}
                onChange={(e) => handleInputChange(ques.questionId, e.target.value)}
                placeholder="Enter your answer"
              />
            )}
            {ques.questionType === "Y" &&
              ques.acceptableAnswers.map((check) => (
                <div className="check" key={check.id}>
                  <input
                    type="checkbox"
                    value={check.id}
                    onChange={(e) =>
                      handleCheckboxChange(ques.questionId, check.id, e.target.checked)
                    }
                  />
                  <label>{check.text}</label>
                </div>
              ))}
          </div>
        </div>
      ))}

      <div className="submit">
        <button onClick={sendAnswer}>
          {language === "ar" ? "ارسال" : "Submit"}
        </button>
      </div>
    </div>
  );
};

export default Survey;
