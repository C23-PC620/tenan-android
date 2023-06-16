<img src="galleries/tenan_logo.png"/>

# Tenan Android

Tenan is an Android application that help user to find their favorite tourism place and accomodation around that place.

This project is created to fulfill the final project at Bangkit Academy.

## Features
For the current development phase Tenan Android have several feature.

### Explore
The [Explore Feature](app/src/main/java/com/tenan/android/ui/feature/explore/ExploreScreen.kt) is the feature for the user find the most favorite tourism place in our Application.

### Search
The [Search Feature](app/src/main/java/com/tenan/android/ui/feature/search/SearchScreen.kt) is the feature for user to search tourism that they want. We also give hotel that related to their query on the [Search Result Feature](app/src/main/java/com/tenan/android/ui/feature/search/result/SearchResultScreen.kt)

## Libraries
We are using several library to build this application

[Jetpack Compose](https://developer.android.com/jetpack/compose) To build our UI
[Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) To manage our dependency injection
[Retrofit](https://square.github.io/retrofit/) To communicate with the API
[Okhttp](https://square.github.io/okhttp/) Work with retrofit to extend some feature like http logging or interceptor.
