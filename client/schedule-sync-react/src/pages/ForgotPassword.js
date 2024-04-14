import Button from "../components/Button";
import "./ForgotPassword.css";

const ForgotPassword = () => {
  return (
    <div className="forgot-password">
      <div className="forgot-password-wrapper">
        <h1 className="forgot-password1">Forgot Password?</h1>
      </div>
      <div className="password-hide-see">
        <img className="icon" alt="" />
        <div className="hide">Hide</div>
      </div>
      <div className="frame-parent">
        <div className="label-parent">
          <div className="label">Email address or user name</div>
          <input className="text-field" type="text" />
        </div>
        <Button icons="pending_I12:604;26:2567" signUp="Send Reset Link" />
      </div>
    </div>
  );
};

export default ForgotPassword;
