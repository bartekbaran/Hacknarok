import axios from "axios";

export const changePassword = async (email, login, oldPassword, newPassword) => {

  const token = sessionStorage.getItem(email)

  try {
    await axios.post("http://localhost:8080/user/updatePassword", {
      email: email,
      login: login,
      oldPassword: oldPassword,
      newPassword: newPassword
    }, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    return [true, "Success!"]
  } catch (error) {
    return [false, error.response.data.message]
  }
}