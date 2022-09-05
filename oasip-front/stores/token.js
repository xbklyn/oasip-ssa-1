import { defineStore } from 'pinia';

export const useCounterStore = defineStore('token', {
  state() {
    return {
      accessToken: '',
    };
  },
  actions: {
    storeToken(token) {
      this.accessToken = token;
    },
  },
});
