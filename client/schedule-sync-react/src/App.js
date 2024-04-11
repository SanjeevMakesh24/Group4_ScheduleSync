// App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Import Routes
import CreateAccount from "./CreateAccount"; // Your sign-up page component
import Login from "./log-in"; // Your login page component
import "./App.css";

function App() {
  return (
    <Router>
      <Routes> {/* Use Routes instead of Switch */}
        <Route path="/" element={<CreateAccount />} /> {/* Use element prop */}
        <Route path="/log-in" element={<Login />} /> {/* Use element prop */}
      </Routes>
    </Router>
  );
}

export default App;
