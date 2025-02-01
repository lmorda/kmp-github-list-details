# kmp-github-list-details

This project serves as a starter template for a typical list/details KMP Multiplatform project.  The project utilizes the latest Kotlin libraries including Compose Multiplatform,
Ktor, Coil, and Koin.

> The project is based off of [Kotlin Multiplatform app template](https://github.com/kotlin/KMP-App-Template).

## Project Overview

The app fetches a list of GitHub repositories from the [GitHub API](https://docs.github.com/en/rest) and displays them in a list. When a repository is tapped, the app navigates to a detailed view showing
additional information about the selected repository.  Paging is supported by swiping up on the list page.

## Libraries

- [Compose Multiplatform](https://jb.gg/compose) for UI
- [Compose Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)
- [Ktor](https://ktor.io/) for networking
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON handling
- [Coil](https://github.com/coil-kt/coil) for image loading
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection

## Setup

### Prerequisites
- Android Studio Ladybug or newer

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/lmorda/android-homework-problem.git
   ```
2. Open the project in Android Studio
3. Sync Gradle
4. Run the app on an emulator or a physical device

## Asset Acknowledgement

Thanks for the app icon!

https://www.flaticon.com/authors/agung-rama

## License

This project is licensed under the Apache License. See the [LICENSE](LICENSE) file for details.
