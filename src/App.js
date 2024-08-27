import { useContext } from "react";
import "./App.css";
import Survey from "./pages/survey/Survey";
import { AppContext } from "./context/appContext";
import { Route, Routes } from "react-router-dom";
import End from './pages/end/End';

function App() {
  const {language} = useContext(AppContext)
  return (
    <div className="App" style={{direction: language === "AR" ? "rtl":"ltr"}}>
      <Routes>
        <Route path="/" element={<Survey/>} />
        <Route path="/end" element={<End/>} />
      </Routes>
    </div>
  );
}

export default App;
