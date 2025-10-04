import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-pastel-pink to-pastel-purple">
      <div className="container mx-auto px-4 py-16">
        <div className="text-center">
          <h1 className="text-5xl md:text-6xl font-bold text-white mb-6">
            Welcome to Scinkare
          </h1>
          <p className="text-xl md:text-2xl text-white mb-8 max-w-3xl mx-auto">
            Track your skincare routine, monitor your progress, and achieve your best skin ever! 
            A beautiful, minimal app designed to help you stay consistent with your skincare journey.
          </p>
          
          <div className="flex flex-col sm:flex-row gap-4 justify-center mb-16">
            <Link
              to="/dashboard"
              className="bg-white text-pastel-pink px-8 py-4 rounded-full text-lg font-semibold hover:bg-pastel-yellow transition-colors shadow-lg"
            >
              Start Tracking
            </Link>
            <Link
              to="/about"
              className="bg-transparent border-2 border-white text-white px-8 py-4 rounded-full text-lg font-semibold hover:bg-white hover:text-pastel-pink transition-colors"
            >
              Learn More
            </Link>
          </div>

          <div className="grid md:grid-cols-3 gap-8 max-w-6xl mx-auto">
            <div className="bg-white bg-opacity-20 backdrop-blur-sm rounded-xl p-6 text-center">
              <div className="text-4xl mb-4">🌅</div>
              <h3 className="text-xl font-semibold text-white mb-2">Morning Routine</h3>
              <p className="text-white">Track your morning skincare products and build healthy habits</p>
            </div>
            
            <div className="bg-white bg-opacity-20 backdrop-blur-sm rounded-xl p-6 text-center">
              <div className="text-4xl mb-4">🌙</div>
              <h3 className="text-xl font-semibold text-white mb-2">Night Routine</h3>
              <p className="text-white">Manage your evening skincare routine for optimal skin health</p>
            </div>
            
            <div className="bg-white bg-opacity-20 backdrop-blur-sm rounded-xl p-6 text-center">
              <div className="text-4xl mb-4">📊</div>
              <h3 className="text-xl font-semibold text-white mb-2">Progress Tracking</h3>
              <p className="text-white">Monitor your skin's improvement with detailed progress logs</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
