import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

const NavBar: React.FC = () => {
  const location = useLocation();
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  const isActive = (path: string) => {
    return location.pathname === path ? 'text-primary font-semibold bg-primary/10' : 'text-text-secondary hover:text-primary hover:bg-primary/5';
  };

  const toggleMobileMenu = () => {
    setIsMobileMenuOpen(!isMobileMenuOpen);
  };

  return (
    <nav className="bg-white/95 backdrop-blur-md shadow-lg border-b border-neutral-dark sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <div className="flex-shrink-0">
            <Link 
              to="/" 
              className="text-2xl font-bold gradient-text hover:scale-105 transition-transform duration-300"
            >
              ✨ Scinkare
            </Link>
          </div>

          {/* Navigation Links */}
          <div className="hidden md:block">
            <div className="ml-10 flex items-baseline space-x-2">
              <Link
                to="/"
                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all duration-300 hover:scale-105 ${isActive('/')}`}
              >
                🏠 Home
              </Link>
              <Link
                to="/dashboard"
                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all duration-300 hover:scale-105 ${isActive('/dashboard')}`}
              >
                📊 Dashboard
              </Link>
              <Link
                to="/about"
                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all duration-300 hover:scale-105 ${isActive('/about')}`}
              >
                ℹ️ About
              </Link>
              <Link
                to="/contact"
                className={`px-4 py-2 rounded-lg text-sm font-medium transition-all duration-300 hover:scale-105 ${isActive('/contact')}`}
              >
                📞 Contact
              </Link>
            </div>
          </div>

          {/* Mobile menu button */}
          <div className="md:hidden">
            <button
              type="button"
              onClick={toggleMobileMenu}
              className="text-text-secondary hover:text-primary focus:outline-none focus:text-primary p-2 rounded-lg hover:bg-primary/10 transition-all duration-300"
              aria-controls="mobile-menu"
              aria-expanded={isMobileMenuOpen}
            >
              <span className="sr-only">Open main menu</span>
              <svg 
                className={`h-6 w-6 transition-transform duration-300 ${isMobileMenuOpen ? 'rotate-90' : ''}`} 
                fill="none" 
                viewBox="0 0 24 24" 
                stroke="currentColor"
              >
                {isMobileMenuOpen ? (
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                ) : (
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
                )}
              </svg>
            </button>
          </div>
        </div>
      </div>

      {/* Mobile menu */}
      <div className={`md:hidden transition-all duration-300 overflow-hidden ${isMobileMenuOpen ? 'max-h-96 opacity-100' : 'max-h-0 opacity-0'}`}>
        <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3 bg-white/95 backdrop-blur-md border-t border-neutral-dark">
          <Link
            to="/"
            onClick={() => setIsMobileMenuOpen(false)}
            className={`block px-4 py-3 rounded-lg text-base font-medium transition-all duration-300 hover:scale-105 ${isActive('/')}`}
          >
            🏠 Home
          </Link>
          <Link
            to="/dashboard"
            onClick={() => setIsMobileMenuOpen(false)}
            className={`block px-4 py-3 rounded-lg text-base font-medium transition-all duration-300 hover:scale-105 ${isActive('/dashboard')}`}
          >
            📊 Dashboard
          </Link>
          <Link
            to="/about"
            onClick={() => setIsMobileMenuOpen(false)}
            className={`block px-4 py-3 rounded-lg text-base font-medium transition-all duration-300 hover:scale-105 ${isActive('/about')}`}
          >
            ℹ️ About
          </Link>
          <Link
            to="/contact"
            onClick={() => setIsMobileMenuOpen(false)}
            className={`block px-4 py-3 rounded-lg text-base font-medium transition-all duration-300 hover:scale-105 ${isActive('/contact')}`}
          >
            📞 Contact
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default NavBar;
