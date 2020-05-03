import axios from 'axios';

export default {
  apiClient: axios.create({
    baseURL: process.env.VUE_APP_API_URL,
    withCredentials: false,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    timeout: 10000,
  }),
  successNotification(msg) {
    return {
      text: msg,
      color: 'success',
    };
  },
  errorNotification(err) {
    return {
      text: (err.response ? err.response.message : err),
      color: 'error',
    };
  },
};
