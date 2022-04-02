import axios from "axios";

export const getToken = async (email, password) => {
  try {
    const token = await axios.post("http://localhost:8080/authenticate", {
      nickname: email,
      password: password
    })
    return token.data
  } catch (error) {
    console.log(error)
  }
}