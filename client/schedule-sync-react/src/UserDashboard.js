import React from "react";
import { useLocation, useNavigate } from 'react-router-dom';
import './UserDashboard.css';

export const UserDashboard = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const username = location.state.username;

  const handleFriendsClick = () => {
    navigate(`/friends/${username}`);
  };

  const handleScheduleClick = () => {
    navigate(`/userSchedule/${username}`);
  };

  const handleSignOutClick = () => {
    navigate('/signIn');
  };

  const handleUserGroupClick = () => {
    navigate(`/userGroups/${username}`);
  };

  return (
    <div className="user-dashboard">
      <div className="sidebar">
        <button onClick={handleFriendsClick} className="sidebar-button friends-button">Friends List</button>
        <button onClick={handleScheduleClick} className="sidebar-button schedule-button">User Schedule</button>
        <button onClick={handleSignOutClick} className="sidebar-button sign-out-button">Sign Out</button>
        <button onClick={handleUserGroupClick} className="sidebar-button friends-button">User Groups</button>
      </div>
      <h1>Welcome, {username}!</h1>
      <div className="main-content">
        {/* Main content goes here */}
      </div>
    </div>
  );
};

export default UserDashboard;