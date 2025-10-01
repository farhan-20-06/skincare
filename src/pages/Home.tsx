import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-neutral-light via-neutral to-neutral-dark relative overflow-hidden">
      {/* Animated Background Elements */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute -top-40 -right-40 w-80 h-80 bg-primary/10 rounded-full blur-3xl animate-pulse"></div>
        <div className="absolute -bottom-40 -left-40 w-80 h-80 bg-primary/10 rounded-full blur-3xl animate-pulse delay-1000"></div>
        <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-96 h-96 bg-primary/5 rounded-full blur-3xl animate-pulse delay-500"></div>
      </div>

      <div className="container mx-auto px-4 py-16 relative z-10">
        <div className="text-center">
          {/* App Name with Animation */}
          <div className="mb-8 animate-fade-in-up">
            <h1 className="text-6xl md:text-8xl font-bold text-black mb-4 drop-shadow-2xl">
              Scinkare
            </h1>
            <div className="w-24 h-1 bg-gradient-to-r from-accent to-primary mx-auto rounded-full"></div>
          </div>
          
          {/* Tagline with Animation */}
          <div className="animate-fade-in-up delay-200">
            <p className="text-xl md:text-3xl text-black mb-4 max-w-3xl mx-auto leading-relaxed font-light">
              Track your skincare progress and achieve your best skin ever
            </p>
            <p className="text-lg md:text-xl text-gray-700 mb-12 max-w-2xl mx-auto">
              Your personal skincare companion for a radiant, healthy glow
            </p>
          </div>

          {/* Enhanced Features with Hover Effects */}
          <div className="grid md:grid-cols-3 gap-8 mb-16 max-w-5xl mx-auto animate-fade-in-up delay-400">
            <div className="group bg-white/90 backdrop-blur-md rounded-2xl p-8 text-black hover:bg-white transition-all duration-500 hover:scale-105 hover:shadow-2xl border border-gray-200">
              <div className="text-6xl mb-6 group-hover:scale-110 transition-transform duration-300">🌅</div>
              <h3 className="text-2xl font-bold mb-4 group-hover:text-accent-light transition-colors">Morning Routine</h3>
              <p className="text-gray-700 leading-relaxed">Track your morning skincare products and build consistent habits for glowing skin</p>
              <div className="mt-4 w-full h-1 bg-gradient-to-r from-accent to-primary rounded-full opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
            </div>
            
            <div className="group bg-white/90 backdrop-blur-md rounded-2xl p-8 text-black hover:bg-white transition-all duration-500 hover:scale-105 hover:shadow-2xl border border-gray-200">
              <div className="text-6xl mb-6 group-hover:scale-110 transition-transform duration-300">🌙</div>
              <h3 className="text-2xl font-bold mb-4 group-hover:text-accent transition-colors">Night Routine</h3>
              <p className="text-gray-700 leading-relaxed">Manage your evening skincare routine for optimal skin health and recovery</p>
              <div className="mt-4 w-full h-1 bg-gradient-to-r from-primary to-secondary rounded-full opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
            </div>
            
            <div className="group bg-white/90 backdrop-blur-md rounded-2xl p-8 text-black hover:bg-white transition-all duration-500 hover:scale-105 hover:shadow-2xl border border-gray-200">
              <div className="text-6xl mb-6 group-hover:scale-110 transition-transform duration-300">📊</div>
              <h3 className="text-2xl font-bold mb-4 group-hover:text-accent-light transition-colors">Progress Tracking</h3>
              <p className="text-gray-700 leading-relaxed">Monitor your skin's progress with daily logs and detailed insights</p>
              <div className="mt-4 w-full h-1 bg-gradient-to-r from-accent-light to-accent rounded-full opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
            </div>
          </div>

          {/* Enhanced CTA Button */}
          <div className="animate-fade-in-up delay-600">
            <Link
              to="/dashboard"
              className="group inline-flex items-center space-x-3 bg-white text-primary font-bold text-xl px-10 py-5 rounded-full hover:bg-white/95 transition-all duration-300 transform hover:scale-110 shadow-2xl hover:shadow-3xl"
            >
              <span>Start Your Journey</span>
              <svg className="w-6 h-6 group-hover:translate-x-1 transition-transform duration-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7l5 5m0 0l-5 5m5-5H6" />
              </svg>
            </Link>
          </div>

          {/* Stats Section */}
          <div className="mt-20 grid grid-cols-1 md:grid-cols-3 gap-8 max-w-3xl mx-auto animate-fade-in-up delay-800">
            <div className="text-center">
              <div className="text-3xl font-bold text-black mb-2">10K+</div>
              <div className="text-gray-700">Happy Users</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-bold text-black mb-2">50K+</div>
              <div className="text-gray-700">Products Tracked</div>
            </div>
            <div className="text-center">
              <div className="text-3xl font-bold text-black mb-2">95%</div>
              <div className="text-gray-700">Satisfaction Rate</div>
            </div>
          </div>

          {/* Additional Info with Better Styling */}
          <div className="mt-16 text-gray-700 animate-fade-in-up delay-1000">
            <div className="bg-white/90 backdrop-blur-sm rounded-2xl p-6 max-w-2xl mx-auto border border-gray-200">
              <p className="text-lg font-medium mb-2 text-black">✨ Trusted by skincare enthusiasts worldwide</p>
              <p className="text-sm text-gray-600">
                Join thousands of users who are already tracking their skincare progress and achieving their skin goals
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
