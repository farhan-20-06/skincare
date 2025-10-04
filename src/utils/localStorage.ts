import { User, Product, ProgressLog, SkinGoal, Streak } from '../types';

const STORAGE_KEY = 'scinkare_user_data';

// Demo data for first-time users
const getDemoData = (): User => ({
  id: 'demo-user',
  name: 'Demo User',
  skinType: 'Combination',
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
      notes: 'Skin feels smooth today, slight redness around nose area'
    }
  ],
  goals: [
    {
      id: '1',
      title: 'Reduce Acne',
      description: 'Clear up breakouts and prevent new ones',
      targetDate: new Date(Date.now() + 30 * 24 * 60 * 60 * 1000).toISOString().split('T')[0],
      progress: 60,
      completed: false,
      category: 'Acne'
    }
  ],
  streaks: {
    morning: 5,
    night: 3,
    total: 8,
    personalBest: 12
  }
});

export const saveUserData = (userData: User): void => {
  try {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(userData));
  } catch (error) {
    console.error('Error saving user data:', error);
  }
};

export const loadUserData = (): User | null => {
  try {
    const data = localStorage.getItem(STORAGE_KEY);
    if (data) {
      return JSON.parse(data);
    }
    return null;
  } catch (error) {
    console.error('Error loading user data:', error);
    return null;
  }
};

export const getOrCreateUserData = (): User => {
  const existingData = loadUserData();
  if (existingData) {
    return existingData;
  }
  
  const demoData = getDemoData();
  saveUserData(demoData);
  return demoData;
};

export const clearUserData = (): void => {
  localStorage.removeItem(STORAGE_KEY);
};

export const exportUserData = (): string => {
  const userData = loadUserData();
  if (!userData) {
    throw new Error('No user data found');
  }
  
  return JSON.stringify(userData, null, 2);
};

export const importUserData = (jsonData: string): User => {
  try {
    const userData = JSON.parse(jsonData);
    saveUserData(userData);
    return userData;
  } catch (error) {
    throw new Error('Invalid JSON data');
  }
};
