import {BrowserRouter, Routes, Route} from 'react-router-dom'
import LogIn from "./pages/LogIn";
import ForgotPassword from './pages/ForgotPassword'
import CreateAccount from './pages/CreateAccount'
import UserDashboard from './pages/UserDashboard'
import GroupSchedule from './pages/GroupSchedule'

export default function App() {
  
  return (
    <div>
      
    <Routes>
      <Route path="/" element={<LogIn />} />
      <Route path="/ForgotPassword" element={<ForgotPassword />} />
      <Route path="/CreateAccount" element={<CreateAccount />} />
      <Route path="/UserDashboard" element={<UserDashboard />} />
      <Route path="/GroupSchedule" element={<GroupSchedule />} />
    </Routes>
    
    </div>
  )
}
