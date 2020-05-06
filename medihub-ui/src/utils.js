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
      default:
        return '';
    }
  },
};
