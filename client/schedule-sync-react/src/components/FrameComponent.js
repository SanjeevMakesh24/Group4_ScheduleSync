import TextField from "./TextField";
import Button from "./Button";
import "./FrameComponent.css";

const FrameComponent = () => {
  return (
    <div className="text-field-parent">
      <TextField
        label="What should we call you?"
        icon="pending_I12:262;2:54"
        inputsPlaceholder="Enter your profile name"
        icons="pending_I12:262;12:950"
      />
      <TextField
        label="What’s your email?"
        icon="pending_I12:263;2:54"
        inputsPlaceholder="Enter your email address"
        icons="pending_I12:263;12:950"
        propWidth="199px"
      />
      <div className="text-field3">
        <div className="error-message1">
          <div className="label3">Create a password</div>
          <div className="password-hide-see2">
            <div className="icon-wrapper">
              <img className="icon2" loading="lazy" alt="" src="/icon-2.svg" />
            </div>
            <div className="hide2">Hide</div>
          </div>
        </div>
        <div className="text-field4">
          <input
            className="inputs1"
            placeholder="Enter your password"
            type="text"
          />
          <img className="icons3" alt="" />
        </div>
        <div className="error-message2">{`Use 8 or more characters with a mix of letters, numbers & symbols`}</div>
      </div>
      <div className="link-text-parent">
        <div className="link-text">
          <div className="by-creating-an-container">
            <span className="by-creating-an">{`By creating an account, you agree to the `}</span>
            <span className="terms-of-use">Terms of use</span>
            <span className="span1">{` `}</span>
            <span className="and">and</span>
            <span className="span2">{` `}</span>
            <span className="privacy-policy">
              <span>Privacy Policy.</span>
              <span className="span3">{` `}</span>
            </span>
          </div>
        </div>
        <Button
          icons="pending_I12:267;26:2567"
          signUp="Create an account"
          propWidth="209px"
        />
      </div>
    </div>
  );
};

export default FrameComponent;
