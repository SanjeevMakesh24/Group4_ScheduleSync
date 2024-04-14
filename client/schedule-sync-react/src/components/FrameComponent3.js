import { useMemo } from "react";
import "./FrameComponent3.css";

const FrameComponent3 = ({ title, text, propColor }) => {
  const title1Style = useMemo(() => {
    return {
      color: propColor,
    };
  }, [propColor]);

  return (
    <div className="elements-event-wrapper">
      <div className="elements-event11">
        <div className="elements-bg11">
          <div className="color15" />
        </div>
        <div className="title-parent8">
          <div className="title11" style={title1Style}>
            {title}
          </div>
          <div className="text15">{text}</div>
        </div>
      </div>
    </div>
  );
};

export default FrameComponent3;
