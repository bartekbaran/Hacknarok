import axios from "axios";

export const registerUser = async (
  firstName,
  lastName,
  email,
  login,
  password
) => {
  const token = await registrate(firstName, lastName, email, login, password);
  await confirmRegistration(token);
};

async function registrate(firstName, lastName, email, login, password) {
  try {
    const registrationResponse = await axios.post(
      "http://localhost:8080/auth/registration",
      {
        firstname: firstName,
        lastname: lastName,
        email: email,
        nickname: login,
        password: password,
      }
    );

    return registrationResponse.data;
  } catch (error) {
    alert(
      "Error occurred! \n" +
      "Error code: " +
      error.response.data.status +
      "\n" +
      "Reason: " +
      error.response.data.message
    );
  }
}

async function confirmRegistration(token) {
  try {
    await axios.get("http://localhost:8080/auth/registration/confirm", {
      params: {
        token: token,
      },
    });
  } catch (error) {
    alert("Failed to authenticate the user!");
  }
}