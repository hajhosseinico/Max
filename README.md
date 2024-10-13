# Max App

**Max App** is a high-performance Android application built with a clean architecture approach. It leverages **Uncle Bob's Clean Code principles**, **MVI** and **MVVM** architectural patterns, and is crafted to deliver a smooth and responsive user experience. This app uses **Jetpack Compose** for UI development and **Hilt** for dependency injection, ensuring robust and scalable application architecture.

## 🚀 Features

- **Clean Architecture**: Separation of concerns using Uncle Bob's Clean Code principles.
- **MVVM and MVI**: The app adopts both Model-View-ViewModel (MVVM) and Model-View-Intent (MVI) architectures to handle data flow and UI states effectively.
- **Jetpack Compose UI**: Modern declarative UI with Jetpack Compose.
- **Hilt Dependency Injection**: Manage dependencies effortlessly using Hilt.
- **Room Database**: Local data persistence with Room for seamless offline support.
- **Retrofit**: Robust networking with Retrofit for API interactions.
- **Kotlin Coroutines**: Asynchronous programming made easy with Kotlin Coroutines.
- **Comprehensive Testing**: Includes **Unit Tests**, **Instrumented Tests**, and **UI Tests** using JUnit, MockK, and Jetpack Compose Testing Libraries.

## 📱 Technologies and Libraries

### Core Libraries

- **AndroidX Core KTX**
- **AndroidX AppCompat**
- **Material Design Components**
- **Navigation Component**
  
### Architecture Components

- **MVVM (Model-View-ViewModel)**
- **MVI (Model-View-Intent)**
- **Hilt for Dependency Injection**
  
### UI

- **Jetpack Compose**: Build UI components declaratively.
- **Material Design 3**: Consistent and modern UI elements.
- **Compose Navigation**: Navigate seamlessly between screens.
- **Coil**: Image loading with Jetpack Compose integration.

### Networking

- **Retrofit**: HTTP client for networking.
- **Kotlinx Serialization**: JSON parsing and serialization.
- **OkHttp Logging Interceptor**: Intercept and log network requests.
  
### Persistence

- **Room Database**: Local storage for offline support.

### Dependency Injection

- **Hilt**: Simplifies DI in Android applications.

### Testing

- **JUnit**: Unit testing framework.
- **MockK**: Mocking library for Kotlin.
- **Jetpack Compose Testing Library**: UI testing with Jetpack Compose.
- **AndroidX Test (JUnit, Rules, Core)**: Instrumented testing tools.
- **Kotlinx Coroutines Test**: Test coroutine-based code effectively.

## 🧪 Testing Approach

This project embraces **test-driven development (TDD)** with a focus on ensuring code reliability and robustness. The testing strategy covers:

- **Unit Tests**: Validate individual units of logic, especially ViewModel and UseCase layers.
- **Instrumented Tests**: Test the app on physical or virtual devices to verify that it behaves as expected.
- **UI Tests**: Ensure UI components render correctly and respond to user interactions.

## 🛠️ Build Setup

### Clone the Repository

```bash
git clone https://github.com/hajhosseinico/Max.git
cd Max
```

### Setup Dependencies

Open the project in Android Studio and allow it to sync and download dependencies automatically.

### Build and Run

Select a device or emulator, and click on the **Run** button in Android Studio.

## 🧩 Project Architecture

The app is built using the **Clean Architecture** principles to keep code maintainable, scalable, and testable. Here’s a brief overview of the layers:

1. **Presentation Layer**: Contains UI components built with Jetpack Compose, following **MVVM** to handle ViewModel interactions and state management.
2. **Domain Layer**: Contains UseCases to encapsulate business logic, keeping the core functionalities isolated from UI and data operations.
3. **Data Layer**: Responsible for data handling and communication with both **Room Database** and **Retrofit API services**.

## 📚 Libraries and Dependencies

- **Jetpack Compose** for UI and navigation.
- **Room** for local data storage.
- **Retrofit & OkHttp** for networking.
- **Coil** for image loading.
- **Hilt** for Dependency Injection.
- **JUnit**, **MockK**, **Compose Testing Library**, and **Kotlinx Coroutines Test** for testing.
  
The dependencies in the `build.gradle` file provide full details on each library version used.

## 👨‍💻 Contribution

Contributions are welcome! Feel free to open an issue or submit a pull request. Ensure that your code adheres to the project's coding style and passes all tests.

## 📄 License

Use the library in any way you like! This project is created to help others and improve my skills.

## 📬 Contact

Feel free to reach out with any questions or suggestions!

- **Author**: Ali Hajhosseini
- **Email**: [hajhosseinico@gmail.com](mailto:hajhosseinico@gmail.com)
- **GitHub**: [hajhosseinico](https://github.com/hajhosseinico)
