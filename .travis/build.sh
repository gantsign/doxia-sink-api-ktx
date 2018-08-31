#!/bin/bash

set -e

./mvnw install --batch-mode --show-version

if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then

    # See http://www.debonair.io/post/maven-cd/ for instructions
    openssl aes-256-cbc -K $encrypted_83553d998fe4_key -iv $encrypted_83553d998fe4_iv \
        -in .travis/codesigning.asc.enc -out .travis/codesigning.asc -d
    gpg --fast-import .travis/codesigning.asc

    ./mvnw deploy -P publish-artifacts --batch-mode --show-version --settings .travis/settings.xml -Denforcer.skip=true
fi
