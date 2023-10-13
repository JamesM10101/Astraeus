# Astraeus: Explore the Cosmos 🌌

**Astraeus** is an Android app that allows space enthusiasts to explore captivating celestial imagery and stay updated with the latest discoveries. Whether you're interested in stunning astronomy photos, Mars rover snapshots, or satellite imagery, Astraeus has you covered.

## Features

1. **Astronomy Picture of the Day (APOD)**:
   - Discover a new awe-inspiring astronomy photo every day.
   - Read detailed descriptions and learn about the universe.

2. **Mars Rover Photos**:
   - Access high-resolution images captured by NASA's Mars rovers.
   - Explore the Red Planet's surface and geological features.

3. **NASA Image and Video Library**:
   - Search through a vast collection of NASA images.

4. **E.P.I.C. Satellite Imagery**:
   - View Earth from space using imagery from NASA's Earth Polychromatic Imaging Camera (E.P.I.C.).
   - Witness breathtaking views of our planet.

5. **Favorites and Downloads**:
   - Save your favorite photos to revisit later.
   - Download images for offline viewing or sharing.

## Screenshots

<table>
  <tr>
    <td valign="top">
      <img src="https://imgur.com/VuIdpdL.jpg" />
    </td>
    <td valign="top">
      <img src="https://imgur.com/jVm3Pfi.jpg" />
    </td>
    <td valign="top">
      <img src="https://imgur.com/GRyvMZk.jpg" />
    </td>
  </tr>
</table>

[View All Images](https://imgur.com/a/66xP81p)

## Installation

1. Download the Astraeus APK from the [releases page](https://github.com/JamesM10101/Astraeus/releases).
2. Enable intallation from unknown sources in your device settings.
3. Install the APK on your Android device.
4. Open Astraeus and start exploring the cosmos!

## Tech Stack

- Kotlin
- XML
- Material Design
- Coil (Image loading)
- Retrofit (HTTP requests)
- Moshi (JSON parsing library)
- Room (Local storage)
- Testing with JUnit and Espresso

## Permissions

Astraeus requires the following permissions:

- Internet access: To fetch data from NASA APIs.
- Storage access: To save downloaded images to your device.
## Run Locally

1. **Clone the Repository**:
   - First, clone the Astraeus repository to your local machine using Git:
     
     ```
     git clone https://github.com/JamesM10101/Astraeus.git
     ```
2. **Add Your NASA API Key**:
   - Obtain an API key from NASA by following these steps:
     1. Visit the [NASA API Portal](https://api.nasa.gov/).
     2. Sign up for an account (if you haven't already).
     3. Create a new application and generate an API key.

3. **Build and Run**:
   - Open the project in Android Studio.
   - Take your NASA API key and add it to the `local.properties` file in the project root directory:
     
     ```
     NASA_API_KEY="your-api-key-here"
     ```
   - Build and Run the app on an emulator or a physical device.
     
## Credits

- APOD data provided by NASA's [Astronomy Picture of the Day API](https://api.nasa.gov/planetary/apod).
- Mars rover photos sourced from NASA's [Mars Rover Photos API](https://api.nasa.gov/mars-photos/).
- Image and video library content from NASA's [Media Library](https://images.nasa.gov/).
- E.P.I.C. imagery is courtesy of [NOAA/NASA](https://epic.gsfc.nasa.gov/).

## Licenses

- **Kotlin**: Language for Android development. ([Apache License 2.0](https://kotlinlang.org/))
- **Navigation Components**: Android Jetpack library for navigation. ([Apache License 2.0](https://github.com/androidx/navigation))
- **Material Components**: UI components following Material Design guidelines. ([Apache License 2.0](https://github.com/material-components/material-components-android))
- **Coil** (Image Loading): Efficient image loading library. ([Apache License 2.0](https://github.com/coil-kt/coil))
- **Retrofit** (HTTP Requests): Type-safe HTTP client for Android. ([Apache License 2.0](https://github.com/square/retrofit))
- **Room** (Local Storage): Android architecture component for local data storage. ([Apache License 2.0](https://developer.android.com/training/data-storage/room))
- **TouchImageView** (Interactable Images): Adds pinch zoom, dragging, fling, double tap zoom functionality and other animation polish to images. ([MIT](https://github.com/MikeOrtiz/TouchImageView))
- **Android Youtube Player** (YouTube Media Player): A stable and customizable open source YouTube player for Android. ([MIT](https://github.com/PierfrancescoSoffritti/android-youtube-player))
- **JUnit** (Testing): Testing framework for Java and Kotlin. ([Eclipse Public License 1.0](https://junit.org/junit5/))
- **Espresso** (UI Testing): UI testing framework for Android. ([Apache License 2.0](https://developer.android.com/training/testing/espresso))
## Feedback and Support

Feedback is welcome! If you encounter any issues or have suggestions for improvement, please feel free to open an issue on the [GitHub repository](https://github.com/JamesM10101/Astraeus/issues).

---

*Note: Astraeus is not affiliated with NASA but aims to bring the wonders of space exploration to your fingertips.*

*Note: The images used in Astraeus are obtained from various NASA services and are not owned by us. Astraeus does not claim any rights or ownership over these images. All credits and attributions go to the original authors and sources. Please refer to the [NASA Media Usage Guidelines](https://www.nasa.gov/multimedia/guidelines/index.html) for more information.*
