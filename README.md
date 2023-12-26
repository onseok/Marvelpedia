<h1 align="center">Marvelpedia</h1>

<p align="center">
<img src="https://github.com/onseok/Marvelpedia/assets/76798309/0084602c-1e8d-4daa-b59b-14def0a9ef6a"/>
</p>

<p align="center">  
🌟 Marvelpedia is an intricately designed application that stands on the cutting-edge of modern Android development practices. 
</p>
</br>

- **`MVVM (Model-View-ViewModel`)**: This project is structured around the MVVM architectural pattern, ensuring a clean separation of concerns, which improves the maintainability and scalability of the application.

- **`Multi-Module Architecture`**: Marvelpedia is organized into multiple modules, making the codebase more manageable and modular. This structure aids in better separation of features and functionalities, enhancing the overall development process.

- **`Clean Architecture`**: The overall architecture is based on Clean Architecture principles, promoting a more structured and decoupled approach to app development. This results in a codebase that's easier to test, maintain, and scale.

- **`Jetpack Compose`**: The UI components are built using Jetpack Compose, Google's modern toolkit for building native UI. This allows for a more flexible, concise, and reactive way to create user interfaces in Kotlin.

- **`Hilt` for `Dependency Injection`**: Hilt is used for dependency injection, simplifying the way dependencies are managed and injected into the application. This leads to more testable and maintainable code.

- **`Coil` for `Image Loading`**: For efficient and effortless image loading and caching, Coil, a Kotlin-based image loading library, is integrated. It's optimized for Jetpack Compose, making image handling smooth and responsive.

- **`Coroutines & Flow`**: Marvelpedia utilizes Kotlin Coroutines and Flow for managing asynchronous tasks and data streams, enhancing the app's performance and responsiveness.

- **`Room`**: Room, a component of Android Jetpack, is employed for database management. It provides a layer of abstraction over SQLite, making database access and data management smoother and more efficient.

## 🚀 Project Setup Guide
Obtain your unique API Key from [Marvel's developer site](https://developer.marvel.com/).

In your `local.properties`, ensure to store the base URL along with your keys (both public and private). 

Format it like this:
```
MARVEL_API_ENDPOINT = "https://gateway.marvel.com:443/v1/public/"
YOUR_MARVEL_PUBLIC_KEY = "INSERT_YOUR_PUBLIC_KEY_HERE"
YOUR_MARVEL_PRIVATE_KEY = "INSERT_YOUR_PRIVATE_KEY_HERE"
```
