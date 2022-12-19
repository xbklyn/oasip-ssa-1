import { defineStore } from 'pinia';
import { computed, ref } from 'vue';

export const useStoreToken = defineStore('token', () => {
  const accessToken = ref(localStorage.getItem('access_token'));
  const isLogged = ref(false);
  const userRole = ref('');
  const userEmail = ref('');

  const getAccessToken = computed(() => {
    return accessToken.value;
  });

  const getIsLogged = computed(() => {
    return isLogged.value;
  });

  const getUserRole = computed(() => {
    return userRole.value;
  });

  const getUserEmail = computed(() => {
    return userEmail.value;
  });

  const setAccessToken = (token) => {
    if (token === null) {
      accessToken.value = '';
      isLogged.value = false;
      return;
    }
    accessToken.value = token;
    isLogged.value = true;
  };

  const setUserRole = (role) => {
    if (role === null) {
      userRole.value = '';
      return;
    }
    userRole.value = role;
  };

  const setUserEmail = (email) => {
    if (email === null) {
      userEmail.value = '';
      return;
    }
    userEmail.value = email;
  };

  return {
    accessToken,
    isLogged,
    userRole,
    userEmail,
    setAccessToken,
    setUserRole,
    setUserEmail,
    getAccessToken,
    getIsLogged,
    getUserRole,
    getUserEmail,
  };
});
