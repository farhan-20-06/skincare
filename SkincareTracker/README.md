# 🌟 Skincare Tracker - Java OOP Project

A comprehensive skincare tracking application built with Java, demonstrating Object-Oriented Programming principles for academic purposes.

## 📋 Project Overview

This Java application provides the same functionality as the React-based skincare tracker but implemented using core OOP concepts. It features a console-based interface for tracking skincare routines, managing goals, and monitoring progress.

## 🎯 Features

### ✨ Core Functionality
- **User Profile Management** - Personal information and skin type tracking
- **Routine Tracking** - Morning and night skincare routines
- **Progress Logging** - Daily skin condition tracking with acne and glow levels
- **Streak System** - Duolingo-style streak tracking for consistency motivation
- **Goal Management** - Set and track specific skin improvement goals
- **Data Persistence** - File-based data storage using Java serialization

### 🔥 Streak System
- Track daily completion of morning and night routines
- Visual progress indicators and motivational messages
- Personal best tracking and consistency scoring
- Weekly progress overview

### 🎯 Goal Management
- Multiple goal categories (Acne, Hydration, Brightness, Texture, Custom)
- Progress tracking with visual indicators
- Target date management and overdue notifications
- Automatic completion detection

## 🏗️ Object-Oriented Design

### 📦 Package Structure
```
com.skincare/
├── models/           # Data models (User, Product, Goal, Streak, etc.)
├── services/         # Business logic (StreakService, GoalService)
├── utils/            # Utility classes (DataManager, DateUtils, IdGenerator)
├── ui/               # User interface (ConsoleUI)
└── SkincareTrackerApp.java  # Main application class
```

### 🎨 OOP Principles Demonstrated

#### 1. **Encapsulation**
- Private fields with public getters/setters
- Data validation in setter methods
- Defensive copying for collections

#### 2. **Inheritance**
- All model classes implement `Serializable`
- Common interface patterns

#### 3. **Polymorphism**
- Method overloading in utility classes
- Interface implementations

#### 4. **Abstraction**
- Service layer abstracts business logic
- Utility classes hide implementation details

#### 5. **Design Patterns**
- **Singleton Pattern**: `DataManager` class
- **Service Layer Pattern**: Business logic separation
- **Factory Pattern**: ID generation utilities

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation & Running

1. **Clone/Download the project**
   ```bash
   cd SkincareTracker
   ```

2. **Compile the Java files**
   ```bash
   javac -d out -sourcepath src/main/java src/main/java/com/skincare/SkincareTrackerApp.java
   ```

3. **Run the application**
   ```bash
   java -cp out com.skincare.SkincareTrackerApp
   ```

### Alternative: Using IDE
1. Open the `SkincareTracker` folder in your Java IDE
2. Set `src/main/java` as the source folder
3. Run `SkincareTrackerApp.java`

## 📱 Usage Guide

### First Time Setup
1. Launch the application
2. Enter your name and select skin type
3. Start tracking your routines!

### Main Features

#### 🏠 Dashboard
- Overview of today's completion status
- Current streak information
- Goals summary and recent progress

#### 🔥 Streaks
- Complete morning/night routines
- View streak statistics
- Track weekly progress
- Undo completions if needed

#### 🎯 Goals
- Create new skin goals with categories
- Update progress regularly
- View active, completed, and overdue goals
- Track progress with visual indicators

#### 📊 Progress Logging
- Log daily skin condition (acne/glow levels)
- Add notes about skin observations
- View historical progress data

## 💾 Data Storage

The application uses Java serialization to persist data:
- **Main Data File**: `skincare_data.ser`
- **Backup File**: `skincare_data_backup.ser`
- **Export Feature**: Text file export for data portability

## 🎨 Console Interface Features

- **Colorized Output**: ANSI color codes for better readability
- **Progress Bars**: Visual progress indicators
- **Table Formatting**: Organized data display
- **Input Validation**: Robust user input handling
- **Menu Navigation**: Intuitive menu-driven interface

## 🧪 Key Classes Overview

### Models
- **`User`**: Main user data container with routines and progress
- **`Product`**: Skincare product information
- **`SkinGoal`**: Goal tracking with categories and progress
- **`Streak`**: Streak calculation and motivation system
- **`ProgressLog`**: Daily skin condition logging
- **`RoutineCompletion`**: Individual routine completion records

### Services
- **`StreakService`**: Handles all streak-related business logic
- **`GoalService`**: Manages goal creation, updates, and tracking

### Utilities
- **`DataManager`**: Singleton for data persistence and management
- **`DateUtils`**: Date manipulation and formatting utilities
- **`IdGenerator`**: Unique ID generation for entities
- **`ConsoleUI`**: Console interface utilities and formatting

## 🎓 Educational Value

This project demonstrates:
- **Clean Code Principles**: Readable, maintainable code structure
- **SOLID Principles**: Single responsibility, open/closed, etc.
- **Error Handling**: Robust exception handling and validation
- **File I/O**: Serialization and file management
- **Data Structures**: Lists, streams, and collections usage
- **Date/Time Handling**: Modern Java time API usage
- **Console Applications**: User interaction and menu systems

## 🔧 Extensibility

The modular design allows for easy extensions:
- Add new goal categories
- Implement different streak algorithms
- Add new data export formats
- Integrate with external APIs
- Add notification systems

## 📈 Future Enhancements

Potential improvements for advanced OOP concepts:
- **Observer Pattern**: For real-time updates
- **Strategy Pattern**: Different streak calculation strategies
- **Command Pattern**: Undo/redo functionality
- **Builder Pattern**: Complex object creation
- **Decorator Pattern**: Enhanced UI features

## 🤝 Contributing

This is an educational project. Feel free to:
- Add new features
- Improve the UI
- Optimize performance
- Add unit tests
- Enhance documentation

## 📄 License

This project is created for educational purposes. Feel free to use and modify for learning.

---

**Created for Object-Oriented Programming Course**  
*Demonstrating Java OOP principles through a practical application*

🌟 **Happy Coding and Happy Skincare!** 🌟
