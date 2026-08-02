#!/bin/bash

echo "----------start deploy -----------"

cd /home/ubuntu/practice-kotlin

sudo fuser -k -n tcp 8080 || true

nohup java -jar project.jar > ./output.log 2>&1 &

echo "----------end deploy-----------"
