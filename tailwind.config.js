/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'pastel-pink': '#FFB6C1',
        'pastel-purple': '#DDA0DD',
        'pastel-blue': '#B0E0E6',
        'pastel-green': '#98FB98',
        'pastel-yellow': '#F0E68C',
      }
    },
  },
  plugins: [],
}
