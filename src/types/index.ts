export interface User {
  id: string;
  name: string;
  skinType: string;
  morningRoutine: Product[];
  nightRoutine: Product[];
  progressLogs: ProgressLog[];
  goals: SkinGoal[];
  streaks: Streak;
}

export interface Product {
  id: string;
  name: string;
  brand: string;
  category: string;
  notes?: string;
}

export interface ProgressLog {
  id: string;
  date: string;
  acneLevel: number;
  glowLevel: number;
  notes: string;
}

export interface SkinGoal {
  id: string;
  title: string;
  description: string;
  targetDate: string;
  progress: number;
  completed: boolean;
  category: string;
}

export interface Streak {
  morning: number;
  night: number;
  total: number;
  personalBest: number;
}

export interface RoutineCompletion {
  id: string;
  date: string;
  routineType: 'morning' | 'night';
  completed: boolean;
}
