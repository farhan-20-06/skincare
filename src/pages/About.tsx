import React from 'react';

const About: React.FC = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-neutral-light via-neutral to-neutral-dark py-12">
      <div className="container mx-auto px-4 max-w-6xl">
        {/* Hero Section */}
        <div className="text-center mb-16 animate-fade-in-up">
          <div className="inline-flex items-center justify-center w-20 h-20 bg-gradient-to-r from-primary to-accent rounded-full mb-6 animate-float">
            <span className="text-4xl">✨</span>
          </div>
          <h1 className="text-5xl md:text-7xl font-bold gradient-text mb-6">
            About Scinkare
          </h1>
          <p className="text-xl md:text-2xl text-gray-600 max-w-3xl mx-auto leading-relaxed">
            Your personal skincare companion for a radiant, healthy glow
          </p>
        </div>

        <div className="grid lg:grid-cols-2 gap-12 items-center mb-16">
          {/* Main Content */}
          <div className="animate-fade-in-left">
            <div className="bg-white/80 backdrop-blur-sm rounded-3xl shadow-2xl p-8 md:p-12 border border-primary/20">
          
              <div className="prose prose-lg max-w-none">
                <p className="text-gray-700 text-lg leading-relaxed mb-8">
                  Scinkare is your personal skincare companion designed to help you track, monitor, 
                  and improve your skin health journey. We believe that consistent skincare routines 
                  and progress tracking are key to achieving your best skin.
                </p>

                <div className="space-y-8">
                  <div className="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-2xl p-6 border border-primary/20">
                    <h2 className="text-2xl font-bold text-primary mb-4 flex items-center space-x-3">
                      <span>🎯</span>
                      <span>Our Mission</span>
                    </h2>
                    <p className="text-gray-700 text-lg leading-relaxed">
                      To empower individuals to take control of their skincare journey through 
                      simple, effective tracking tools that make skincare routines manageable 
                      and progress visible.
                    </p>
                  </div>

                  <div className="bg-gradient-to-r from-accent/10 to-accent-light/10 rounded-2xl p-6 border border-accent/20">
                    <h2 className="text-2xl font-bold text-primary mb-6 flex items-center space-x-3">
                      <span>✨</span>
                      <span>Key Features</span>
                    </h2>
                    <div className="grid md:grid-cols-2 gap-4">
                      <div className="flex items-start space-x-3">
                        <div className="w-8 h-8 bg-primary/20 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                          <span className="text-primary font-bold">👤</span>
                        </div>
                        <div>
                          <h3 className="font-semibold text-gray-800">Personalized Profiles</h3>
                          <p className="text-gray-600 text-sm">Track your skin type and personal information</p>
                        </div>
                      </div>
                      <div className="flex items-start space-x-3">
                        <div className="w-8 h-8 bg-accent/20 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                          <span className="text-accent font-bold">🌅</span>
                        </div>
                        <div>
                          <h3 className="font-semibold text-gray-800">Routine Management</h3>
                          <p className="text-gray-600 text-sm">Organize morning and evening skincare products</p>
                        </div>
                      </div>
                      <div className="flex items-start space-x-3">
                        <div className="w-8 h-8 bg-accent-light/20 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                          <span className="text-accent-light font-bold">📊</span>
                        </div>
                        <div>
                          <h3 className="font-semibold text-gray-800">Progress Logging</h3>
                          <p className="text-gray-600 text-sm">Record daily skin condition and track improvements</p>
                        </div>
                      </div>
                      <div className="flex items-start space-x-3">
                        <div className="w-8 h-8 bg-accent/20 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                          <span className="text-accent font-bold">🔒</span>
                        </div>
                        <div>
                          <h3 className="font-semibold text-gray-800">Data Privacy</h3>
                          <p className="text-gray-600 text-sm">Your data is safely stored locally on your device</p>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="bg-gradient-to-r from-pastel-yellow/10 to-accent/10 rounded-2xl p-6 border border-pastel-yellow/20">
                    <h2 className="text-2xl font-bold text-primary mb-4 flex items-center space-x-3">
                      <span>💡</span>
                      <span>Why Scinkare?</span>
                    </h2>
                    <p className="text-gray-700 text-lg leading-relaxed">
                      Unlike complex skincare apps, Scinkare focuses on simplicity and effectiveness. 
                      We understand that skincare routines can be overwhelming, so we've created a 
                      clean, minimal interface that makes tracking your progress enjoyable and sustainable.
                    </p>
                  </div>
                </div>
              </div>
            </div>

            {/* Visual Elements */}
            <div className="animate-fade-in-right">
              <div className="bg-gradient-to-br from-primary/20 to-accent/20 rounded-3xl p-8 text-center">
                <div className="grid grid-cols-2 md:grid-cols-4 gap-6 mb-8">
                  <div className="bg-white/50 rounded-2xl p-4 animate-float">
                    <div className="text-3xl mb-2">🌅</div>
                    <div className="text-sm font-semibold text-gray-700">Morning</div>
                  </div>
                  <div className="bg-white/50 rounded-2xl p-4 animate-float" style={{ animationDelay: '0.5s' }}>
                    <div className="text-3xl mb-2">🌙</div>
                    <div className="text-sm font-semibold text-gray-700">Night</div>
                  </div>
                  <div className="bg-white/50 rounded-2xl p-4 animate-float" style={{ animationDelay: '1s' }}>
                    <div className="text-3xl mb-2">📊</div>
                    <div className="text-sm font-semibold text-gray-700">Progress</div>
                  </div>
                  <div className="bg-white/50 rounded-2xl p-4 animate-float" style={{ animationDelay: '1.5s' }}>
                    <div className="text-3xl mb-2">✨</div>
                    <div className="text-sm font-semibold text-gray-700">Results</div>
                  </div>
                </div>
                
                <div className="bg-white/60 backdrop-blur-sm rounded-2xl p-6 border border-white/50">
                  <h3 className="text-2xl font-bold text-primary mb-4 flex items-center justify-center space-x-2">
                    <span>🚀</span>
                    <span>Start Your Journey Today</span>
                  </h3>
                  <p className="text-gray-700 mb-6">
                    Ready to transform your skincare routine? Head to the Dashboard to create 
                    your profile and start tracking your progress!
                  </p>
                  <a 
                    href="/dashboard"
                    className="inline-flex items-center space-x-2 bg-gradient-to-r from-primary to-accent text-white font-bold py-3 px-8 rounded-full hover:from-primary/90 hover:to-accent/90 transition-all duration-300 transform hover:scale-105 shadow-lg hover:shadow-xl"
                  >
                    <span>Get Started</span>
                    <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7l5 5m0 0l-5 5m5-5H6" />
                    </svg>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default About;
