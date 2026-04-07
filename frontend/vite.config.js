import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  base: './',
  plugins: [react()],
  build: {
    outDir: '../src/main/resources/static',  // Builds straight to Spring
    emptyOutDir: true
  },
  server: {
    port: 5173,
    proxy: {
      '/api': 'http://localhost:8080'        // Proxies API calls in dev
    }
  }
})