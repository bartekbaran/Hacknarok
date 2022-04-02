import React, {useState} from "react";
import {registerUser} from "../services/register";

const RegisterBox = () => {
  const [login, setLogin] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const [haveRegistred, setHaveRegistred] = useState(false);

  function onLoginChange(e) {
    setLogin(e.target.value);
  }

  function onEmailChange(e) {
    setEmail(e.target.value);
  }

  function onPasswordChange(e) {
    setPassword(e.target.value);
  }

  function onFirstNameChange(e) {
    setFirstName(e.target.value);
  }

  function onLastNameChange(e) {
    setLastName(e.target.value);
  }

  async function handleRegistration(e) {
    e.preventDefault();
    await registerUser(firstName, lastName, email, login, password);
    setHaveRegistred(true);
  }

  return (
    <form id="loginForm">
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
        id="email"
        className="fadeIn third"
        name="email"
        placeholder="email"
        value={email}
        onChange={onEmailChange}
      />
      <input
        type="text"
        id="firstName"
        className="fadeIn third"
        name="firstName"
        placeholder="first name"
        value={firstName}
        onChange={onFirstNameChange}
      />
      <input
        type="text"
        id="lastName"
        className="fadeIn third"
        name="lastName"
        placeholder="last name"
        value={lastName}
        onChange={onLastNameChange}
      />
      <input
        type="password"
        id="password"
        className="fadeIn third"
        name="password"
        placeholder="password"
        value={password}
        onChange={onPasswordChange}
      />
      {!haveRegistred ? (
        <input
          type="button"
          className="fadeIn fourth"
          value="Sign up"
          onClick={handleRegistration}
        />
      ) : (
        <input
          type="button"
          className="fadeIn fourth"
          value="Registration completed."
          disabled={true}
          onClick={handleRegistration}
        />
      )}
    </form>
  );
};

export default RegisterBox;