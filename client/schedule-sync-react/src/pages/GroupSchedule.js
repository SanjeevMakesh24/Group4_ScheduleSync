import FrameComponent2 from "../components/FrameComponent2";
import ElseStatement from "../components/ElseStatement";
import "./GroupSchedule.css";

const GroupSchedule = () => {
  return (
    <div className="group-schedule">
      <div className="input-gate">
        <img className="side-bar1-icon1" alt="" src="/side-bar1.svg" />
      </div>
      <div className="output-port">
        <div className="memory-bank">
          <div className="comparator">
            <div className="elements-button2">
              <div className="color12" />
              <div className="text12">Month</div>
            </div>
            <div className="elements-button3">
              <div className="color13" />
              <div className="text13">Week</div>
            </div>
          </div>
          <FrameComponent2 />
        </div>
      </div>
      <div className="elements-line-vertical-frame">
        <img
          className="elements-line-vertical8"
          alt=""
          src="/elements--line--vertical1.svg"
        />
      </div>
      <ElseStatement />
    </div>
  );
};

export default GroupSchedule;
