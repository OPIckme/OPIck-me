import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1/';
class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'auth/login', {
        id: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem('user');
  }
  register(user) {
    return axios.post(API_URL + 'users', {
      id: user.username,
      email: user.email,
      password: user.password
    });
  }
}
export default new AuthService();