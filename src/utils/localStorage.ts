import { AppData, UserProfile, Product, ProgressLog } from '../types';

const STORAGE_KEY = 'scinkare-data';

// Default demo data for first-time users
const getDefaultData = (): AppData => ({
  userProfile: null,
  morningRoutine: [
    { id: '1', name: 'Gentle Cleanser', brand: 'CeraVe', category: 'Cleanser' },
    { id: '2', name: 'Vitamin C Serum', brand: 'The Ordinary', category: 'Serum' },
    { id: '3', name: 'Moisturizer', brand: 'Neutrogena', category: 'Moisturizer' },
    { id: '4', name: 'Sunscreen SPF 30', brand: 'EltaMD', category: 'Sunscreen' }
  ],
  nightRoutine: [
    { id: '5', name: 'Oil Cleanser', brand: 'DHC', category: 'Cleanser' },
    { id: '6', name: 'Retinol Serum', brand: 'The Ordinary', category: 'Treatment' },
    { id: '7', name: 'Night Cream', brand: 'Olay', category: 'Moisturizer' }
  ],
  progressLogs: [
    {
      id: '1',
      date: new Date().toISOString().split('T')[0],
      acneLevel: 3,
      glowLevel: 7,
      notes: 'Skin feels smooth today, slight redness around nose'
    }
  ]
});

// Load data from localStorage or return default data
export const loadData = (): AppData => {
  try {
    const stored = localStorage.getItem(STORAGE_KEY);
    if (stored) {
      return JSON.parse(stored);
    }
    // Return default data for first-time users
    const defaultData = getDefaultData();
    saveData(defaultData);
    return defaultData;
  } catch (error) {
    console.error('Error loading data from localStorage:', error);
    return getDefaultData();
  }
};

// Save data to localStorage
export const saveData = (data: AppData): void => {
  try {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
  } catch (error) {
    console.error('Error saving data to localStorage:', error);
  }
};

// Update user profile
export const updateUserProfile = (profile: UserProfile): void => {
  const data = loadData();
  data.userProfile = profile;
  saveData(data);
};

// Add product to morning routine
export const addMorningProduct = (product: Product): void => {
  const data = loadData();
  data.morningRoutine.push(product);
  saveData(data);
};

// Add product to night routine
export const addNightProduct = (product: Product): void => {
  const data = loadData();
  data.nightRoutine.push(product);
  saveData(data);
};

// Remove product from morning routine
export const removeMorningProduct = (productId: string): void => {
  const data = loadData();
  data.morningRoutine = data.morningRoutine.filter(p => p.id !== productId);
  saveData(data);
};

// Remove product from night routine
export const removeNightProduct = (productId: string): void => {
  const data = loadData();
  data.nightRoutine = data.nightRoutine.filter(p => p.id !== productId);
  saveData(data);
};

// Add progress log
export const addProgressLog = (log: ProgressLog): void => {
  const data = loadData();
  data.progressLogs.unshift(log); // Add to beginning for newest first
  saveData(data);
};

// Remove progress log
export const removeProgressLog = (logId: string): void => {
  const data = loadData();
  data.progressLogs = data.progressLogs.filter(log => log.id !== logId);
  saveData(data);
};

// Clear all data (for testing)
export const clearAllData = (): void => {
  localStorage.removeItem(STORAGE_KEY);
};
