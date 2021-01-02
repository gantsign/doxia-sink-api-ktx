#!/bin/bash

set -e

./mvnw install \
    "-Drevision=${GIT_TAG:-development-SNAPSHOT}" \
    --batch-mode \
    --show-version
