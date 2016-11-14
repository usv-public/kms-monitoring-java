#!/bin/bash -ex
echo -n "Starting application: "
sleep 30
touch /var/log/sendstats.log
# Redirects default and error output to a log file
EXTERNAL_IP=$(curl http://169.254.169.254/latest/meta-data/public-ipv4)
applDir="/root/kms-monitoring-java"
cd $applDir && java -Dkms.ws.uri=ws://$EXTERNAL_IP:8888/kurento -cp $applDir/libraries/commons-codec-1.9.jar:$applDir/libraries/commons-logging-1.2.jar:$applDir/libraries/fluent-hc-4.5.1.jar:$applDir/libraries/gson-2.5.jar:$applDir/libraries/guava-19.0.jar:$applDir/libraries/httpclient-4.5.1.jar:$applDir/libraries/httpcore-4.4.3.jar:$applDir/libraries/javax.inject-1.jar:$applDir/libraries/jetty-io-9.3.0.M2.jar:$applDir/libraries/jetty-util-9.3.0.M2.jar:$applDir/libraries/kms-api-core-6.5.0.jar:$applDir/libraries/kms-api-elements-6.5.0.jar:$applDir/libraries/kms-api-filters-6.5.0.jar:$applDir/libraries/kurento-client-6.5.0.jar:$applDir/libraries/kurento-commons-6.5.0.jar:$applDir/libraries/kurento-jsonrpc-client-6.5.0.jar:$applDir/libraries/paranamer-2.7.jar:$applDir/libraries/slf4j-api-1.7.13.jar:$applDir/libraries/websocket-api-9.3.0.M2.jar:$applDir/libraries/websocket-client-9.3.0.M2.jar:$applDir/libraries/websocket-common-9.3.0.M2.jar:$applDir/target/kms-monitoring-java-6.5.0.jar org.kurento.tutorial.kmsmonitor.SendStats >> /var/log/sendstats.log 2>&1 &
