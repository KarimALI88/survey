import { useContext } from "react";
import "./App.css";
import Survey from "./pages/survey/Survey";
import { AppContext } from "./context/appContext";

function App() {
  const {language} = useContext(AppContext)
  return (
    <div className="App" style={{direction: language === "AR" ? "rtl":"ltr"}}>
      <Survey />
    </div>
  );
}

export default App;
