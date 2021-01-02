#!/bin/bash

set -e
set +x

./mvnw deploy \
    --settings .github/maven/settings.xml \
    -P publish-artifacts,sign-artifacts,ossrh-deploy \
    -DskipTests \
    "-Drevision=${GIT_TAG:-development-SNAPSHOT}" \
    --batch-mode \
    --show-version
