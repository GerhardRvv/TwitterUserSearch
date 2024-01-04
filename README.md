#  TwitterSearch - Gerhard Reyes Vera Vazquez

## About

## NOTE:
- The app purpose is to search Twitter User by screen name, by using Twitter public API 
- and then display user details and last tweet.

## Features (How to Use)

- At the `User Search Screen`:
- The user should enter the `Twitter's Screen Name` to search for.
- Once the input field is not empty,`Seach` button is enable ans can be used.
- If the app is able to find and existing user, it will open `User Details` screen.
- The screen will contain the user details information and card with that user `Last Tweet` if any.
- At the `User Search` screen, the user can going back will bring the user back to the `Search User` 
- screen.

#### Architecture
- The app is build using MVVM + UseCase + Repository Architecture, using Coroutines to run Retrofit
- to fetch data from BE endpoints located at `ApiTwitterService`, then serialize and mapped it 
- into Data models to extract only the information required by the UI.

#### UI
- The Ui is rendered using Jetpack Compose with the use of a uiState data class to determine 
- the components needed to be displayed.

#### Design System
- Design system components are located at `core >> component`. This directory is meant to be used to
- save reusable composable components.
- Design System theme `TwitterAppTheme` is located at `ui >> theme` this custom theme can be easily access
- through the app to apply pre-selected patters such as `TwitterAppTheme.colors` and
- `TwitterAppTheme.typography`
- Custom theme also "select" the changes to the UI colors to match `Light/Dark Mode`

#### Navigation
- Jetpack Navigation graph handles navigation between different fragments.

#### Event
- SingleLiveEvent used to handling events from user interaction or business logic.

#### Util
- Utils `utils` directory, contain utility classes and testing data objects.
- Extension utilities `UtilExtensions` class with functions to simplify and reuse value transformations.

#### Unit Test
- Unit test are created for `repositories`

  +--------------------------------------------------------+
  |                       View Layer                       |
  |                                                        |
  |                 +------------------------+             |
  |                 |    Activity/Fragment   |             |
  |                 +------------------------+             |
  |                 |    ViewModel instance  |             |
  |                 +------------------------+             |
  |                 |    LiveData instances  |             |
  |                 +------------------------+             |
  |                                                        |
  +--------------------------------|-----------------------+
  |
  |
  +--------------------------------|-------------------------------+
  |                 |                              |               |
  |          ViewModel Layer               Repository Layer        |
  |                 |                              |               |
  |    +------------------------+      +------------------------+  |
  |    |         ViewModel      |      |        Repository      |  |
  |    +------------------------+      +------------------------+  |
  |    |          Model         |      |         Model          |  |
  |    +------------------------+      +------------------------+  |
  |    |   Repository instances |      |    Remote Data Source  |  |
  |    +------------------------+      +------------------------+  |
  |                 ^                               ^              |
  |                 |                               |              |                         
  +-----------------|-------------------------------|--------------+
  |                 |                               |              |
  |            Model Layer                   Data Source Layer     |
  |                 |                               |              |
  |    +------------------------+      +------------------------+  |
  |    |          Model         |      |   Remote Data Source   |  |
  |    +------------------------+      +------------------------+  |
  |                                                                |
  +----------------------------------------------------------------+

# Build

- The app contains the usual `debug` and `release` build variants.
- Select `debug` to be able to launch into an emulator or test device.
