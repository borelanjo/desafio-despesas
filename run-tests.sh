#!/bin/bash
docker-compose -f docker-compose.test.yml down
sudo rm -Rf ./docker/postgres/data-despesa-test
docker-compose -f docker-compose.test.yml up -d
mvn clean test
docker-compose -f docker-compose.test.yml down