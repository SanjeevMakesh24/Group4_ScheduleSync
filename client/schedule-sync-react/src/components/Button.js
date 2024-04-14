import { useMemo } from "react";
import "./Button.css";

const Button = ({ icons, signUp, propWidth }) => {
  const signUpStyle = useMemo(() => {
    return {
      width: propWidth,
    };
  }, [propWidth]);

  return (
    <button className="button1">
      <div className="icons-group">
        <img className="icons1" alt="" src={icons} />
        <div className="sign-up1" style={signUpStyle}>
          {signUp}
        </div>
      </div>
    </button>
  );
};

export default Button;
