# Weather App Dagger
This is an example of Android application using TDD and MVP design pattern to show the current weather information.
It is written 100% in Kotlin with both Espresso and Unit tests.

This demo explains below concepts such as:

1. TDD architecture
2. MVP design pattern
3. Dagger dependency injection
4. Kotlin code architecture
5. ReactiveX for Async operations
6. Clean code concept

Features:

Neat build.gradle with shared dependencies.

Dagger2 with android component. Injectable Views and structure to make the app testable and maintanable.

State management with RxKotlin and Android Components - background-foreground and orientation changes will connect to the ongoing network request.

Network requests continue in the background - UI is capable of retrieval of the current state when resumed.

Custom retrofit call factory to handle no connection error in a reactive way.

Espresso tests with overriden dependencies and MockWebServer.

Easy debuggable builds.

## Languages, libraries and tools used in this project

* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* Android Support Libraries
* [Dagger 2](https://github.com/google/dagger)
* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
