import axios from "axios";

export const logIn = async (email, password) => {
  try {
    const userData = await axios.post("http://localhost:8080/auth/login", {
      email: email,
      password: password,
    });
    return userData.data
  } catch (error) {
    console.log(error);
    return false
  }
};