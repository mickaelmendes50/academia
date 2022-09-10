import axios from 'axios';

export const axiosCrud = axios.create({
  baseURL: 'https://ufg-app-gym.herokuapp.com/',
});
