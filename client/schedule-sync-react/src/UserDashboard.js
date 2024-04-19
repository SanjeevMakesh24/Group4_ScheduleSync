import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './UserDashboard.css';
import axios from 'axios';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import { findDayAndMonth } from './utils';
import moment from 'moment';
import './Calendar.css';
// import MyCalendar from "./UserSchedule";

export const UserDashboard = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const username = location.state.username;
  const [events, setEvents] = useState([]);

  const handleFriendsClick = () => {
    navigate(`/friends/${username}`);
  };

  const handleScheduleClick = () => {
    navigate(`/userSchedule/${username}`);
  };

  const handleSignOutClick = () => {
    navigate('/');
  };

  const handleUserGroupClick = () => {
    navigate(`/userGroups/${username}`);
  };


  useEffect(() => {
    axios.get(`http://localhost:8080/api/user/${username}/schedule`)
      .then(response => {
        const timeBlocks = response.data.timeBlocks;
        const events = timeBlocks.map(block => {
          const { dayNumber, monthName } = findDayAndMonth(block.blockDay);
          const start = moment(`${monthName} ${dayNumber}, ${new Date().getFullYear()} ${block.startTime}`, 'MMMM D, YYYY HH:mm');
          const end = moment(`${monthName} ${dayNumber}, ${new Date().getFullYear()} ${block.endTime}`, 'MMMM D, YYYY HH:mm');
        
          return {
            title: block.blockName,
            start: start.toISOString(),
            end: end.toISOString()
          };
        });
        setEvents(events);
      })
      .catch(error => {
        console.error('Cannot get the timeblocks in the user schedule', error);
      });
  }, [username]);

  return (
    <div className="user-dashboard">
      <div className="sidebar">
        <h2 className='title-button'>Schedule Sync</h2>
        <button onClick={handleFriendsClick} className="sidebar-button friends-button">Friends List</button>
        <button onClick={handleScheduleClick} className="sidebar-button schedule-button">User Schedule</button>
        <button onClick={handleUserGroupClick} className="sidebar-button friends-button">User Groups</button>
        <button onClick={handleSignOutClick} className="sidebar-button sign-out-button">Sign Out</button>
      </div>
      <h1>Welcome, {username}!</h1>
      <div className="calendar-container">
      <FullCalendar
        plugins={[dayGridPlugin, timeGridPlugin]}
        initialView="timeGridWeek"
        events={events}
        editable={true}
        eventStartEditable={true}
        eventDurationEditable={true}
        selectable={true}
        slotMinTime="07:00:00"
        slotMaxTime="23:00:00"
        height="auto" 
        className="my-calendar"
      />
    </div>
    </div>
  );
};

export default UserDashboard;