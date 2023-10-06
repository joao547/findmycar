import react from '@vitejs/plugin-react-swc';

const commonViteConfig = {
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: './testSetup.js',
  },
};

export default commonViteConfig;
