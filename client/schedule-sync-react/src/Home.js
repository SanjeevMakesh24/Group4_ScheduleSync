// Home.js
import React from 'react';
import './Home.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faUserPlus, faSignInAlt } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import scheduleImage from './schedule-image.jpg';

function Home() {
  return (
    <div className="home">
      <header>
        <nav>
          <div className="logo">ScheduleSync</div>
          <ul>
            <li><Link to="/create-account"><FontAwesomeIcon icon={faUserPlus} /> Sign Up</Link></li>
            <li><Link to="/signIn"><FontAwesomeIcon icon={faSignInAlt} /> Login</Link></li>
          </ul>
        </nav>
      </header>
      <section id="about" className="main-section">
        <div className="container">
          <p>
          Level up your scheduling game with ScheduleSync! Seamlessly sync your schedules and stay tight-knit with friends at the same time.
          </p>
          <div className="button-container">
            <Link to="/create-account" className="cta-btn">Sign Up</Link>
            <Link to="/SignIn" className="cta-btn">Login</Link>
          </div>
        </div>
        <div className="image-container">
          <img src={scheduleImage} alt="ScheduleSync" />
        </div>
      </section>
      <footer>
        <div className="container">
          <p>&copy; 2024 ScheduleSync. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
}

export default Home;