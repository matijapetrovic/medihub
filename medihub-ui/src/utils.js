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
    console.log(err.response);
    let text = err.response ? err.response.data.message : err;
    if (!text) {
      text = err.response.status;
      if (!text || text === 401) {
        text = 'Invalid username/password';
      }
    }
    return {
      text,
      color: 'error',
    };
  },
  roleRootPath(role) {
    const roleName = role.length ? role[0] : 'UNKNOWN';
    switch (roleName) {
      case 'ROLE_PATIENT':
        return '/patient';
      case 'ROLE_DOCTOR':
        return '/medical-doctor';
      case 'ROLE_CLINIC_ADMIN':
        return '/clinic-admin';
      case 'ROLE_CLINIC_CENTER_ADMIN':
        return '/clinic-center-admin';
      case 'ROLE_NURSE':
        return '/medical-nurse';
      default:
        return '';
    }
  },
};
