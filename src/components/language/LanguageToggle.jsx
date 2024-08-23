import React, { useContext } from "react";
import "./style/lang.css";
import { AppContext } from "./../../context/appContext";

const LanguageToggle = () => {
  const { language, setLanguage } = useContext(AppContext);
  return (
    <div className="toggleButtons">
      <button
        onClick={() => setLanguage("en")}
        style={{
          backgroundColor: language === "en" ? "#021526" : "transparent",
          color: language === "en" ? "white" : "#021526",
        }}
      >
        English
      </button>
      <button
        onClick={() => setLanguage("ar")}
        style={{
          backgroundColor: language === "ar" ? "#021526" : "transparent",
          color: language === "ar" ? "white" : "#021526",
        }}
      >
        العربية
      </button>
    </div>
  );
};

export default LanguageToggle;
