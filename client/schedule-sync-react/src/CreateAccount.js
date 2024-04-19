import React, { useState } from "react";
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './CreateAccount.css'; //Import the CSS file
import 'animate.css';

export const CreateAccount = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [name, setName] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();

    const user = {
      name,
      username,
      email,
      password,
      friends: [],
      schedule: {},
      groups: []
    };

    axios.post('http://localhost:8080/api/user/addUser', user)
      .then(response => {
        console.log(`User created with username: ${response.data}`);
        console.log(response);
        console.log(response.data);
        navigate('/signIn');
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  };

  return (
    <div className="create-account">
      <div className="form-container">
        <h1 className="title">ScheduleSync</h1>
        <h3 className="subtitle">Create Account</h3>
        <form onSubmit={handleSubmit} className="account-form">
          <label className="centered-input">
            Name:  
            <input  type="text" placeholder="Name"  value={name} onChange={e => setName(e.target.value)} required />
          </label>
          <label className="centered-input">
            Username:
            <input  type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} required />
          </label>
          <label className="centered-input">
            Email:
            <input  type="email" placeholder="Email" required value={email} onChange={e => setEmail(e.target.value)} required />
          </label>
          <label className="centered-input">
            Password:
            <input  type="password" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)} required />
          </label>
          <input type="submit" value="Create Account" className="submit-button1" />
          <a href="Login">Already Have an Account</a>
        </form>
        <div className="signInlink">
        Already have an account? <Link to="/signIn">Sign In</Link>
      </div>
      </div>
    </div>
  );
};

export default CreateAccount;