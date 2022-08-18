import moment from 'moment';
import 'moment/locale/th';

export const formatDate = (date) => {
  return moment(date).local('th').format('MMMM DD YYYY, h:mm:ss a');
};
