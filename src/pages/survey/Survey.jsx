import React, { useContext, useState } from "react";
import "./style/survey.css";
import Question from "../../components/question/Question";
import Input from "../../components/input/Input";
import LanguageToggle from "../../components/language/LanguageToggle";
import { AppContext } from "../../context/appContext";
import axios from "axios";

const Survey = () => {
  const [inpValue, setInpValue] = useState("");
  const {language} = useContext(AppContext)
  const [questions, setQuestions] = useState([])

  // get questions 
  const getQuestions = async () => {
    console.log("get questions");
    try {
      const response = await axios.get("localhost:8080/api/surveys/answer")
      console.log("response: ", response);
      
    } catch (error) {
      console.error("error", error);
      
    }
  }

  // send answer 
  const sendAnswer = async() => {
    console.log("send answer");
    try {
      const response = await axios.post("localhost:8080/api/surveys/answer")
      console.log("response", response);
      
    } catch (error) {
      console.error("error:", error)
    }
    
  }
  return (
    <div className="surveyContainer">
      {/* image */}
      <div className="image">
        <img src="https://img.freepik.com/free-photo/hand-holding-writing-checklist-application-form-document-clipboard-white-background-3d-illustration_56104-1551.jpg?t=st=1724366252~exp=1724369852~hmac=93a163d76c13b07c885efb41e8714a7c7bd9287cae86704375a197b09d0a22c5&w=740" alt="logo" />
      </div>
      {/* ********************** */}
      {/* language buttons */}
      <div className="languages">
        <LanguageToggle />
      </div>
      {/* ***************** */}
      {/* question and answer block */}
      <div className="questionAndAnswer">
        <div className="questionContainer">
          <Question question={"what's your name"} />
        </div>
        <div className="inputContainer">
          <Input
            type={"text"}
            value={inpValue}
            onchange={(e) => setInpValue(e.targetvalue)}
            placeholder={"enter your answer"}
          />
        </div>
      </div>
      {/* ************************* */}
      {/* submit button */}
      <div className="submit">
        <button onClick={sendAnswer}>{language === "ar" ? "ارسال" : "submit"}</button>
      </div>
      {/* ************** */}
    </div>
  );
};

export default Survey;
