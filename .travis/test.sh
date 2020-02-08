#!/bin/bash

set -e

./mvnw install \
    "-Drevision=${TRAVIS_TAG:-development-SNAPSHOT}" \
    --batch-mode \
    --show-version
