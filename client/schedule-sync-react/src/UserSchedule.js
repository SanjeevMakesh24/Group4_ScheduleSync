import React, { useState, useEffect, ReactDOM } from 'react';
import axios from 'axios';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import moment from 'moment';

const CustomEvent = ({ event }) => {
  return (
    <div>
      <b>{event.title}</b>
      <br />
      <small>{moment(event.start).format('HH:mm')} - {moment(event.end).format('HH:mm')}</small>
    </div>
  );
};

const UserScheduleCalendar = ({ userId }) => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/user/${userId}/schedule`)
      .then(response => {
        const timeBlocks = response.data;
        const events = timeBlocks.map(block => {
          const dayOfWeek = ["SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"].indexOf(block.blockDay);
          const start = moment().day(dayOfWeek).hour(moment(block.startTime, 'HH:mm').hour()).minute(moment(block.startTime, 'HH:mm').minute()).format();
          const end = moment().day(dayOfWeek).hour(moment(block.endTime, 'HH:mm').hour()).minute(moment(block.endTime, 'HH:mm').minute()).format();

          return {
            title: block.blockName,
            start: start,
            end: end,
            allDay: false
          };
        });
        setEvents(events);
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
  }, [userId]);

  return (
    <FullCalendar
      plugins={[dayGridPlugin]}
      initialView="dayGridWeek"
      events={events}
      eventContent={({ event, el }) => {
        // render the inner content of the event
        ReactDOM.render(<CustomEvent event={event} />, el);
        return el;
      }}
    />
  );
};

export default UserScheduleCalendar;