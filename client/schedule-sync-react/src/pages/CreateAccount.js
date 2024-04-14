import FrameComponent from "../components/FrameComponent";
import "./CreateAccount.css";

const CreateAccount = () => {
  return (
    <div className="create-account">
      <div className="image">
        <img className="rectangle-icon" alt="" src="/rectangle@2x.png" />
        <div className="header">
          <div className="header-child" />
          <div className="logo" />
        </div>
      </div>
      <main className="create-an-account">
        <div className="create-an-account-inner">
          <div className="create-an-account-parent">
            <h1 className="create-an-account1">Create an account</h1>
            <div className="have-an-account-login-wrapper">
              <div className="have-an-account-login">
                <div className="already-have-an-container">
                  <span className="already-have-an">
                    Already have an account?
                  </span>
                  <span className="span">{` `}</span>
                  <span className="log-in">{`Log in  `}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <FrameComponent />
      </main>
    </div>
  );
};

export default CreateAccount;
