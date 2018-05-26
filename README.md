# Secutor

Multiplayer games depend on a server to keep connected clients in sync with each other. Secutor demonstrates how
a server can maintain the state of the game and keep clients in check.

![screenshot](https://i.imgur.com/wzqQTYm.png)

## Try it out

1. Grab a JAR application with `./gradlew desktop:dist`
2. Run the server with `java -jar secutor.jar -server`
3. Connect any number of clients with `java -jar secutor.jar`

Old demonstration with collission handling (removed): [Movement & collision handling](https://www.youtube.com/watch?v=Giy4LkKaBZ8)

## Developing & Running

Java 8 is the only requirement.

* Run: `./gradlew desktop:run`
* Distribute: `./gradlew desktop:dist` outputs to the `desktop/build/libs` folder

Run a server with the flag `-server`.

The `core` directory contains all the game logic and rendering.
