#!/usr/bin/env bash

PACKAGE=com.example.basicscodelab
ACTIVITY=.MainActivity
APK_LOCATION=app/build/outputs/apk/app-debug.apk
echo "Package: $PACKAGE"

echo "Building the project with tasks: $TASKS"
./gradlew $TASKS

echo "Uninstalling $PACKAGE"
adb uninstall $PACKAGE

echo "Installing $APK_LOCATION"
adb install $APK_LOCATION

echo "Starting $ACTIVITY"
adb shell am start -n $PACKAGE/$ACTIVITY
