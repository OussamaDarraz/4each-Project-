#!/bin/bash
cd ../..
mvn package
scp -r -p server/target/server-1.0.0-jar-with-dependencies.jar foreach@172.31.254.54:server-1.0.0-jar-with-dependencies.jar

mv client/target/client-1.0.0-jar-with-dependencies.jar ~/Desktop/client-1.0.0-jar-with-dependencies.jar
