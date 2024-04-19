import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import { findDayAndMonth } from './utils';
import moment from 'moment';
import AddEventModal from './AddEventModal';
import TimeBlockForm from './TimeBlock';
import './Calendar.css'; // Import the CSS file

const MyCalendar = () => {
  const { username } = useParams();  
  const [events, setEvents] = useState([]);
  const navigate = useNavigate();
  const [isModalOpen, setModalOpen] = useState(false);

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

  const handleAddTimeBlockClick = () => {
    navigate(`/timeblock/${username}`);
  };

  return (
    <div className="calendar-container">
      <button onClick={() => setModalOpen(true)} className='addTimeBlock'>Add Time Block</button>
      <AddEventModal isOpen={isModalOpen} closeModal={() => setModalOpen(false)} title="Add Time Block">
        <TimeBlockForm username={username} setEvents={setEvents} />
      </AddEventModal>
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
  );
};

export default MyCalendar;