#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Install SDKMAN!
curl -s "https://get.sdkman.io" | bash

# Load SDKMAN
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Java
sdk install java 17.0.8-tem

# Set JAVA_HOME environment variable
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"

# Install Maven
sdk install maven

# Run Maven commands
mvn clean install
