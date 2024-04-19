// Home.js
import React from 'react';
import './Home.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faUserPlus, faSignInAlt } from '@fortawesome/free-solid-svg-icons';
import scheduleImage from './schedule-image.jpg';
import CreateAccount from '../CreateAccount';
import About from './About';
function Home() {
  return (
    <div className="home">
      <header>
        <nav>
          <div className="logo">ScheduleSync</div>
          <ul>
            <li><a href="About">About</a></li>
            <li><a href="#Contact"><FontAwesomeIcon icon={faEnvelope} /> Contact Us</a></li>
            <li><a href="CreateAccount"><FontAwesomeIcon icon={faUserPlus} /> Sign Up</a></li>
            <li><a href="Login"><FontAwesomeIcon icon={faSignInAlt} /> Login</a></li>
            <li><a href="Features">Features</a></li>
          </ul>
        </nav>
      </header>
      <section id="about" className="main-section">
        <div className="container">
          <p>
          Level up your scheduling game with ScheduleSync! Seamlessly sync your schedules and stay tight-knit with friends at the same time.         </p>
          <a href="CreateAccount" className="cta-btn">Sign Up</a>
        </div>
        <div className="image-container">
          <img src={scheduleImage} alt="ScheduleSync" />
        </div>
      </section>
      <footer>
        <div className="container">
          <ul>
            <li><a href="#"><FontAwesomeIcon icon={['fab', 'facebook']} /></a></li>
            <li><a href="#"><FontAwesomeIcon icon={['fab', 'twitter']} /></a></li>
            <li><a href="#"><FontAwesomeIcon icon={['fab', 'instagram']} /></a></li>
          </ul>
          <p>&copy; 2024 ScheduleSync. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
}

export default Home;
