import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignupPage from './SignupPage';
import StatisticsPage from './StatisticsPage';
import './style.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/statistics" element={<StatisticsPage />} />
        <Route path="/" element={<SignupPage />} exact />
      </Routes>
    </Router>
  );
}

export default App;
