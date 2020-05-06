import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'https://restcountries.eu/rest/v2',
  withCredentials: false,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
  timeout: 10000,
});

export default {
  fetchCountries() {
    return apiClient.get('all?fields=name');
  },
};
