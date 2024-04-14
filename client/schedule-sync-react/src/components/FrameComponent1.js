import { useState } from "react";
import "./FrameComponent1.css";

const FrameComponent1 = () => {
  const [checkBoxIconChecked, setCheckBoxIconChecked] = useState(true);
  return (
    <form className="frame-form">
      <div className="log-in-wrapper">
        <h1 className="log-in3">Log in</h1>
      </div>
      <div className="social-media-signuplogin-wrapper">
        <button className="social-media-signuplogin">
          <div className="social-media-logo-parent">
            <img
              className="social-media-logo"
              alt=""
              src="/social-media-logo.svg"
            />
            <div className="continue-with-google">Continue with Google</div>
          </div>
        </button>
      </div>
      <div className="divider-wrapper">
        <div className="divider3">
          <div className="divider-container">
            <div className="divider4" />
          </div>
          <div className="or1">OR</div>
          <div className="divider-frame">
            <div className="divider5" />
          </div>
        </div>
      </div>
      <div className="frame-parent9">
        <div className="email-wrapper">
          <div className="email">
            <div className="label-group">
              <div className="label4">Email address or user name</div>
              <div className="password-hide-see3">
                <img className="icon3" alt="" />
                <div className="hide3">Hide</div>
              </div>
            </div>
            <input className="text-field5" type="text" />
            <div className="error-message3">Error message</div>
          </div>
        </div>
        <div className="text-field6">
          <div className="label-container">
            <div className="label5">Password</div>
            <div className="password-hide-see4">
              <div className="icon-container">
                <img
                  className="icon4"
                  loading="lazy"
                  alt=""
                  src="/icon-1.svg"
                />
              </div>
              <div className="hide4">Hide</div>
            </div>
          </div>
          <input className="text-field7" type="text" />
          <div className="error-message4">Error message</div>
        </div>
        <div className="frame-parent10">
          <div className="forget-your-password-wrapper">
            <div className="forget-your-password">Forget your password</div>
          </div>
          <div className="check-box">
            <input
              className="check-box1"
              checked={checkBoxIconChecked}
              type="checkbox"
              onChange={(event) => setCheckBoxIconChecked(event.target.checked)}
            />
            <div className="i-want-to">Remember me</div>
          </div>
        </div>
      </div>
      <div className="button2">
        <button className="icons-container">
          <img className="icons4" alt="" />
          <div className="sign-up2">Log in</div>
        </button>
      </div>
    </form>
  );
};

export default FrameComponent1;
