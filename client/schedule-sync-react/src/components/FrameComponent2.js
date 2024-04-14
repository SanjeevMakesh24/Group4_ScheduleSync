import ElementsEvent from "./ElementsEvent";
import FrameComponent3 from "./FrameComponent3";
import "./FrameComponent2.css";

const FrameComponent2 = () => {
  return (
    <div className="elements-line-vertical-group">
      <img
        className="elements-line-vertical9"
        loading="lazy"
        alt=""
        src="/elements--line--vertical1.svg"
      />
      <div className="frame-wrapper7">
        <div className="frame-parent11">
          <div className="monday-container">
            <div className="monday2">
              <p className="p7">3</p>
              <p className="monday3">Monday</p>
            </div>
          </div>
          <div className="elements-event-parent1">
            <ElementsEvent title="1:00" text="Everyone" />
            <ElementsEvent
              title="2:30"
              text={`Logan, Noah & Andy`}
              propHeight="79px"
              propBackgroundColor="#fff"
              propWidth="62px"
              propColor="#34b53a"
            />
          </div>
          <FrameComponent3 title="5:30" text={`Sanjeev, Logan & Noah`} />
          <ElementsEvent
            title="8:30"
            text="Event: Team Meeting"
            propHeight="91.1px"
            propBackgroundColor="#efd7d7"
            propWidth="62px"
            propColor="#ffb200"
          />
        </div>
      </div>
      <img
        className="elements-line-vertical10"
        alt=""
        src="/elements--line--vertical1.svg"
      />
      <div className="frame-wrapper8">
        <div className="frame-parent12">
          <div className="frame-parent13">
            <div className="frame-parent14">
              <div className="tuesday-container">
                <div className="tuesday2">
                  <p className="p8">4</p>
                  <p className="tuesday3">Tuesday</p>
                </div>
              </div>
              <div className="elements-event12">
                <div className="elements-bg12">
                  <div className="color16" />
                </div>
                <div className="title-parent9">
                  <div className="title12">10:00</div>
                  <div className="text16">{`Logan & Noah`}</div>
                </div>
              </div>
            </div>
            <ElementsEvent
              title="1:00"
              text={`Andy, Noah & Logan`}
              propHeight="76px"
              propBackgroundColor="#fff"
              propWidth="58px"
              propColor="#34b53a"
            />
          </div>
          <div className="input-processor">
            <ElementsEvent
              title="4:00"
              text={`Sanjeev & Andy`}
              propHeight="69px"
              propBackgroundColor="#fff"
              propWidth="64px"
              propColor="#34b53a"
            />
            <ElementsEvent
              title="5:30"
              text="Sanjeev"
              propHeight="79px"
              propBackgroundColor="#fff"
              propWidth="62px"
              propColor="#34b53a"
            />
            <ElementsEvent
              title="7:00"
              text="Andy"
              propHeight="79px"
              propBackgroundColor="#fff"
              propWidth="63px"
              propColor="#34b53a"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default FrameComponent2;
