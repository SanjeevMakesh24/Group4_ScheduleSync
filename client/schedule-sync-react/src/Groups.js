import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './Groups.css';

function UserGroups() {
  const { username } = useParams();  
  const [groups, setGroups] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/user/${username}/groups`)
      .then(response => setGroups(response.data))
      .catch(error => console.error(error));
  }, []);

  const handleGroupClick = (group) => {
    navigate(`/groupCalendar/${group}`);
  };

  const handleCreateGroupClick = () => {
    navigate(`/createGroup/${username}`);
  };

  const handleBackClick = () => {
    navigate('/userDashboard', { state: { username } });
  };

  return (
    <div className="user-groups-container">
      <button onClick={handleBackClick} className="back-button">Go Back</button>
      <h2 className="user-groups-title">Your Groups:</h2>
      <div className="group-buttons">
        {groups.map(group => (
          <button key={group} onClick={() => handleGroupClick(group)} className="group-button">
            {group}
          </button>
        ))}
      </div>
      <button onClick={handleCreateGroupClick} className="create-group-button">Create New Group</button>
    </div>
  );
}

export default UserGroups;
