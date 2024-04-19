import React, { useState } from "react";
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './SignIn.css';

export const SignIn = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();

    const signInData = {
      username,
      password
    };

    axios.post(`http://localhost:8080/api/user/signIn`, signInData)
      .then(response => {
        if (response.data.success) {
          navigate('/userDashboard', { state: { username } });
        } else {
          window.alert('Invalid username or password');
        }
      })
      .catch(error => {
        console.error('There was an error!', error);
      });

    setUsername("");
    setPassword("");
  };

  return (
    <div className="sign-in">
      <div className="form-container1">
        <h1 className="title">ScheduleSync</h1>
        <h3 className="subtitle">Sign In</h3>
        <form onSubmit={handleSubmit} className="account-form">
          <label className="centered-input">
            Username:
            <input type="text" value={username} onChange={e => setUsername(e.target.value)} required />
          </label>
          <label className="centered-input">
            Password:
            <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
          </label>
          <input type="submit" value="Sign In" className="submit-button" />
        </form>
        <div className="createAccountLink">
          Don't have an account? <Link to="/create-account">Create Account</Link>
        </div>
      </div>
    </div>
  );
};

export default SignIn;