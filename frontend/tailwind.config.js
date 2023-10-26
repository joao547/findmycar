/** @type {import('tailwindcss').Config} */
export default {
  content: ['./src/**/*.{jsx,tsx}'],
  theme: {
    extend: {
      backgroundImage: {
        app: 'url(src/assets/car.png)',
      },
    },
  },
  plugins: [],
};
