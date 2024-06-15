#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Install SDKMAN!
curl -s "https://get.sdkman.io" | bash

# Load SDKMAN
source "$HOME/.sdkman/bin/sdkman-init.sh"

# Install Maven
sdk install maven

# Run Maven commands
mvn clean install
