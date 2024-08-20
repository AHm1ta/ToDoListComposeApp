# TodoList App

This is a simple TodoList Android application built using modern Android development tools and libraries. The app leverages Jetpack Compose for the UI, Room for local database storage, and Kotlin as the main programming language.

## Features

- **Add Tasks**: Easily add new tasks to your todo list.
- **Delete Tasks**: Remove tasks from the list when they are no longer needed.
- **Update Tasks**: Edit existing tasks to change their title, description, or status.
- **Live Data Binding**: Automatically updates the UI when the data changes.

## Technology Stack

- **Kotlin 1.9.0**: The main programming language used for Android development.
- **Jetpack Compose**: Modern UI toolkit for building declarative UIs in Android.
- **Room 2.6.1**: Persistence library for local storage with SQLite.
- **Type Converters**: For storing custom data types in Room (e.g., dates).
- **ViewModel & LiveData**: For managing UI-related data in a lifecycle-conscious way.

## Architecture

This app follows the MVVM (Model-View-ViewModel) architecture, ensuring separation of concerns and easier maintenance.

- **Model**: Represents the data layer, using Room as the local database.
- **View**: The UI layer is built with Jetpack Compose.
- **ViewModel**: Provides data to the UI and handles logic for user interactions.

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later.
- Kotlin 1.9.0 or higher.
- Gradle 7.5 or higher.

### Installation

1. Clone the repository:
    bash
    git clone https://github.com/AHm1ta/ToDoListComposeApp.git
    
2. Open the project in Android Studio.
3. Build the project and run the app on your device or emulator.

## How to Use

### Add a Task:
- Enter the task title.
- Click "Add" to add the task to your list with the created time.

### Update a Task:
- Click on a task to open the edit button.
- Modify the task details and save changes.

### Delete a Task:
- Click the delete icon to remove it from the list.

### Future Enhancements
- Implement task categorization with labels.
- Add reminder notifications.
- Enable task prioritization (e.g., high, medium, low).

### License
This project is licensed under the MIT License - see the LICENSE file for details.
