import { useMemo } from "react";
import "./TextField.css";

const TextField = ({ label, icon, inputsPlaceholder, icons, propWidth }) => {
  const inputsStyle = useMemo(() => {
    return {
      width: propWidth,
    };
  }, [propWidth]);

  return (
    <div className="text-field1">
      <div className="label1">
        <div className="label2">{label}</div>
        <div className="password-hide-see1">
          <img className="icon1" alt="" src={icon} />
          <div className="hide1">Hide</div>
        </div>
      </div>
      <div className="text-field2">
        <input
          className="inputs"
          placeholder={inputsPlaceholder}
          type="text"
          style={inputsStyle}
        />
        <img className="icons2" alt="" src={icons} />
      </div>
      <div className="error-message">Error message</div>
    </div>
  );
};

export default TextField;
