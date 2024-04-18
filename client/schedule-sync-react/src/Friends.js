import React, { useState } from "react";
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './Friends.css';

export const FriendsManager = () => {
  const { username } = useParams();  
  const [friendUsername, setFriendUsername] = useState("");
  const [friends, setFriends] = useState([]);
  const [showTable, setShowTable] = useState(false);

  const handleGetFriends = () => {
    axios.get(`http://localhost:8080/api/user/${username}/friends`)
      .then(response => {
        setFriends(response.data);
        setShowTable(true);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    axios.post(`http://localhost:8080/api/user/${username}/addFriend/${friendUsername}`)
      .then(response => {
        setFriends(prevFriends => [...prevFriends, { username: friendUsername }]);
        setFriendUsername("");
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  };

  return (
    <div className="friends-manager">
      <form onSubmit={handleSubmit} className="form">
        <label>
          Friend's Username:
          <input type="text" value={friendUsername} onChange={e => setFriendUsername(e.target.value)} required />
        </label>
        <input type="submit" value="Add Friend" className="submit-button" />
      </form>
      <button onClick={handleGetFriends} className="get-friends-button">Get Friends</button>
      {showTable && (
        <table className="friends-table">
          <thead>
            <tr>
              <th>Username</th>
              <th>Name</th>
            </tr>
          </thead>
          <tbody>
            {friends.map(friend => (
              <tr key={friend.username}>
                <td>{friend.username}</td>
                <td>{friend.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default FriendsManager;