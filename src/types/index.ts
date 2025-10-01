// Type definitions for Scinkare app

export interface UserProfile {
  name: string;
  skinType: string;
}

export interface Product {
  id: string;
  name: string;
  brand?: string;
  category?: string;
}

export interface ProgressLog {
  id: string;
  date: string;
  acneLevel: number; // 0-10
  glowLevel: number; // 0-10
  notes: string;
}

export interface AppData {
  userProfile: UserProfile | null;
  morningRoutine: Product[];
  nightRoutine: Product[];
  progressLogs: ProgressLog[];
}

export interface AlertMessage {
  id: string;
  type: 'success' | 'error' | 'info';
  message: string;
}
