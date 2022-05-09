import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    base: '/ssa1/',
    server: {
        proxy: {
            '/api': {
                target: 'http://intproj21.sit.kmutt.ac.th:8080/ssa1',
                changeOrigin: true,
                secure: false,
                rewrite: (path) => path.replace(/^\/api/, '')
            }
        }
    }
})

