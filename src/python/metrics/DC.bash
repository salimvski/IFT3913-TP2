#!/bin/bash

find ../../../jfreechart/src/main/ -name '*.java' > javaFiles
file="javaFiles"
while read -r line; do
    python3 DC.py $line >> DC_metric.txt
    # echo -e "$line\n"
done <$file 
python3 averageDC.py >> DC_metric.txt