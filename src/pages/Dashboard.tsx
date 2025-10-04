import React, { useState, useEffect } from 'react';
import { User, Product, ProgressLog, SkinGoal } from '../types';
import { getOrCreateUserData, saveUserData } from '../utils/localStorage';

const Dashboard: React.FC = () => {
  const [user, setUser] = useState<User | null>(null);
  const [activeTab, setActiveTab] = useState<'profile' | 'morning' | 'night' | 'progress' | 'history' | 'goals'>('profile');
  const [showAddProduct, setShowAddProduct] = useState(false);
  const [showAddLog, setShowAddLog] = useState(false);
  const [showAddGoal, setShowAddGoal] = useState(false);
  const [newProduct, setNewProduct] = useState({ name: '', brand: '', category: '', notes: '' });
  const [newLog, setNewLog] = useState({ acneLevel: 5, glowLevel: 5, notes: '' });
  const [newGoal, setNewGoal] = useState({ title: '', description: '', targetDate: '', category: 'Custom' });

  useEffect(() => {
    const userData = getOrCreateUserData();
    setUser(userData);
  }, []);

  const handleSaveUser = () => {
    if (user) {
      saveUserData(user);
      alert('Profile updated successfully!');
    }
  };

  const addProduct = (routine: 'morning' | 'night') => {
    if (user && newProduct.name && newProduct.brand && newProduct.category) {
      const product: Product = {
        id: Date.now().toString(),
        ...newProduct
      };
      
      const updatedUser = {
        ...user,
        [routine === 'morning' ? 'morningRoutine' : 'nightRoutine']: [
          ...user[routine === 'morning' ? 'morningRoutine' : 'nightRoutine'],
          product
        ]
      };
      
      setUser(updatedUser);
      saveUserData(updatedUser);
      setNewProduct({ name: '', brand: '', category: '', notes: '' });
      setShowAddProduct(false);
    }
  };

  const removeProduct = (routine: 'morning' | 'night', productId: string) => {
    if (user) {
      const updatedUser = {
        ...user,
        [routine === 'morning' ? 'morningRoutine' : 'nightRoutine']: 
          user[routine === 'morning' ? 'morningRoutine' : 'nightRoutine'].filter(p => p.id !== productId)
      };
      
      setUser(updatedUser);
      saveUserData(updatedUser);
    }
  };

  const addProgressLog = () => {
    if (user && newLog.notes) {
      const log: ProgressLog = {
        id: Date.now().toString(),
        date: new Date().toISOString().split('T')[0],
        ...newLog
      };
      
      const updatedUser = {
        ...user,
        progressLogs: [...user.progressLogs, log]
      };
      
      setUser(updatedUser);
      saveUserData(updatedUser);
      setNewLog({ acneLevel: 5, glowLevel: 5, notes: '' });
      setShowAddLog(false);
    }
  };

  const addGoal = () => {
    if (user && newGoal.title && newGoal.description && newGoal.targetDate) {
      const goal: SkinGoal = {
        id: Date.now().toString(),
        progress: 0,
        completed: false,
        ...newGoal
      };
      
      const updatedUser = {
        ...user,
        goals: [...user.goals, goal]
      };
      
      setUser(updatedUser);
      saveUserData(updatedUser);
      setNewGoal({ title: '', description: '', targetDate: '', category: 'Custom' });
      setShowAddGoal(false);
    }
  };

  const deleteLog = (logId: string) => {
    if (user) {
      const updatedUser = {
        ...user,
        progressLogs: user.progressLogs.filter(log => log.id !== logId)
      };
      
      setUser(updatedUser);
      saveUserData(updatedUser);
    }
  };

  const deleteGoal = (goalId: string) => {
    if (user) {
      const updatedUser = {
        ...user,
        goals: user.goals.filter(goal => goal.id !== goalId)
      };
      
      setUser(updatedUser);
      saveUserData(updatedUser);
    }
  };

  if (!user) {
    return <div className="min-h-screen bg-pastel-blue flex items-center justify-center">
      <div className="text-center">
        <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-pastel-pink mx-auto"></div>
        <p className="mt-4 text-lg text-gray-600">Loading...</p>
      </div>
    </div>;
  }

  return (
    <div className="min-h-screen bg-pastel-blue">
      <div className="container mx-auto px-4 py-8">
        <h1 className="text-4xl font-bold text-center text-gray-800 mb-8">Your Skincare Dashboard</h1>
        
        {/* Tab Navigation */}
        <div className="flex flex-wrap justify-center mb-8">
          {[
            { id: 'profile', label: '👤 Profile', color: 'pastel-pink' },
            { id: 'morning', label: '🌅 Morning', color: 'pastel-yellow' },
            { id: 'night', label: '🌙 Night', color: 'pastel-purple' },
            { id: 'progress', label: '📊 Progress', color: 'pastel-green' },
            { id: 'history', label: '📈 History', color: 'pastel-blue' },
            { id: 'goals', label: '🎯 Goals', color: 'pastel-pink' }
          ].map(tab => (
            <button
              key={tab.id}
              onClick={() => setActiveTab(tab.id as any)}
              className={`px-4 py-2 m-1 rounded-full font-semibold transition-colors ${
                activeTab === tab.id
                  ? `bg-${tab.color} text-white`
                  : 'bg-white text-gray-700 hover:bg-gray-100'
              }`}
            >
              {tab.label}
            </button>
          ))}
        </div>

        {/* Profile Tab */}
        {activeTab === 'profile' && (
          <div className="bg-white rounded-xl p-6 shadow-lg">
            <h2 className="text-2xl font-bold mb-4 text-gray-800">User Profile</h2>
            <div className="grid md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Name</label>
                <input
                  type="text"
                  value={user.name}
                  onChange={(e) => setUser({...user, name: e.target.value})}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Skin Type</label>
                <select
                  value={user.skinType}
                  onChange={(e) => setUser({...user, skinType: e.target.value})}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                >
                  <option value="Dry">Dry</option>
                  <option value="Oily">Oily</option>
                  <option value="Combination">Combination</option>
                  <option value="Normal">Normal</option>
                  <option value="Sensitive">Sensitive</option>
                </select>
              </div>
            </div>
            <div className="mt-6">
              <h3 className="text-lg font-semibold mb-2 text-gray-800">Streak Information</h3>
              <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                <div className="text-center p-3 bg-pastel-yellow rounded-lg">
                  <div className="text-2xl font-bold text-gray-800">{user.streaks.morning}</div>
                  <div className="text-sm text-gray-600">Morning</div>
                </div>
                <div className="text-center p-3 bg-pastel-purple rounded-lg">
                  <div className="text-2xl font-bold text-gray-800">{user.streaks.night}</div>
                  <div className="text-sm text-gray-600">Night</div>
                </div>
                <div className="text-center p-3 bg-pastel-green rounded-lg">
                  <div className="text-2xl font-bold text-gray-800">{user.streaks.total}</div>
                  <div className="text-sm text-gray-600">Total</div>
                </div>
                <div className="text-center p-3 bg-pastel-pink rounded-lg">
                  <div className="text-2xl font-bold text-gray-800">{user.streaks.personalBest}</div>
                  <div className="text-sm text-gray-600">Best</div>
                </div>
              </div>
            </div>
            <button
              onClick={handleSaveUser}
              className="mt-6 bg-pastel-pink text-white px-6 py-2 rounded-lg hover:bg-pink-400 transition-colors"
            >
              Save Profile
            </button>
          </div>
        )}

        {/* Morning Routine Tab */}
        {activeTab === 'morning' && (
          <div className="bg-white rounded-xl p-6 shadow-lg">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-2xl font-bold text-gray-800">Morning Routine</h2>
              <button
                onClick={() => setShowAddProduct(true)}
                className="bg-pastel-yellow text-white px-4 py-2 rounded-lg hover:bg-yellow-400 transition-colors"
              >
                Add Product
              </button>
            </div>
            
            {showAddProduct && (
              <div className="mb-6 p-4 bg-gray-50 rounded-lg">
                <h3 className="text-lg font-semibold mb-3">Add New Product</h3>
                <div className="grid md:grid-cols-2 gap-4">
                  <input
                    type="text"
                    placeholder="Product Name"
                    value={newProduct.name}
                    onChange={(e) => setNewProduct({...newProduct, name: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-yellow"
                  />
                  <input
                    type="text"
                    placeholder="Brand"
                    value={newProduct.brand}
                    onChange={(e) => setNewProduct({...newProduct, brand: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-yellow"
                  />
                  <input
                    type="text"
                    placeholder="Category (e.g., Cleanser, Serum)"
                    value={newProduct.category}
                    onChange={(e) => setNewProduct({...newProduct, category: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-yellow"
                  />
                  <input
                    type="text"
                    placeholder="Notes (optional)"
                    value={newProduct.notes}
                    onChange={(e) => setNewProduct({...newProduct, notes: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-yellow"
                  />
                </div>
                <div className="mt-3 flex gap-2">
                  <button
                    onClick={() => addProduct('morning')}
                    className="bg-pastel-yellow text-white px-4 py-2 rounded-lg hover:bg-yellow-400 transition-colors"
                  >
                    Add to Morning Routine
                  </button>
                  <button
                    onClick={() => setShowAddProduct(false)}
                    className="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition-colors"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            )}

            <div className="space-y-3">
              {user.morningRoutine.map(product => (
                <div key={product.id} className="flex justify-between items-center p-4 bg-pastel-yellow bg-opacity-20 rounded-lg">
                  <div>
                    <h3 className="font-semibold text-gray-800">{product.name}</h3>
                    <p className="text-sm text-gray-600">{product.brand} • {product.category}</p>
                    {product.notes && <p className="text-sm text-gray-500 italic">{product.notes}</p>}
                  </div>
                  <button
                    onClick={() => removeProduct('morning', product.id)}
                    className="text-red-500 hover:text-red-700 font-bold"
                  >
                    ✕
                  </button>
                </div>
              ))}
              {user.morningRoutine.length === 0 && (
                <p className="text-gray-500 text-center py-8">No products in your morning routine yet. Add some to get started!</p>
              )}
            </div>
          </div>
        )}

        {/* Night Routine Tab */}
        {activeTab === 'night' && (
          <div className="bg-white rounded-xl p-6 shadow-lg">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-2xl font-bold text-gray-800">Night Routine</h2>
              <button
                onClick={() => setShowAddProduct(true)}
                className="bg-pastel-purple text-white px-4 py-2 rounded-lg hover:bg-purple-400 transition-colors"
              >
                Add Product
              </button>
            </div>
            
            {showAddProduct && (
              <div className="mb-6 p-4 bg-gray-50 rounded-lg">
                <h3 className="text-lg font-semibold mb-3">Add New Product</h3>
                <div className="grid md:grid-cols-2 gap-4">
                  <input
                    type="text"
                    placeholder="Product Name"
                    value={newProduct.name}
                    onChange={(e) => setNewProduct({...newProduct, name: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-purple"
                  />
                  <input
                    type="text"
                    placeholder="Brand"
                    value={newProduct.brand}
                    onChange={(e) => setNewProduct({...newProduct, brand: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-purple"
                  />
                  <input
                    type="text"
                    placeholder="Category (e.g., Cleanser, Serum)"
                    value={newProduct.category}
                    onChange={(e) => setNewProduct({...newProduct, category: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-purple"
                  />
                  <input
                    type="text"
                    placeholder="Notes (optional)"
                    value={newProduct.notes}
                    onChange={(e) => setNewProduct({...newProduct, notes: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-purple"
                  />
                </div>
                <div className="mt-3 flex gap-2">
                  <button
                    onClick={() => addProduct('night')}
                    className="bg-pastel-purple text-white px-4 py-2 rounded-lg hover:bg-purple-400 transition-colors"
                  >
                    Add to Night Routine
                  </button>
                  <button
                    onClick={() => setShowAddProduct(false)}
                    className="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition-colors"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            )}

            <div className="space-y-3">
              {user.nightRoutine.map(product => (
                <div key={product.id} className="flex justify-between items-center p-4 bg-pastel-purple bg-opacity-20 rounded-lg">
                  <div>
                    <h3 className="font-semibold text-gray-800">{product.name}</h3>
                    <p className="text-sm text-gray-600">{product.brand} • {product.category}</p>
                    {product.notes && <p className="text-sm text-gray-500 italic">{product.notes}</p>}
                  </div>
                  <button
                    onClick={() => removeProduct('night', product.id)}
                    className="text-red-500 hover:text-red-700 font-bold"
                  >
                    ✕
                  </button>
                </div>
              ))}
              {user.nightRoutine.length === 0 && (
                <p className="text-gray-500 text-center py-8">No products in your night routine yet. Add some to get started!</p>
              )}
            </div>
          </div>
        )}

        {/* Progress Tab */}
        {activeTab === 'progress' && (
          <div className="bg-white rounded-xl p-6 shadow-lg">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-2xl font-bold text-gray-800">Daily Progress Log</h2>
              <button
                onClick={() => setShowAddLog(true)}
                className="bg-pastel-green text-white px-4 py-2 rounded-lg hover:bg-green-400 transition-colors"
              >
                Add Log Entry
              </button>
            </div>
            
            {showAddLog && (
              <div className="mb-6 p-4 bg-gray-50 rounded-lg">
                <h3 className="text-lg font-semibold mb-3">Add Progress Log</h3>
                <div className="grid md:grid-cols-2 gap-4 mb-4">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">Acne Level (0-10)</label>
                    <input
                      type="range"
                      min="0"
                      max="10"
                      value={newLog.acneLevel}
                      onChange={(e) => setNewLog({...newLog, acneLevel: parseInt(e.target.value)})}
                      className="w-full"
                    />
                    <div className="text-center text-sm text-gray-600">{newLog.acneLevel}</div>
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">Glow Level (0-10)</label>
                    <input
                      type="range"
                      min="0"
                      max="10"
                      value={newLog.glowLevel}
                      onChange={(e) => setNewLog({...newLog, glowLevel: parseInt(e.target.value)})}
                      className="w-full"
                    />
                    <div className="text-center text-sm text-gray-600">{newLog.glowLevel}</div>
                  </div>
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">Notes</label>
                  <textarea
                    value={newLog.notes}
                    onChange={(e) => setNewLog({...newLog, notes: e.target.value})}
                    placeholder="How is your skin feeling today? Any observations?"
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-green"
                    rows={3}
                  />
                </div>
                <div className="mt-3 flex gap-2">
                  <button
                    onClick={addProgressLog}
                    className="bg-pastel-green text-white px-4 py-2 rounded-lg hover:bg-green-400 transition-colors"
                  >
                    Add Log Entry
                  </button>
                  <button
                    onClick={() => setShowAddLog(false)}
                    className="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition-colors"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            )}

            <div className="text-center text-gray-500">
              <p>Track your daily skin condition and progress here!</p>
              <p className="text-sm">Use the "Add Log Entry" button to record today's skin status.</p>
            </div>
          </div>
        )}

        {/* History Tab */}
        {activeTab === 'history' && (
          <div className="bg-white rounded-xl p-6 shadow-lg">
            <h2 className="text-2xl font-bold text-gray-800 mb-4">Progress History</h2>
            
            <div className="space-y-4">
              {user.progressLogs.map(log => (
                <div key={log.id} className="p-4 bg-pastel-green bg-opacity-20 rounded-lg">
                  <div className="flex justify-between items-start">
                    <div className="flex-1">
                      <div className="flex items-center gap-4 mb-2">
                        <span className="font-semibold text-gray-800">{log.date}</span>
                        <div className="flex gap-4 text-sm">
                          <span className="bg-red-100 text-red-800 px-2 py-1 rounded">
                            Acne: {log.acneLevel}/10
                          </span>
                          <span className="bg-yellow-100 text-yellow-800 px-2 py-1 rounded">
                            Glow: {log.glowLevel}/10
                          </span>
                        </div>
                      </div>
                      <p className="text-gray-700">{log.notes}</p>
                    </div>
                    <button
                      onClick={() => deleteLog(log.id)}
                      className="text-red-500 hover:text-red-700 font-bold ml-4"
                    >
                      ✕
                    </button>
                  </div>
                </div>
              ))}
              {user.progressLogs.length === 0 && (
                <p className="text-gray-500 text-center py-8">No progress logs yet. Start tracking your skin's journey!</p>
              )}
            </div>
          </div>
        )}

        {/* Goals Tab */}
        {activeTab === 'goals' && (
          <div className="bg-white rounded-xl p-6 shadow-lg">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-2xl font-bold text-gray-800">Skin Goals</h2>
              <button
                onClick={() => setShowAddGoal(true)}
                className="bg-pastel-pink text-white px-4 py-2 rounded-lg hover:bg-pink-400 transition-colors"
              >
                Add Goal
              </button>
            </div>
            
            {showAddGoal && (
              <div className="mb-6 p-4 bg-gray-50 rounded-lg">
                <h3 className="text-lg font-semibold mb-3">Add New Goal</h3>
                <div className="grid md:grid-cols-2 gap-4">
                  <input
                    type="text"
                    placeholder="Goal Title"
                    value={newGoal.title}
                    onChange={(e) => setNewGoal({...newGoal, title: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  />
                  <select
                    value={newGoal.category}
                    onChange={(e) => setNewGoal({...newGoal, category: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  >
                    <option value="Acne">Acne</option>
                    <option value="Hydration">Hydration</option>
                    <option value="Brightness">Brightness</option>
                    <option value="Texture">Texture</option>
                    <option value="Custom">Custom</option>
                  </select>
                  <input
                    type="date"
                    value={newGoal.targetDate}
                    onChange={(e) => setNewGoal({...newGoal, targetDate: e.target.value})}
                    className="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                  />
                </div>
                <div className="mt-4">
                  <textarea
                    value={newGoal.description}
                    onChange={(e) => setNewGoal({...newGoal, description: e.target.value})}
                    placeholder="Describe your goal in detail..."
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-pastel-pink"
                    rows={3}
                  />
                </div>
                <div className="mt-3 flex gap-2">
                  <button
                    onClick={addGoal}
                    className="bg-pastel-pink text-white px-4 py-2 rounded-lg hover:bg-pink-400 transition-colors"
                  >
                    Add Goal
                  </button>
                  <button
                    onClick={() => setShowAddGoal(false)}
                    className="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition-colors"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            )}

            <div className="space-y-4">
              {user.goals.map(goal => (
                <div key={goal.id} className="p-4 bg-pastel-pink bg-opacity-20 rounded-lg">
                  <div className="flex justify-between items-start">
                    <div className="flex-1">
                      <div className="flex items-center gap-2 mb-2">
                        <h3 className="font-semibold text-gray-800">{goal.title}</h3>
                        <span className="bg-pastel-purple text-white px-2 py-1 rounded text-xs">
                          {goal.category}
                        </span>
                      </div>
                      <p className="text-gray-700 mb-2">{goal.description}</p>
                      <div className="flex items-center gap-4 text-sm text-gray-600">
                        <span>Target: {goal.targetDate}</span>
                        <span>Progress: {goal.progress}%</span>
                        <div className="w-32 bg-gray-200 rounded-full h-2">
                          <div 
                            className="bg-pastel-pink h-2 rounded-full" 
                            style={{ width: `${goal.progress}%` }}
                          ></div>
                        </div>
                      </div>
                    </div>
                    <button
                      onClick={() => deleteGoal(goal.id)}
                      className="text-red-500 hover:text-red-700 font-bold ml-4"
                    >
                      ✕
                    </button>
                  </div>
                </div>
              ))}
              {user.goals.length === 0 && (
                <p className="text-gray-500 text-center py-8">No goals set yet. Create your first skin goal!</p>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default Dashboard;
