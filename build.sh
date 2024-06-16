#!/bin/bash

# Set locale
export LANG=en_US.UTF-8
export LANGUAGE=en_US:en
export LC_ALL=en_US.UTF-8

# Set JAVA_HOME to use Java 17
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk

# Ensure Maven is executable
chmod +x ./build.sh

# Clean and package the application
./mvnw clean package -DskipTests

# Copy the JAR file to a location Vercel expects
mkdir -p target
cp target/todoList-0.0.1-SNAPSHOT.jar target/application.jar
