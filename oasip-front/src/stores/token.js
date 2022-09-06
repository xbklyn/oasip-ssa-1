import { defineStore } from 'pinia';

export const useStoreToken = defineStore('token', {
  state: () => {
    return {
      accessToken: {},
    };
  },
  actions: {
    storeToken(token) {
      this.accessToken = token;
    },
  },

  getters: {
    getAccessToken(state) {
      return state.accessToken.token;
    },
  },
});
