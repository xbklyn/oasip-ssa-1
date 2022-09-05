import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import moment from 'moment';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  base: '/ssa1/',
  server: {
    proxy: {
      '/api': {
        target: 'https://intproj21.sit.kmutt.ac.th/ssa1',
        // target: 'http://ip21ssa1.sit.kmutt.ac.th/',
        changeOrigin: true,
        secure: false,
      },
    },
  },
});
