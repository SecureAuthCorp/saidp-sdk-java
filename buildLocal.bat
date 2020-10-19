:: this batch is used internally in the corporation to build and export a local version in maven local
@echo off
./gradlew.bat clean buildLocal test publishToMavenLocal