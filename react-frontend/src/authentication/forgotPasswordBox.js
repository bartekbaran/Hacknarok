import React, {useState} from "react";
import {changePassword} from "../services/changePassword";

const ForgotPasswordBox = ({showLoginBox}) => {
  const [login, setLogin] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [oldPassword, setOldPassword] = useState("");

  function onLoginChange(e) {
    setLogin(e.target.value);
  }

  function onEmailChange(e) {
    setEmail(e.target.value);
  }

  function onPasswordChange(e) {
    setPassword(e.target.value);
  }

  function onOldPasswordChange(e) {
    setOldPassword(e.target.value);
  }

  async function handlePasswordChange(e) {
    e.preventDefault();
    const result = await changePassword(email, login, oldPassword, password);
    if (!result[0]) {
      alert(result[1]);
      return;
    }
    showLoginBox();
  }

  return (
    <form id="loginForm">
      <input
        type="text"
        id="email"
        className="fadeIn third"
        name="email"
        placeholder="email"
        value={email}
        onChange={onEmailChange}
      />
      <input
        type="text"
        id="login"
        className="fadeIn second"
        name="login"
        placeholder="login"
        value={login}
        onChange={onLoginChange}
      />
      <input
        type="text"
        id="oldPassword"
        className="fadeIn third"
        name="oldPassword"
        placeholder="old password"
        value={oldPassword}
        onChange={onOldPasswordChange}
      />
      <input
        type="password"
        id="password"
        className="fadeIn third"
        name="password"
        placeholder="new password"
        value={password}
        onChange={onPasswordChange}
      />
      <input
        type="button"
        className="fadeIn fourth"
        value="Change password"
        onClick={handlePasswordChange}
      />
    </form>

  );
};

export default ForgotPasswordBox;