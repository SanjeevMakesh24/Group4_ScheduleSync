import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './CreateGroup.css';

export const CreateGroup = () => {
  const [groupName, setGroupName] = useState("");
  const [groupId, setGroupId] = useState(null);
  const [friends, setFriends] = useState([]);
  const [selectedFriends, setSelectedFriends] = useState([]);
  const [groupCreated, setGroupCreated] = useState(false);
  const navigate = useNavigate();

  const { username } = useParams();

  useEffect(() => {
    if (!username) {
      console.error("Username is not provided.");
      return;
    }

    axios.get(`http://localhost:8080/api/user/${username}/friends`)
      .then(response => {
        setFriends(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, [username]);

  const handleFriendSelect = (event) => {
    const selectedFriend = friends.find(friend => friend.username === event.target.value);
    setSelectedFriends(prev => [...prev, selectedFriend]);
    setFriends(prev => prev.filter(friend => friend.username !== event.target.value));
  };

  const handleGroupCreate = (event) => {
    event.preventDefault();
  
    const groupData = {
      name: groupName
    };
  
    axios.post(`http://localhost:8080/api/group/create`, groupData)
      .then(response => {
        console.log("GROUP CREATED: " + response.data);
        setGroupId(response.data);
        setGroupCreated(true);
  
        axios.post(`http://localhost:8080/api/group/${response.data}/addMember/${username}`)
          .then(response => {
            console.log(`Added user to the group`);
          })
          .catch(error => {
            console.error('There was an error!', error);
          });
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  
    setGroupName("");
  };

  const handleAddFriends = () => {
    if (!groupId) {
      console.error("No group ID available for adding friends.");
      return;
    }

    selectedFriends.forEach(friend => {
      axios.post(`http://localhost:8080/api/group/${groupId}/addMember/${friend.username}`)
        .then(response => {
          console.log(`Added ${friend.name} to the group`);
          navigate(`/userGroups/${username}`);
        })
        .catch(error => {
          console.error('There was an error in adding friends', error);
        });
    });

    setSelectedFriends([]);
  };

  return (
    <div className="create-group-container">
      <form onSubmit={handleGroupCreate} className="group-form">
        <label className="group-label">
          Group Name:
          <input type="text" value={groupName} onChange={e => setGroupName(e.target.value)} required className="group-input" />
        </label>
        <input type="submit" value="Create Group" className="create-group-btn" />
      </form>
      {groupCreated && (
        <>
          <label className="friend-select-label">
            Select Friends:
            <select onChange={handleFriendSelect} className="friend-select">
              <option value="">Select a friend</option>
              {friends.map(friend => (
                <option key={friend.username} value={friend.username}>{friend.name}</option>
              ))}
            </select>
          </label>
          <button onClick={handleAddFriends} className="add-friends-btn">Add Friends</button>
        </>
      )}
      <div className="selected-friends">
        Selected Friends:
        <ul className="friends-list">
          {selectedFriends.map(friend => (
            <li key={friend.username}>{friend.name}</li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default CreateGroup;