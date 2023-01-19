#!/bin/bash

wget https://dlcdn.apache.org/spark/spark-3.3.1/spark-3.3.1-bin-hadoop3.tgz
tar -xzf spark-3.3.1-bin-hadoop3.tgz
mkdir Script
chmod 777 Script/
mv spark-3.3.1-bin-hadoop3 Script
export SPARK_HOME=$HOME/Script/spark-3.3.1-bin-hadoop3
export PATH=$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH
source $HOME/.bashrc
cd Script/spark-3.3.1-bin-hadoop3/sbin
./start-master.sh
./start-worker.sh spark://localhost:7077
./spark-submit --master spark://localhost:7077 --conf spark.app.name="covid." --num-executors 2 --executor-cores 1 --executor-memory 1G /home/clau/IdeaProjects/CovidProjectSpark/target/projectSpark-1.0-SNAPSHOT.jar

