import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CreateAccount from './CreateAccount';
import FriendsManager from "./Friends";
import TimeBlockForm from "./TimeBlock";
import SignIn from "./SignIn";
import UserScheduleCalendar from "./UserSchedule";
import UserDashboard from "./UserDashboard";
import CreateGroup from "./CreateGroup";
import GroupCalendar from "./GroupCalender";
import UserGroups from "./Groups";
import "./App.css";

function App() {
  return (
    <Router>
      <div>
        <Routes>
            <Route path="/" element={<CreateAccount />} />
            <Route path="/friends/:username" element={<FriendsManager />} />
            <Route path="/timeblock" element={<TimeBlockForm />} />
            <Route path="/userSchedule/:userId" element={<UserScheduleCalendar />} />
            <Route path="/signIn" element={<SignIn />} />
            <Route path="/userDashboard" element={<UserDashboard />} />
            <Route path="/createGroup/:username" element={<CreateGroup />} />
            <Route path="/groupCalendar/:groupId" element={<GroupCalendar />} />
            <Route path="/userGroups/:username" element={<UserGroups />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;