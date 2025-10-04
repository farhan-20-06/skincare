import React from 'react';

const About: React.FC = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-pastel-blue to-pastel-green">
      <div className="container mx-auto px-4 py-16">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-5xl font-bold text-center text-white mb-8">About Scinkare</h1>
          
          <div className="bg-white bg-opacity-90 rounded-xl p-8 shadow-lg mb-8">
            <h2 className="text-3xl font-bold text-gray-800 mb-6">Our Mission</h2>
            <p className="text-lg text-gray-700 mb-6">
              Scinkare is designed to help you build and maintain consistent skincare habits. 
              We believe that beautiful, healthy skin comes from understanding your routine, 
              tracking your progress, and staying motivated on your skincare journey.
            </p>
            
            <h2 className="text-3xl font-bold text-gray-800 mb-6">Why We Built This</h2>
            <p className="text-lg text-gray-700 mb-6">
              Skincare can be overwhelming with countless products and routines. Scinkare 
              simplifies this by providing a clean, intuitive platform to track your 
              morning and night routines, monitor your skin's progress, and set achievable goals.
            </p>
          </div>

          <div className="grid md:grid-cols-2 gap-8">
            <div className="bg-white bg-opacity-90 rounded-xl p-6 shadow-lg">
              <h3 className="text-2xl font-bold text-gray-800 mb-4">✨ Key Features</h3>
              <ul className="space-y-3 text-gray-700">
                <li className="flex items-center">
                  <span className="text-pastel-pink mr-3">🌅</span>
                  Morning & Night Routine Tracking
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-pink mr-3">📊</span>
                  Daily Progress Logging
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-pink mr-3">📈</span>
                  Progress History & Analytics
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-pink mr-3">🎯</span>
                  Goal Setting & Tracking
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-pink mr-3">📱</span>
                  Mobile-Responsive Design
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-pink mr-3">🔒</span>
                  Privacy-Focused (Local Storage)
                </li>
              </ul>
            </div>

            <div className="bg-white bg-opacity-90 rounded-xl p-6 shadow-lg">
              <h3 className="text-2xl font-bold text-gray-800 mb-4">🛠️ Technology</h3>
              <ul className="space-y-3 text-gray-700">
                <li className="flex items-center">
                  <span className="text-pastel-blue mr-3">⚛️</span>
                  React 18 with TypeScript
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-blue mr-3">🎨</span>
                  TailwindCSS for Styling
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-blue mr-3">🔄</span>
                  React Router for Navigation
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-blue mr-3">💾</span>
                  LocalStorage for Data Persistence
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-blue mr-3">📱</span>
                  Mobile-First Responsive Design
                </li>
                <li className="flex items-center">
                  <span className="text-pastel-blue mr-3">🎨</span>
                  Custom Pastel Color Palette
                </li>
              </ul>
            </div>
          </div>

          <div className="bg-white bg-opacity-90 rounded-xl p-8 shadow-lg mt-8">
            <h2 className="text-3xl font-bold text-gray-800 mb-6 text-center">Get Started Today</h2>
            <p className="text-lg text-gray-700 text-center mb-6">
              Ready to transform your skincare routine? Start tracking your progress and 
              achieve your best skin ever with Scinkare!
            </p>
            <div className="text-center">
              <a
                href="/dashboard"
                className="inline-block bg-pastel-pink text-white px-8 py-4 rounded-full text-lg font-semibold hover:bg-pink-400 transition-colors shadow-lg"
              >
                Start Your Journey
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default About;
