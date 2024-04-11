import React from "react";
import { Link } from "react-router-dom";
//import { Button } from "./Button";
//import { HaveAnAccount } from "./HaveAnAccount";
//import { LinkText } from "./LinkText";
//import { Property1Hide } from "./Property1Hide";
import "./App.css";

export const CreateAccount = () => {

  
  return (
    
    <div 
    className="create-account">
      <div className="overlap-wrapper">
        <div className="overlap">
          <div className="image">
            <div className="overlap-group">
              <header className="header">
                <div className="rectangle" />
                <div className="logo" />
              </header>
            </div>
          </div>
          <div className="create-an-account">
            <div className="content">
              <div className="div">
                <div className="text-wrapper-7">Create an account</div>
                <div className="text-wrapper-1">Already Have An Account? Log In </div>
               {/*} <HaveAnAccount className="design-component-instance-node" /> */}
              </div>
              <div className="frame-2">
                <div className="text-field">
                  <div className="frame-3">
                    <div className="label">What should we call you?</div>
                  </div>
                  <div className="text-field-2">
                    <div className="inputs">
                      <input className="element" placeholder="Enter your profile name" type="text" />
                    </div>
                  </div>
                </div>
                <div className="text-field">
                  <div className="frame-3">
                    <input className="input" placeholder="What’s your email?" type="email" />
                  </div>
                  <div className="text-field-2">
                    <div className="inputs">
                      <input className="element" placeholder="Enter your email address" type="email" />
                    </div>
                  </div>
                </div>
                <div className="text-field">
                  <div className="frame-3">
                    <div className="label">Create a password</div>
                    <div className="password-hide-see">
                    {/*}  <Property1Hide className="icon" color="#666666" opacity="0.8" /> */}
                      <div className="text-wrapper-8">Hide</div>
                    </div>
                  </div>
                  <div className="text-field-2">
                    <div className="inputs">
                      <div className="element-2">Enter your password</div>
                    </div>
                  </div>
                  <p className="error-message">Use 8 or more characters with a mix of letters, numbers &amp; symbols</p>
                </div>
                <div className="frame-4">
                 {/* <LinkText className="design-component-instance-node" />
                  <Button className="button-instance" frameClassName="button-2" text="Create an account" /> */}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};


export default CreateAccount;