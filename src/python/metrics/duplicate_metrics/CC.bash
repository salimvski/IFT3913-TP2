#!/bin/bash

./pmd-bin-6.50.0/bin/run.sh cpd --minimum-tokens $1 --files ../../../../jfreechart/ > CC_metric.txt
python3 CC.py