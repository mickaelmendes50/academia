import axios from 'axios';

export const axiosAuth = axios.create({
  baseURL: 'https://ufg-app-login.herokuapp.com/',
});
