#!/bin/bash

find ../../../jfreechart/src/main/java/org/jfree/ -name "*.java" > testFiles
echo "END OF MAIN" >> testFiles
find ../../../jfreechart/src/test/java/org/jfree/ -name "*.java" >> testFiles
python3 ../../python/metrics/RTCTT.py testFiles > RTCTT_metric.txt
# rm testFiles

