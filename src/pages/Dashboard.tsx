import React, { useState, useEffect } from 'react';
import { AppData, UserProfile, Product, ProgressLog } from '../types';
import { loadData, updateUserProfile, addMorningProduct, addNightProduct, removeMorningProduct, removeNightProduct, addProgressLog, removeProgressLog } from '../utils/localStorage';

const Dashboard: React.FC = () => {
  const [activeTab, setActiveTab] = useState('profile');
  const [data, setData] = useState<AppData>(loadData());
  const [showAlert, setShowAlert] = useState<{ type: 'success' | 'error'; message: string } | null>(null);

  // Alert system
  const showAlertMessage = (type: 'success' | 'error', message: string) => {
    setShowAlert({ type, message });
    setTimeout(() => setShowAlert(null), 3000);
  };

  // Profile form state
  const [profileForm, setProfileForm] = useState<UserProfile>({
    name: data.userProfile?.name || '',
    skinType: data.userProfile?.skinType || ''
  });

  // Product form state
  const [productForm, setProductForm] = useState<Omit<Product, 'id'>>({
    name: '',
    brand: '',
    category: ''
  });

  // Progress log form state
  const [progressForm, setProgressForm] = useState<Omit<ProgressLog, 'id'>>({
    date: new Date().toISOString().split('T')[0],
    acneLevel: 5,
    glowLevel: 5,
    notes: ''
  });

  // Update data when localStorage changes
  useEffect(() => {
    setData(loadData());
  }, []);

  // Handle profile form submission
  const handleProfileSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!profileForm.name.trim() || !profileForm.skinType.trim()) {
      showAlertMessage('error', 'Please fill in all fields');
      return;
    }
    updateUserProfile(profileForm);
    setData(loadData());
    showAlertMessage('success', 'Profile updated successfully!');
  };

  // Handle adding morning product
  const handleAddMorningProduct = (e: React.FormEvent) => {
    e.preventDefault();
    if (!productForm.name.trim()) {
      showAlertMessage('error', 'Please enter a product name');
      return;
    }
    const newProduct: Product = {
      ...productForm,
      id: Date.now().toString()
    };
    addMorningProduct(newProduct);
    setData(loadData());
    setProductForm({ name: '', brand: '', category: '' });
    showAlertMessage('success', 'Product added to morning routine!');
  };

  // Handle adding night product
  const handleAddNightProduct = (e: React.FormEvent) => {
    e.preventDefault();
    if (!productForm.name.trim()) {
      showAlertMessage('error', 'Please enter a product name');
      return;
    }
    const newProduct: Product = {
      ...productForm,
      id: Date.now().toString()
    };
    addNightProduct(newProduct);
    setData(loadData());
    setProductForm({ name: '', brand: '', category: '' });
    showAlertMessage('success', 'Product added to night routine!');
  };

  // Handle progress log submission
  const handleProgressSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!progressForm.notes.trim()) {
      showAlertMessage('error', 'Please add some notes');
      return;
    }
    const newLog: ProgressLog = {
      ...progressForm,
      id: Date.now().toString()
    };
    addProgressLog(newLog);
    setData(loadData());
    setProgressForm({
      date: new Date().toISOString().split('T')[0],
      acneLevel: 5,
      glowLevel: 5,
      notes: ''
    });
    showAlertMessage('success', 'Progress log added successfully!');
  };

  // Handle removing products
  const handleRemoveMorningProduct = (productId: string) => {
    removeMorningProduct(productId);
    setData(loadData());
    showAlertMessage('success', 'Product removed from morning routine');
  };

  const handleRemoveNightProduct = (productId: string) => {
    removeNightProduct(productId);
    setData(loadData());
    showAlertMessage('success', 'Product removed from night routine');
  };

  // Handle removing progress log
  const handleRemoveProgressLog = (logId: string) => {
    removeProgressLog(logId);
    setData(loadData());
    showAlertMessage('success', 'Progress log removed');
  };

  const tabs = [
    { id: 'profile', label: 'Profile', icon: '👤' },
    { id: 'morning', label: 'Morning Routine', icon: '🌅' },
    { id: 'night', label: 'Night Routine', icon: '🌙' },
    { id: 'progress', label: 'Daily Progress', icon: '📊' },
    { id: 'history', label: 'History', icon: '📈' }
  ];

  return (
    <div className="min-h-screen bg-soft-gray py-8">
      <div className="container mx-auto px-4 max-w-6xl">
        {/* Alert */}
        {showAlert && (
          <div className={`mb-6 p-4 rounded-lg ${
            showAlert.type === 'success' 
              ? 'bg-green-100 border border-green-400 text-green-700' 
              : 'bg-red-100 border border-red-400 text-red-700'
          }`}>
            {showAlert.message}
          </div>
        )}

        <div className="bg-white rounded-3xl shadow-2xl overflow-hidden border border-neutral-dark">
          {/* Header */}
          <div className="bg-gradient-to-r from-primary via-primary-light to-secondary p-8 relative overflow-hidden">
            <div className="absolute inset-0 bg-gradient-to-r from-white/10 to-transparent"></div>
            <div className="relative z-10">
              <h1 className="text-4xl font-bold text-white mb-3 animate-fade-in-down">
                ✨ Dashboard
              </h1>
              <p className="text-white/95 text-lg animate-fade-in-up delay-200">
                Manage your skincare routine and track your progress
              </p>
              <div className="mt-4 flex items-center space-x-4 animate-fade-in-up delay-400">
                <div className="flex items-center space-x-2 bg-white/20 backdrop-blur-sm rounded-full px-4 py-2">
                  <div className="w-2 h-2 bg-green-400 rounded-full animate-pulse"></div>
                  <span className="text-white text-sm font-medium">All systems active</span>
                </div>
                <div className="flex items-center space-x-2 bg-white/20 backdrop-blur-sm rounded-full px-4 py-2">
                  <span className="text-white text-sm font-medium">📊 {data.progressLogs.length} logs</span>
                </div>
              </div>
            </div>
          </div>

          {/* Enhanced Tabs */}
          <div className="border-b border-gray-200 bg-gradient-to-r from-gray-50 to-white">
            <nav className="flex overflow-x-auto px-4">
              {tabs.map((tab, index) => (
                <button
                  key={tab.id}
                  onClick={() => setActiveTab(tab.id)}
                  className={`group flex items-center space-x-3 px-6 py-4 text-sm font-medium whitespace-nowrap border-b-3 transition-all duration-300 hover:scale-105 ${
                    activeTab === tab.id
                      ? 'border-primary text-primary bg-primary/5'
                      : 'border-transparent text-text-secondary hover:text-primary hover:border-primary/50 hover:bg-primary/5'
                  }`}
                  style={{ animationDelay: `${index * 100}ms` }}
                >
                  <span className={`text-xl transition-transform duration-300 group-hover:scale-110 ${
                    activeTab === tab.id ? 'animate-bounce' : ''
                  }`}>{tab.icon}</span>
                  <span className="font-semibold">{tab.label}</span>
                  {activeTab === tab.id && (
                    <div className="w-2 h-2 bg-primary rounded-full animate-pulse"></div>
                  )}
                </button>
              ))}
            </nav>
          </div>

          {/* Tab Content */}
          <div className="p-6">
            {/* Profile Tab */}
            {activeTab === 'profile' && (
              <div className="max-w-2xl animate-fade-in-up">
                <div className="flex items-center space-x-3 mb-8">
                  <div className="w-12 h-12 bg-gradient-to-r from-primary to-secondary rounded-full flex items-center justify-center">
                    <span className="text-2xl">👤</span>
                  </div>
                  <div>
                    <h2 className="text-3xl font-bold gradient-text">User Profile</h2>
                    <p className="text-gray-600">Set up your personal information</p>
                  </div>
                </div>

                <div className="grid md:grid-cols-2 gap-8">
                  {/* Profile Form */}
                  <div className="bg-gradient-to-br from-primary/5 to-secondary/5 rounded-2xl p-6 border border-primary/20">
                    <h3 className="text-xl font-semibold text-gray-800 mb-6 flex items-center space-x-2">
                      <span>✏️</span>
                      <span>Edit Profile</span>
                    </h3>
                    <form onSubmit={handleProfileSubmit} className="space-y-6">
                      <div>
                        <label htmlFor="name" className="block text-sm font-semibold text-gray-700 mb-3">
                          👤 Full Name
                        </label>
                        <input
                          type="text"
                          id="name"
                          value={profileForm.name}
                          onChange={(e) => setProfileForm({ ...profileForm, name: e.target.value })}
                          className="w-full px-4 py-4 border-2 border-gray-200 rounded-xl focus:ring-2 focus:ring-primary focus:border-primary transition-all duration-300 hover:border-primary/50"
                          placeholder="Enter your full name"
                        />
                      </div>
                      <div>
                        <label htmlFor="skinType" className="block text-sm font-semibold text-gray-700 mb-3">
                          🧴 Skin Type
                        </label>
                        <select
                          id="skinType"
                          value={profileForm.skinType}
                          onChange={(e) => setProfileForm({ ...profileForm, skinType: e.target.value })}
                          className="w-full px-4 py-4 border-2 border-gray-200 rounded-xl focus:ring-2 focus:ring-primary focus:border-primary transition-all duration-300 hover:border-primary/50"
                        >
                          <option value="">Select your skin type</option>
                          <option value="Normal">Normal</option>
                          <option value="Dry">Dry</option>
                          <option value="Oily">Oily</option>
                          <option value="Combination">Combination</option>
                          <option value="Sensitive">Sensitive</option>
                        </select>
                      </div>
                      <button
                        type="submit"
                        className="w-full bg-gradient-to-r from-primary to-accent text-white font-bold py-4 px-6 rounded-xl hover:from-pastel-purple/90 hover:to-pastel-pink/90 transition-all duration-300 transform hover:scale-105 shadow-lg hover:shadow-xl"
                      >
                        💾 Save Profile
                      </button>
                    </form>
                  </div>

                  {/* Current Profile Display */}
                  <div className="bg-gradient-to-br from-pastel-blue/10 to-pastel-green/10 rounded-2xl p-6 border border-pastel-blue/20">
                    <h3 className="text-xl font-semibold text-gray-800 mb-6 flex items-center space-x-2">
                      <span>📋</span>
                      <span>Current Profile</span>
                    </h3>
                    {data.userProfile ? (
                      <div className="space-y-4">
                        <div className="bg-white/50 rounded-xl p-4 border border-white/50">
                          <div className="flex items-center space-x-3">
                            <div className="w-10 h-10 bg-pastel-purple/20 rounded-full flex items-center justify-center">
                              <span className="text-pastel-purple font-bold">👤</span>
                            </div>
                            <div>
                              <p className="text-sm text-gray-600">Name</p>
                              <p className="font-semibold text-gray-800">{data.userProfile.name}</p>
                            </div>
                          </div>
                        </div>
                        <div className="bg-white/50 rounded-xl p-4 border border-white/50">
                          <div className="flex items-center space-x-3">
                            <div className="w-10 h-10 bg-pastel-blue/20 rounded-full flex items-center justify-center">
                              <span className="text-pastel-blue font-bold">🧴</span>
                            </div>
                            <div>
                              <p className="text-sm text-gray-600">Skin Type</p>
                              <p className="font-semibold text-gray-800">{data.userProfile.skinType}</p>
                            </div>
                          </div>
                        </div>
                      </div>
                    ) : (
                      <div className="text-center py-8">
                        <div className="text-4xl mb-4">📝</div>
                        <p className="text-gray-500">No profile information yet</p>
                        <p className="text-sm text-gray-400">Fill out the form to get started</p>
                      </div>
                    )}
                  </div>
                </div>
              </div>
            )}

            {/* Morning Routine Tab */}
            {activeTab === 'morning' && (
              <div>
                <h2 className="text-2xl font-semibold text-gray-800 mb-6">Morning Routine</h2>
                
                {/* Add Product Form */}
                <form onSubmit={handleAddMorningProduct} className="mb-8 p-6 bg-pastel-pink/10 rounded-xl">
                  <h3 className="text-lg font-semibold text-gray-800 mb-4">Add Product</h3>
                  <div className="grid md:grid-cols-3 gap-4">
                    <input
                      type="text"
                      placeholder="Product name"
                      value={productForm.name}
                      onChange={(e) => setProductForm({ ...productForm, name: e.target.value })}
                      className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                      required
                    />
                    <input
                      type="text"
                      placeholder="Brand (optional)"
                      value={productForm.brand}
                      onChange={(e) => setProductForm({ ...productForm, brand: e.target.value })}
                      className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                    />
                    <input
                      type="text"
                      placeholder="Category (optional)"
                      value={productForm.category}
                      onChange={(e) => setProductForm({ ...productForm, category: e.target.value })}
                      className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                    />
                  </div>
                  <button
                    type="submit"
                    className="mt-4 bg-pastel-pink text-white font-semibold py-2 px-6 rounded-lg hover:bg-pastel-pink/90 transition-colors"
                  >
                    Add to Morning Routine
                  </button>
                </form>

                {/* Products List */}
                <div className="grid gap-4">
                  {data.morningRoutine.map((product) => (
                    <div key={product.id} className="flex items-center justify-between p-4 bg-white border border-gray-200 rounded-lg shadow-sm">
                      <div>
                        <h4 className="font-semibold text-gray-800">{product.name}</h4>
                        {product.brand && <p className="text-sm text-gray-600">Brand: {product.brand}</p>}
                        {product.category && <p className="text-sm text-gray-600">Category: {product.category}</p>}
                      </div>
                      <button
                        onClick={() => handleRemoveMorningProduct(product.id)}
                        className="text-red-500 hover:text-red-700 p-2"
                      >
                        <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                        </svg>
                      </button>
                    </div>
                  ))}
                  {data.morningRoutine.length === 0 && (
                    <p className="text-gray-500 text-center py-8">No products in morning routine yet</p>
                  )}
                </div>
              </div>
            )}

            {/* Night Routine Tab */}
            {activeTab === 'night' && (
              <div>
                <h2 className="text-2xl font-semibold text-gray-800 mb-6">Night Routine</h2>
                
                {/* Add Product Form */}
                <form onSubmit={handleAddNightProduct} className="mb-8 p-6 bg-pastel-purple/10 rounded-xl">
                  <h3 className="text-lg font-semibold text-gray-800 mb-4">Add Product</h3>
                  <div className="grid md:grid-cols-3 gap-4">
                    <input
                      type="text"
                      placeholder="Product name"
                      value={productForm.name}
                      onChange={(e) => setProductForm({ ...productForm, name: e.target.value })}
                      className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                      required
                    />
                    <input
                      type="text"
                      placeholder="Brand (optional)"
                      value={productForm.brand}
                      onChange={(e) => setProductForm({ ...productForm, brand: e.target.value })}
                      className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                    />
                    <input
                      type="text"
                      placeholder="Category (optional)"
                      value={productForm.category}
                      onChange={(e) => setProductForm({ ...productForm, category: e.target.value })}
                      className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                    />
                  </div>
                  <button
                    type="submit"
                    className="mt-4 bg-pastel-purple text-white font-semibold py-2 px-6 rounded-lg hover:bg-pastel-purple/90 transition-colors"
                  >
                    Add to Night Routine
                  </button>
                </form>

                {/* Products List */}
                <div className="grid gap-4">
                  {data.nightRoutine.map((product) => (
                    <div key={product.id} className="flex items-center justify-between p-4 bg-white border border-gray-200 rounded-lg shadow-sm">
                      <div>
                        <h4 className="font-semibold text-gray-800">{product.name}</h4>
                        {product.brand && <p className="text-sm text-gray-600">Brand: {product.brand}</p>}
                        {product.category && <p className="text-sm text-gray-600">Category: {product.category}</p>}
                      </div>
                      <button
                        onClick={() => handleRemoveNightProduct(product.id)}
                        className="text-red-500 hover:text-red-700 p-2"
                      >
                        <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                        </svg>
                      </button>
                    </div>
                  ))}
                  {data.nightRoutine.length === 0 && (
                    <p className="text-gray-500 text-center py-8">No products in night routine yet</p>
                  )}
                </div>
              </div>
            )}

            {/* Daily Progress Tab */}
            {activeTab === 'progress' && (
              <div className="max-w-2xl">
                <h2 className="text-2xl font-semibold text-gray-800 mb-6">Daily Progress Log</h2>
                <form onSubmit={handleProgressSubmit} className="space-y-6">
                  <div>
                    <label htmlFor="date" className="block text-sm font-medium text-gray-700 mb-2">
                      Date
                    </label>
                    <input
                      type="date"
                      id="date"
                      value={progressForm.date}
                      onChange={(e) => setProgressForm({ ...progressForm, date: e.target.value })}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent"
                    />
                  </div>

                  <div className="grid md:grid-cols-2 gap-6">
                    <div>
                      <label htmlFor="acneLevel" className="block text-sm font-medium text-gray-700 mb-2">
                        Acne Level (0-10)
                      </label>
                      <input
                        type="range"
                        id="acneLevel"
                        min="0"
                        max="10"
                        value={progressForm.acneLevel}
                        onChange={(e) => setProgressForm({ ...progressForm, acneLevel: parseInt(e.target.value) })}
                        className="w-full"
                      />
                      <div className="flex justify-between text-sm text-gray-600 mt-1">
                        <span>0 (Clear)</span>
                        <span className="font-semibold">{progressForm.acneLevel}</span>
                        <span>10 (Severe)</span>
                      </div>
                    </div>

                    <div>
                      <label htmlFor="glowLevel" className="block text-sm font-medium text-gray-700 mb-2">
                        Glow Level (0-10)
                      </label>
                      <input
                        type="range"
                        id="glowLevel"
                        min="0"
                        max="10"
                        value={progressForm.glowLevel}
                        onChange={(e) => setProgressForm({ ...progressForm, glowLevel: parseInt(e.target.value) })}
                        className="w-full"
                      />
                      <div className="flex justify-between text-sm text-gray-600 mt-1">
                        <span>0 (Dull)</span>
                        <span className="font-semibold">{progressForm.glowLevel}</span>
                        <span>10 (Radiant)</span>
                      </div>
                    </div>
                  </div>

                  <div>
                    <label htmlFor="notes" className="block text-sm font-medium text-gray-700 mb-2">
                      Notes
                    </label>
                    <textarea
                      id="notes"
                      value={progressForm.notes}
                      onChange={(e) => setProgressForm({ ...progressForm, notes: e.target.value })}
                      rows={4}
                      className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-pastel-purple focus:border-transparent resize-none"
                      placeholder="How is your skin feeling today? Any observations or changes?"
                    />
                  </div>

                  <button
                    type="submit"
                    className="bg-pastel-green text-white font-semibold py-3 px-6 rounded-lg hover:bg-pastel-green/90 transition-colors"
                  >
                    Log Progress
                  </button>
                </form>
              </div>
            )}

            {/* History Tab */}
            {activeTab === 'history' && (
              <div>
                <h2 className="text-2xl font-semibold text-gray-800 mb-6">Progress History</h2>
                
                {data.progressLogs.length > 0 ? (
                  <div className="overflow-x-auto">
                    <table className="w-full border-collapse border border-gray-300">
                      <thead>
                        <tr className="bg-pastel-blue/20">
                          <th className="border border-gray-300 px-4 py-3 text-left font-semibold text-gray-800">Date</th>
                          <th className="border border-gray-300 px-4 py-3 text-left font-semibold text-gray-800">Acne Level</th>
                          <th className="border border-gray-300 px-4 py-3 text-left font-semibold text-gray-800">Glow Level</th>
                          <th className="border border-gray-300 px-4 py-3 text-left font-semibold text-gray-800">Notes</th>
                          <th className="border border-gray-300 px-4 py-3 text-left font-semibold text-gray-800">Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {data.progressLogs.map((log) => (
                          <tr key={log.id} className="hover:bg-gray-50">
                            <td className="border border-gray-300 px-4 py-3 text-gray-700">
                              {new Date(log.date).toLocaleDateString()}
                            </td>
                            <td className="border border-gray-300 px-4 py-3">
                              <div className="flex items-center space-x-2">
                                <div className="w-16 bg-gray-200 rounded-full h-2">
                                  <div 
                                    className="bg-red-400 h-2 rounded-full" 
                                    style={{ width: `${(log.acneLevel / 10) * 100}%` }}
                                  ></div>
                                </div>
                                <span className="text-sm font-medium">{log.acneLevel}</span>
                              </div>
                            </td>
                            <td className="border border-gray-300 px-4 py-3">
                              <div className="flex items-center space-x-2">
                                <div className="w-16 bg-gray-200 rounded-full h-2">
                                  <div 
                                    className="bg-yellow-400 h-2 rounded-full" 
                                    style={{ width: `${(log.glowLevel / 10) * 100}%` }}
                                  ></div>
                                </div>
                                <span className="text-sm font-medium">{log.glowLevel}</span>
                              </div>
                            </td>
                            <td className="border border-gray-300 px-4 py-3 text-gray-700 max-w-xs truncate">
                              {log.notes}
                            </td>
                            <td className="border border-gray-300 px-4 py-3">
                              <button
                                onClick={() => handleRemoveProgressLog(log.id)}
                                className="text-red-500 hover:text-red-700 p-1"
                              >
                                <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                                </svg>
                              </button>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                ) : (
                  <div className="text-center py-12">
                    <div className="text-6xl mb-4">📊</div>
                    <p className="text-gray-500 text-lg">No progress logs yet</p>
                    <p className="text-gray-400">Start logging your daily progress to see your skin journey!</p>
                  </div>
                )}
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
