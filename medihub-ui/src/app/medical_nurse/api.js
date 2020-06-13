import utils from '@/utils';

export default {
  getNurseWorkingCalendar(id) {
    let path = 'api/medical-nurse/schedule';
    if (id) {
      path = path.concat(`?id=${id}`);
    }
    return utils.apiClient.get(path);
  },
};
