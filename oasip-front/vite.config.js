import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    proxy: {
        '/api': {
            target: 'http://ip21ssa1.sit.kmutt.ac.th:8080',
            changeOrigin: true,
            secure: false,
            ws: true,
        }
    }
})