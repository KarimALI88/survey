import React, { useContext } from "react";
import "./style/lang.css";
import { AppContext } from "./../../context/appContext";

const LanguageToggle = () => {
  const { language, setLanguage } = useContext(AppContext);
  return (
    <div className="toggleButtons">
      <button
        onClick={() => setLanguage("EN")}
        style={{
          backgroundColor: language === "EN" ? "#021526" : "transparent",
          color: language === "EN" ? "white" : "#021526",
        }}
      >
        English
      </button>
      <button
        onClick={() => setLanguage("AR")}
        style={{
          backgroundColor: language === "AR" ? "#021526" : "transparent",
          color: language === "AR" ? "white" : "#021526",
        }}
      >
        العربية
      </button>
    </div>
  );
};

export default LanguageToggle;
