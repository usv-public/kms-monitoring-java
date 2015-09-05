#!/bin/bash

cd /home/ubuntu/kms-monitoring-java
nohup java -cp httpcore-4.3.1.jar:websocket-api-9.3.0.M1.jar:jetty-io-9.3.0.M1.jar:jetty-util-9.3.0.M1.jar:websocket-common-9.3.0.M1.jar:websocket-client-9.3.0.M1.jar:gson-2.3.1.jar:guava-18.0.jar:slf4j-api-1.7.5.jar:kurento-jsonrpc-client-6.0.0-SNAPSHOT.jar:kurento-commons-6.0.0-SNAPSHOT.jar:kurento-client-6.0.0-SNAPSHOT.jar:target/kms-monitoring-java-6.0.0-SNAPSHOT.jar org.kurento.tutorial.kmsmonitor.SendStats &
