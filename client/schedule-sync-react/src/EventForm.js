import React, { useState } from 'react';
import axios from 'axios';
import './EventForm.css'; // Ensure this is correctly imported

const AddEventForm = ({ groupId }) => {
    const [title, setTitle] = useState('');
    const [start, setStart] = useState('');
    const [end, setEnd] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        const startDate = new Date(start);
        const days = ['SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'];
        const blockDay = days[startDate.getDay()];
        
        const fullTitle = 'EVENT: ' + title;

        const event = {
            eventName: fullTitle,
            timeBlock: {
                blockName: fullTitle,
                startTime: start,
                endTime: end,
                blockDay: blockDay
            },
            description: description
        };

        axios.post(`http://localhost:8080/api/event/create`, event)
            .then(response => {
                const eventId = response.data.blockName;
                console.log(response.data);
                axios.post(`http://localhost:8080/api/group/${groupId}/addEvent/${eventId}`)
                    .then(response => {
                        console.log('Event added to group schedule');
                        window.location.reload(); // Refresh the page
                    })
                    .catch(error => {
                        console.error('Error in ADDING event', error);
                    });
            })
            .catch(error => {
                console.error('Error in CREATING event', error);
            });

        setTitle('');
        setStart('');
        setEnd('');
        setDescription('');
    };

    return (
        <form onSubmit={handleSubmit} className="event-form">
            <div className="form-group">
                <label htmlFor="title" className="form-label">Title:</label>
                <input type="text" id="title" className="form-input" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} required />
            </div>

            <div className="form-group">
                <label htmlFor="start" className="form-label">Start:</label>
                <input type="datetime-local" id="start" className="form-input" placeholder="Start" value={start} onChange={(e) => setStart(e.target.value)} required />
            </div>

            <div className="form-group">
                <label htmlFor="end" className="form-label">End:</label>
                <input type="datetime-local" id="end" className="form-input" placeholder="End" value={end} onChange={(e) => setEnd(e.target.value)} required />
            </div>

            <div className="form-group">
                <label htmlFor="description" className="form-label">Description:</label>
                <input type="text" id="description" className="form-input" placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} required />
            </div>

            <button type="submit" className="submit-button8">Add Event</button>
        </form>
    );
};

export default AddEventForm;
