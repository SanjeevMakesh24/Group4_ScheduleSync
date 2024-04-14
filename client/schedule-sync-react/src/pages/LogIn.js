import FrameComponent1 from "../components/FrameComponent1";
import "./LogIn.css";

const LogIn = () => {
  return (
    <div className="log-in1">
      <main className="log-in2">
        <FrameComponent1 />
        <div className="divider">
          <div className="divider1" />
          <div className="divider2" />
          <div className="or">OR</div>
        </div>
        <div className="frame-group">
          <div className="dont-have-an-account-wrapper">
            <h3 className="dont-have-an">Don’t have an account?</h3>
          </div>
          <div className="button">
            <button className="icons-parent">
              <img className="icons" alt="" />
              <div className="sign-up">Sign up</div>
            </button>
          </div>
        </div>
      </main>
    </div>
  );
};

export default LogIn;
