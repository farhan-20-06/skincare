# ☕ Java Installation Guide - Skincare Tracker

## 🚨 **Java Not Found - Installation Required**

Your system doesn't have Java installed. Here's how to install it and run your Skincare Tracker application.

## 📥 **Step 1: Download and Install Java**

### Option A: Oracle JDK (Recommended)
1. **Visit**: [Oracle JDK Download Page](https://www.oracle.com/java/technologies/downloads/)
2. **Select**: Java 17 or Java 21 (LTS versions)
3. **Choose**: Windows x64 Installer (.exe)
4. **Download** and **run the installer**
5. **Follow** the installation wizard (use default settings)

### Option B: OpenJDK (Free Alternative)
1. **Visit**: [Microsoft OpenJDK](https://docs.microsoft.com/en-us/java/openjdk/download)
2. **Download**: OpenJDK 17 or 21 for Windows
3. **Install** using the MSI installer

### Option C: Using Chocolatey (If you have it)
```powershell
choco install openjdk
```

## 🔧 **Step 2: Verify Installation**

1. **Open a NEW Command Prompt or PowerShell**
2. **Run**:
   ```bash
   java -version
   javac -version
   ```
3. **You should see** something like:
   ```
   java version "17.0.x" 2023-xx-xx LTS
   Java(TM) SE Runtime Environment (build 17.0.x+xx-LTS-xxx)
   Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx-LTS-xxx, mixed mode, sharing)
   ```

## 🚀 **Step 3: Run Your Skincare Tracker**

Once Java is installed:

### Method 1: Using Batch Files (Easiest)
```bash
# Navigate to your project directory
cd C:\Users\raina\skincare\SkincareTracker

# Compile the application
compile.bat

# Run the application
run.bat
```

### Method 2: Manual Commands
```bash
# Navigate to project directory
cd C:\Users\raina\skincare\SkincareTracker

# Compile
javac -d out -sourcepath src/main/java src/main/java/com/skincare/SkincareTrackerApp.java

# Run
java -cp out com.skincare.SkincareTrackerApp
```

## 🎮 **What You'll See**

Once running, you'll see a beautiful console application with:

```
  ███████╗██╗  ██╗██╗███╗   ██╗ ██████╗ █████╗ ██████╗ ███████╗
  ██╔════╝██║ ██╔╝██║████╗  ██║██╔════╝██╔══██╗██╔══██╗██╔════╝
  ███████╗█████╔╝ ██║██╔██╗ ██║██║     ███████║██████╔╝█████╗  
  ╚════██║██╔═██╗ ██║██║╚██╗██║██║     ██╔══██║██╔══██╗██╔══╝  
  ███████║██║  ██╗██║██║ ╚████║╚██████╗██║  ██║██║  ██║███████╗
  ╚══════╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝

            🌟 TRACKER - Your Personal Skincare Journey 🌟

                    Track • Progress • Achieve
```

## 🎯 **Features You Can Use**

### 🏠 **Dashboard**
- Overview of your skincare progress
- Today's routine completion status
- Streak information and goals summary

### 🔥 **Streaks** (Like Duolingo!)
- Complete morning and night routines
- Track consecutive days
- Motivational messages and emojis
- Personal best records

### 🎯 **Goals**
- Set skin improvement goals
- Track progress with visual bars
- Categories: Acne, Hydration, Brightness, Texture
- Target date management

### 📊 **Progress Tracking**
- Daily skin condition logging
- Acne and glow levels (0-10 scale)
- Historical progress viewing
- Notes and observations

## 🔧 **Troubleshooting**

### Problem: "java is not recognized"
**Solution**: 
- Java is not installed or not in PATH
- Install Java using the steps above
- Restart Command Prompt after installation

### Problem: "javac is not recognized"
**Solution**:
- You installed Java Runtime (JRE) instead of Java Development Kit (JDK)
- Download and install JDK (not just JRE)

### Problem: Compilation errors
**Solution**:
- Ensure you're in the correct directory (`SkincareTracker`)
- Check that all `.java` files are present in `src/main/java/com/skincare/`

## 📚 **Why Java for Your OOP Project?**

This Java application demonstrates:
- ✅ **Encapsulation**: Private fields, public methods
- ✅ **Inheritance**: Class hierarchies and interfaces
- ✅ **Polymorphism**: Method overloading and overriding
- ✅ **Abstraction**: Service layers and utility classes
- ✅ **Design Patterns**: Singleton, Factory, Service Layer
- ✅ **File I/O**: Data persistence and serialization
- ✅ **Error Handling**: Exception handling and validation

## 🎓 **Perfect for Academic Submission**

Your professor will see:
- Professional Java code structure
- Proper OOP implementation
- Clean, well-documented code
- Real-world application functionality
- Console-based user interface

## 📞 **Need Help?**

If you encounter issues:
1. **Check Java installation** with `java -version`
2. **Ensure JDK is installed** (not just JRE)
3. **Restart your terminal** after Java installation
4. **Run from the correct directory** (`SkincareTracker`)

---

**Once Java is installed, your skincare tracker will run beautifully! 🌟**

**No more React errors - Pure Java OOP goodness! ☕**
