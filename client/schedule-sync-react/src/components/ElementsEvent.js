import { useMemo } from "react";
import "./ElementsEvent.css";

const ElementsEvent = ({
  title,
  text,
  propHeight,
  propBackgroundColor,
  propWidth,
  propColor,
}) => {
  const elementsEventStyle = useMemo(() => {
    return {
      height: propHeight,
    };
  }, [propHeight]);

  const colorStyle = useMemo(() => {
    return {
      backgroundColor: propBackgroundColor,
    };
  }, [propBackgroundColor]);

  const titleStyle = useMemo(() => {
    return {
      width: propWidth,
      color: propColor,
    };
  }, [propWidth, propColor]);

  return (
    <div className="elements-event10" style={elementsEventStyle}>
      <div className="elements-bg10">
        <div className="color14" style={colorStyle} />
      </div>
      <div className="title-parent7">
        <div className="title10" style={titleStyle}>
          {title}
        </div>
        <div className="text14">{text}</div>
      </div>
    </div>
  );
};

export default ElementsEvent;
