import axios from 'axios';
const API_URL = 'http://i7B202.p.ssafy.io:8080/api/v1/';
class AuthService {
  login(user) {
    return axios.post(API_URL + 'auth/login', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          sessionStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    sessionStorage.removeItem('user');
  }
  register(user) {
    return axios.post(API_URL + 'users', {
      username: user.username,
      email: user.email,
      password: user.password,
      nickname: user.nickname
    });
  }
}
export default new AuthService();