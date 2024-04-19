import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import { findDayAndMonth } from './utils';
import moment from 'moment';
import './GroupCalendar.css'; 
import AddEventForm from './EventForm';
import AddEventModal from './AddEventModal';

const GroupCalendar = () => {
  const { groupId } = useParams();  
  const [events, setEvents] = useState([]);
  const [members, setMembers] = useState([]);
  // const [showForm, setShowForm] = useState(false);
  const [isModalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/group/${groupId}/schedule`)
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
        console.error('There was an error!', error);
      });

    axios.get(`http://localhost:8080/api/group/${groupId}/members`)
      .then(response => {
        setMembers(response.data);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, [groupId]);


  return (
    <div className="page">
      {/* <h2 className="group-name"> {groupId}</h2> */}
    <div className="calendar-container">
      <button className="add-event-button" onClick={() => setModalOpen(true)}>Add Event</button>
      <AddEventModal isOpen={isModalOpen} closeModal={() => setModalOpen(false)}>
        <AddEventForm groupId={groupId} setEvents={setEvents} />
      </AddEventModal>
      <div className="calendar">
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
      <div className="members">
        <h2>Group Members</h2>
        {members.map(member => (
          <p key={member}>{member}</p>
        ))}
      </div>
    </div>
    </div>
  );
};

export default GroupCalendar;