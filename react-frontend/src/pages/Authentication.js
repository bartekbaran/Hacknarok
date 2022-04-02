import React, {useState} from "react";
import "../styles/login.css"
import LoginBox from "../authentication/loginBox";
import ForgotPasswordBox from "../authentication/forgotPasswordBox";
import RegisterBox from "../authentication/registerBox";

const Authentication = () => {
  const [isLoginOpen, setIsLoginOpen] = useState(true);
  const [isRegisterOpen, setIsRegisterOpen] = useState(false);
  const [isForgotPasswordOpen, setIsForgotPasswordOpen] = useState(false);

  function showLoginBox() {
    setIsLoginOpen(true);
    setIsRegisterOpen(false);
    setIsForgotPasswordOpen(false);
  }

  function showRegisterBox() {
    setIsRegisterOpen(true);
    setIsLoginOpen(false);
    setIsForgotPasswordOpen(false);
  }

  function showForgotPasswordBox() {
    setIsForgotPasswordOpen(true);
    setIsLoginOpen(false);
    setIsRegisterOpen(false);
  }

  return (
    <div>
      <div className="wrapper fadeInDown" id="wrapper">
        <div id="formContent">
          <button id="btns" onClick={showLoginBox} disabled={false}>
            <h2 className={isLoginOpen ? "active" : "inactive underlineHover"}>
              {" "}
              Sign In{" "}
            </h2>
          </button>
          <button id="btns" onClick={showRegisterBox} disabled={false}>
            <h2 className={isRegisterOpen ? "active" : "inactive underlineHover"}>
              {" "}
              Sign Up{" "}
            </h2>
          </button>
          <div className="fadeIn first"></div>

          {isLoginOpen && (
            <LoginBox/>
          )}
          {isRegisterOpen && <RegisterBox/>}
          {isForgotPasswordOpen && (
            <ForgotPasswordBox {...{showLoginBox}} />
          )}

          {(
            <div id="formFooter">
              <h2
                className={
                  isForgotPasswordOpen ? "active" : "inactive underlineHover"
                }
                onClick={showForgotPasswordBox}
              >
                {" "}
                Change password{" "}
              </h2>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Authentication;