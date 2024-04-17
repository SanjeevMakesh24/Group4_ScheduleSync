import React, { useState } from "react";
import axios from 'axios';
import './CreateAccount.css'; //Import the CSS file

export const CreateAccount = () => {
  const [userID, setUserID] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [name, setName] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    const user = {
      userID,
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
        console.log(`User created with ID: ${response.data}`);
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
            <input  type="text" value={name} onChange={e => setName(e.target.value)} required />
          </label>
          <label className="centered-input">
            Username:
            <input  type="text" value={username} onChange={e => setUsername(e.target.value)} required />
          </label>
          <label className="centered-input">
            Email:
            <input  type="email" value={email} onChange={e => setEmail(e.target.value)} required />
          </label>
          <label className="centered-input">
            Password:
            <input  type="password" value={password} onChange={e => setPassword(e.target.value)} required />
          </label>
          <input type="submit" value="Create Account" className="submit-button" /> 
          <a href="Login">Already Have an Account</a>
        </form>
      </div>
    </div>
  );
};

export default CreateAccount;