import React, { useState } from "react";
import axios from 'axios';
import './Friends.css';

export const FriendsManager = () => {
  const [userId, setUserId] = useState("");
  const [friendId, setFriendId] = useState("");
  const [friends, setFriends] = useState([]);
  const [showTable, setShowTable] = useState(false);

  const handleGetFriends = () => {
    axios.get(`http://localhost:8080/api/user/${userId}/friends`)
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

    axios.post(`http://localhost:8080/api/user/${userId}/addFriend/${friendId}`)
      .then(response => {
        setFriends(prevFriends => [...prevFriends, { userId: friendId }]);
        setFriendId("");
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  };

  return (
    <div className="friends-manager">
      <form onSubmit={handleSubmit} className="form">
        <label>
          Your User ID:
          <input type="text" value={userId} onChange={e => setUserId(e.target.value)} required />
        </label>
        <label>
          Friend's User ID:
          <input type="text" value={friendId} onChange={e => setFriendId(e.target.value)} required />
        </label>
        <input type="submit" value="Add Friend" className="submit-button" />
      </form>
      <button onClick={handleGetFriends} className="get-friends-button">Get Friends</button>
      {showTable && (
        <table className="friends-table">
          <thead>
            <tr>
              <th>User ID</th>
              <th>Name</th>
              <th>Username</th>
            </tr>
          </thead>
          <tbody>
            {friends.map(friend => (
              <tr key={friend.userId}>
                <td>{friend.userId}</td>
                <td>{friend.name}</td>
                <td>{friend.username}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default FriendsManager;