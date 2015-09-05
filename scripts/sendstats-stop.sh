#!/bin/bash
# Grabs and kill a process from the pidlist

pid=`ps aux | grep SendStats | awk '{print $2}'`
kill -9 $pid
