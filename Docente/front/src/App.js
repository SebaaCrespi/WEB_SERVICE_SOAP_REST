import './App.css';
import { Router, Routes, Route, Link } from "react-router-dom";
import Home from './components/main.jsx';
import Course from './components/course.jsx';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/course" element={<Course />} />
      </Routes>
    </div>
  );
}

export default App;
