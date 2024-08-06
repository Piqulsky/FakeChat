<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Piqulsky/FakeChat">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">FakeChat</h3>

  <p align="center">
    Programmable pseudo chat application to use without concern about rights
    <br />
    <a href="https://github.com/Piqulsky/FakeChat"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Piqulsky/FakeChat">View Demo</a>
    ·
    <a href="https://github.com/Piqulsky/FakeChat/issues">Report Bug</a>
    ·
    <a href="https://github.com/Piqulsky/FakeChat/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

**FakeChat** is an open-source Android mobile application designed to provide a programmable pseudo-chat experience. The primary function of FakeChat is to simulate a typical mobile messaging application, making it an invaluable tool for filmmakers, content creators, and digital storytellers who need a realistic chat interface in their work.

The application allows users to create and customize chat conversations, giving them full control over the content and appearance of the messages. This feature is particularly useful for producing movies, videos, or other content where a realistic-looking chat exchange is needed without the hassle of dealing with licensing or rights issues.

**Key Features:**
- **Imitation of Real Chat Interfaces:** FakeChat closely resembles popular mobile communicators, offering a variety of customization options to mimic real-life messaging apps.
- **Programmable Chats:** Users can pre-program artificial conversations, creating detailed and specific chat sequences. This feature allows complete scripting of dialogues, ensuring that the desired narrative is followed precisely.
- **Delayed Messages:** FakeChat supports the addition of delayed messages, enabling the simulation of real-time conversations. This functionality is perfect for scenarios where timing is crucial, allowing for a more authentic portrayal of interactions.
- **Visual Customization:** The app offers extensive customization options, giving users full control over visual aspects. Users can change color motifs, modify avatars, and even use custom photos for chat backgrounds. These options help in creating a distinct and personalized chat environment that fits the context of the project.
- **Open Source and Free to Use:** As an open-source project, FakeChat is freely available to all. Users can modify and adapt the software to fit their specific needs, promoting creativity and innovation without financial barriers.

Whether you're an amateur filmmaker, a YouTube content creator, or a beginner looking to experiment with pseudo-chat interfaces, FakeChat provides a versatile and practical solution for your creative projects. Its open-source nature encourages collaboration and continuous improvement, making it a community-driven tool for digital creators everywhere.

Feel free to explore the code, contribute, and customize the application to suit your unique requirements. With FakeChat, bring your creative visions to life with ease and without restrictions.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

![Android Studio](https://img.shields.io/badge/android%20studio-346ac1?style=for-the-badge&logo=android%20studio&logoColor=white)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Installation

To install FakeChat on your Android device, follow these steps to download the `.apk` file from the GitHub project page:

1. **Visit the GitHub Releases Page**
   - Go to the [FakeChat GitHub repository](https://github.com/Piqulsky/FakeChat).
   - Navigate to the "Releases" section, which can be found under the repository name or by clicking [here](https://github.com/Piqulsky/FakeChat/releases).

2. **Download the Latest Release**
   - On the Releases page, find the latest release of FakeChat.
   - Under the "Assets" section, locate the `.apk` file (e.g., `Chat.apk`).
   - Click on the `.apk` file to start the download.

3. **Enable Installation from Unknown Sources**
   - Before installing the downloaded `.apk` file, you need to enable installation from unknown sources on your Android device.
   - Go to `Settings` > `Security` (or `Privacy` on some devices) > and toggle on `Install unknown apps` or `Unknown sources`.
   - Depending on your device and Android version, this setting might be located under a different menu. Please check your device's documentation if needed.

4. **Install the Application**
   - Once the download is complete, open the downloaded `.apk` file.
   - You may receive a prompt asking for permission to install the app. Confirm and proceed with the installation.

5. **Launch FakeChat**
   - After the installation is complete, you can open FakeChat from your app drawer.
   - You are now ready to start creating programmable pseudo-chats!

**Note:** Always ensure you download `.apk` files from trusted sources. The FakeChat GitHub repository is the official source for downloading the latest and most secure version of the application. However if you get a warning when installing FakeChat, I am sincerely sorry for that, since I have not found time to ensure that the app is treated by Android as a safe app.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

To get a local copy of the FakeChat project up and running on your machine, follow these steps.

### Prerequisites

Before you begin, ensure you have the following software installed on your development machine:

1. **Java Development Kit (JDK)**
   - FakeChat is developed in Java, so you need to have the JDK installed. Download and install the latest version of JDK from [Oracle's official site](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use a version management tool like SDKMAN to manage different JDK versions.

2. **Android Studio**
   - Android Studio is the official Integrated Development Environment (IDE) for Android development. You can download it from [the official Android Developer website](https://developer.android.com/studio).

3. **Git**
   - Git is a version control system required for cloning the project repository. You can download Git from [the official Git website](https://git-scm.com/downloads).

### Process

1. **Clone the Repository**
   - First, clone the FakeChat repository from GitHub to your local machine. Open a terminal and run the following command:
     ```sh
     git clone https://github.com/Piqulsky/FakeChat.git
     ```
   - This will create a directory named `FakeChat` with all the project files.

2. **Open the Project in Android Studio**
   - Launch Android Studio.
   - Select "Open an existing Android Studio project" from the welcome screen or go to `File` > `Open` if you're already inside Android Studio.
   - Navigate to the cloned `FakeChat` directory and select it. Android Studio will begin to import the project.

3. **Sync the Project with Gradle**
   - Once the project is opened, Android Studio will automatically sync it with Gradle. If it doesn't start automatically, go to `File` > `Sync Project with Gradle Files`.
   - This process downloads all the necessary dependencies specified in the `build.gradle` files.

4. **Set Up an Android Virtual Device (AVD)**
   - If you don't have a physical device to test the application, set up an Android Virtual Device (AVD) by going to `Tools` > `AVD Manager` and following the prompts to create a new virtual device.

5. **Run the Application**
   - To run the FakeChat application, click the green "Run" button (or press `Shift + F10`). Select the device you want to run the app on (either a connected physical device or an AVD).
   - Android Studio will build the project and launch the application on the selected device.

Now you're ready to explore the codebase, make modifications, and contribute to the FakeChat project.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- USAGE EXAMPLES -->
## Usage

WIP

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [ ] WIP

See the [open issues](https://github.com/Piqulsky/FakeChat/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Piqulsky - [https://linktr.ee/piqulsky](https://linktr.ee/piqulsky) - piqulsky@gmail.com

Project Link: [https://github.com/Piqulsky/FakeChat](https://github.com/Piqulsky/FakeChat)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [othneildrew's Best-README-Template](https://github.com/othneildrew/Best-README-Template)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Piqulsky/FakeChat.svg?style=for-the-badge
[contributors-url]: https://github.com/Piqulsky/FakeChat/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Piqulsky/FakeChat.svg?style=for-the-badge
[forks-url]: https://github.com/Piqulsky/FakeChat/network/members
[stars-shield]: https://img.shields.io/github/stars/Piqulsky/FakeChat.svg?style=for-the-badge
[stars-url]: https://github.com/Piqulsky/FakeChat/stargazers
[issues-shield]: https://img.shields.io/github/issues/Piqulsky/FakeChat.svg?style=for-the-badge
[issues-url]: https://github.com/Piqulsky/FakeChat/issues
[license-shield]: https://img.shields.io/github/license/Piqulsky/FakeChat.svg?style=for-the-badge
[license-url]: https://github.com/Piqulsky/FakeChat/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png
