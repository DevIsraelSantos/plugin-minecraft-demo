#!/bin/bash

ACTION="$1"

if [ ! -s .env ]; then
    echo "ERRO: arquivo .env não existe ou está vazio"
    exit 1
fi

set -a
source ./.env
set +a

: "${SERVER_DIR:?SERVER_DIR não definida no .env}"
: "${SERVER_JAR:?SERVER_JAR não definida no .env}"

echo "SERVER_DIR=$SERVER_DIR"
echo "SERVER_JAR=$SERVER_JAR"

echo
echo "========================================"
echo " BUILDANDO PLUGIN"
echo "========================================"
echo

./gradlew clean build

if [ $? -ne 0 ]; then
    echo
    echo "ERRO NO BUILD"
    exit 1
fi

echo
echo "========================================"
echo " COPIANDO PLUGIN"
echo "========================================"
echo

cp -f build/libs/plugin-minecraft-demo.jar "$SERVER_DIR/plugins/"

if [ $? -ne 0 ]; then
    echo
    echo "ERRO AO COPIAR O PLUGIN"
    exit 1
fi


if [ "$ACTION" = "start" ]; then
    echo
    echo "========================================"
    echo " INICIANDO SERVIDOR"
    echo "========================================"
    echo

    cd "$SERVER_DIR" || exit 1

    java -jar "$SERVER_JAR" nogui
else
    echo "Skip start server"
fi

