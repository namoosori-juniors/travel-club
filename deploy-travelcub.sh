#!/bin/bash

REPOSITORY=/home/ec2-user/app/travis/build
APP_NAME="travel-club-app"

CURRENT_MONGO_PID=$(docker ps -aqf "mongodb")

echo "$CURRENT_MONGO_PID"

if [ -z "$CURRENT_MONGO_PID" ]; then
    echo "> No running db."
else
    echo "> kill $CURRENT_MONGO_PID"
    docker stop "$CURRENT_MONGO_PID"
    sleep 5
fi

CURRENT_APP_PID=$(docker ps -aqf "name=$APP_NAME")

echo "$CURRENT_APP_PID"

if [ -z "$CURRENT_APP_PID" ]; then
    echo "> No running app."
else
    echo "> kill $CURRENT_APP_PID"
    docker stop "$CURRENT_APP_PID"
    sleep 5
fi

cd "$REPOSITORY"

docker build -t "$APP_NAME" .
docker-compose up

