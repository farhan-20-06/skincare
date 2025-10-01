/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#374151',      // Dark grey
        'primary-light': '#4B5563', // Medium grey
        'primary-dark': '#1F2937',  // Very dark grey
        'secondary': '#6B7280',     // Light grey
        'secondary-light': '#9CA3AF', // Very light grey
        'accent': '#10B981',        // Green accent
        'accent-light': '#34D399',  // Light green
        'accent-dark': '#059669',   // Dark green
        'neutral': '#F3F4F6',       // Background grey
        'neutral-light': '#F9FAFB', // Very light background
        'neutral-dark': '#E5E7EB',  // Border grey
        'text-primary': '#111827',  // Dark text
        'text-secondary': '#6B7280', // Medium text
        'text-light': '#9CA3AF',    // Light text
      }
    },
  },
  plugins: [],
}
