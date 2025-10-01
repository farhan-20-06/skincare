# Scinkare - Skincare Tracking App

A beautiful, minimal React application for tracking your skincare routine and progress. Built with React, TypeScript, and TailwindCSS.

## Features

### 🏠 **Home Page**
- Beautiful landing page with app branding
- Clear call-to-action to start using the app
- Feature highlights with icons

### 👤 **User Profile**
- Personal information management
- Skin type selection
- Data persistence in localStorage

### 🌅 **Morning Routine**
- Add/remove skincare products
- Track product details (name, brand, category)
- Clean, organized product list

### 🌙 **Night Routine**
- Separate evening routine management
- Same product tracking capabilities
- Easy product organization

### 📊 **Daily Progress Log**
- Track acne level (0-10 scale)
- Monitor glow level (0-10 scale)
- Add detailed notes and observations
- Date auto-fill functionality

### 📈 **Progress History**
- View all progress logs in a table
- Visual progress indicators
- Easy log management and deletion

### 📱 **Responsive Design**
- Mobile-first approach
- Works seamlessly on all devices
- Clean, intuitive interface

## Technology Stack

- **React 18** with TypeScript
- **React Router** for navigation
- **TailwindCSS** for styling
- **localStorage** for data persistence
- **Custom pastel color scheme**

## Getting Started

### Prerequisites
- Node.js (v14 or higher)
- npm or yarn

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd scinkare-frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

4. Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

## Project Structure

```
src/
├── components/
│   └── NavBar.tsx          # Navigation component
├── pages/
│   ├── Home.tsx            # Landing page
│   ├── Dashboard.tsx       # Main dashboard with tabs
│   ├── About.tsx           # About page
│   └── Contact.tsx         # Contact page
├── utils/
│   └── localStorage.ts     # Data persistence utilities
├── types/
│   └── index.ts            # TypeScript type definitions
├── App.tsx                 # Main app component with routing
└── index.css               # Global styles with TailwindCSS
```

## Key Features Explained

### Data Persistence
All user data is stored in the browser's localStorage, ensuring:
- Data persists between browser sessions
- No backend required
- Privacy-focused (data stays on user's device)

### Demo Data
First-time users get sample data to help them understand the app's capabilities:
- Sample morning and night routine products
- Example progress log
- Pre-filled product categories

### User Experience
- **Clean Interface**: Minimal design with pastel colors
- **Intuitive Navigation**: Clear tab-based dashboard
- **Real-time Feedback**: Success/error alerts for all actions
- **Mobile Responsive**: Works perfectly on all screen sizes

## Customization

### Colors
The app uses a custom pastel color palette defined in `tailwind.config.js`:
- Pastel Pink: `#FFB6C1`
- Pastel Purple: `#DDA0DD`
- Pastel Blue: `#B0E0E6`
- Pastel Green: `#98FB98`
- Pastel Yellow: `#F0E68C`

### Adding New Features
The modular structure makes it easy to add new features:
1. Add new types in `src/types/index.ts`
2. Update localStorage utilities in `src/utils/localStorage.ts`
3. Create new components in `src/components/`
4. Add new pages in `src/pages/`

## Browser Support

- Chrome (recommended)
- Firefox
- Safari
- Edge

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is open source and available under the [MIT License](LICENSE).

## Support

For questions or support, please contact us through the Contact page in the app or open an issue on GitHub.

---

**Scinkare** - Track your skincare progress and achieve your best skin ever! ✨