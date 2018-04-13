#!/bin/bash
docker-compose -f docker-compose.test.yml down
rm -Rf ./docker/postgres/data-despesa-test
docker-compose -f docker-compose.test.yml up -d
mvn clean package
docker-compose -f docker-compose.test.yml down