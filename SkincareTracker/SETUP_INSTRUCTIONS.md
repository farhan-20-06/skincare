# 🚀 Setup Instructions - Skincare Tracker Java Application

## 📋 Prerequisites

Before running the Skincare Tracker application, ensure you have:

### ✅ Java Development Kit (JDK)
- **Version**: Java 11 or higher
- **Download**: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **Verify Installation**: Open Command Prompt/Terminal and run:
  ```bash
  java -version
  javac -version
  ```

## 🛠️ Installation Methods

### Method 1: Using Batch Files (Windows - Recommended)

1. **Navigate to the project directory**:
   ```bash
   cd SkincareTracker
   ```

2. **Compile the application**:
   ```bash
   compile.bat
   ```

3. **Run the application**:
   ```bash
   run.bat
   ```

### Method 2: Manual Compilation (All Platforms)

1. **Open Command Prompt/Terminal in the SkincareTracker directory**

2. **Create output directory**:
   ```bash
   mkdir out
   ```

3. **Compile all Java files**:
   ```bash
   javac -d out -sourcepath src/main/java src/main/java/com/skincare/SkincareTrackerApp.java
   ```

4. **Run the application**:
   ```bash
   java -cp out com.skincare.SkincareTrackerApp
   ```

### Method 3: Using IDE (IntelliJ IDEA, Eclipse, VS Code)

1. **Open your Java IDE**
2. **Import/Open the SkincareTracker folder as a project**
3. **Set `src/main/java` as the source directory**
4. **Run `SkincareTrackerApp.java`**

## 🎮 First Time Usage

### 1. Profile Setup
- Enter your name
- Select your skin type (Normal, Dry, Oily, Combination, Sensitive)

### 2. Explore Features
- **Dashboard**: Overview of your progress
- **Streaks**: Track daily routine completions
- **Goals**: Set and monitor skin improvement goals
- **Progress**: Log daily skin condition

## 📁 Project Structure

```
SkincareTracker/
├── src/main/java/com/skincare/
│   ├── models/              # Data models
│   ├── services/            # Business logic
│   ├── utils/               # Utility classes
│   ├── ui/                  # User interface
│   └── SkincareTrackerApp.java  # Main class
├── out/                     # Compiled classes (created after compilation)
├── compile.bat              # Windows compilation script
├── run.bat                  # Windows run script
├── README.md                # Project documentation
└── SETUP_INSTRUCTIONS.md    # This file
```

## 💾 Data Storage

The application creates these files automatically:
- `skincare_data.ser` - Main data file
- `skincare_data_backup.ser` - Backup data file

**Note**: These files are created in the same directory as the application.

## 🔧 Troubleshooting

### Problem: "javac is not recognized"
**Solution**: Java JDK is not installed or not in PATH
- Install Java JDK
- Add Java bin directory to system PATH
- Restart Command Prompt/Terminal

### Problem: "java.lang.ClassNotFoundException"
**Solution**: Incorrect classpath
- Ensure you're running from the correct directory
- Use the exact command: `java -cp out com.skincare.SkincareTrackerApp`

### Problem: Application won't start
**Solution**: 
- Check if compilation was successful
- Verify all .class files exist in the `out` directory
- Try recompiling with `compile.bat`

### Problem: Data not saving
**Solution**:
- Check file permissions in the application directory
- Ensure the directory is writable
- Try running as administrator (Windows) or with sudo (Linux/Mac)

## 🎯 Features Overview

### 🔥 Streak System
- Complete morning and night routines
- Track consecutive days
- Motivational messages and emojis
- Personal best records

### 🎯 Goal Management
- Set specific skin improvement goals
- Track progress with visual indicators
- Multiple categories (Acne, Hydration, Brightness, etc.)
- Target date management

### 📊 Progress Tracking
- Daily skin condition logging
- Acne and glow level tracking (0-10 scale)
- Historical progress viewing
- Notes and observations

### 💾 Data Management
- Automatic data saving
- Backup and restore functionality
- Export to text files
- Data persistence between sessions

## 🎨 Console Interface

The application features:
- **Colorized output** for better readability
- **Menu-driven navigation** for easy use
- **Progress bars** for visual feedback
- **Input validation** for data integrity
- **Table formatting** for organized display

## 📚 Educational Value

This project demonstrates:
- **Object-Oriented Programming** principles
- **Design Patterns** (Singleton, Service Layer, etc.)
- **File I/O** and data persistence
- **Error handling** and validation
- **Console application** development
- **Clean code** practices

## 🤝 Support

If you encounter any issues:
1. Check this troubleshooting guide
2. Verify Java installation
3. Ensure proper compilation
4. Check file permissions

## 🎓 Academic Use

This project is designed for Object-Oriented Programming coursework and demonstrates:
- Class design and relationships
- Encapsulation and data hiding
- Inheritance and polymorphism
- File handling and serialization
- User interface design
- Software architecture patterns

---

**Happy Coding! 🌟**
