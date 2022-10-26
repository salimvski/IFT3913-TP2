import os
import sys
import csv
import pandas as pd 

# how to use 
# python3 lscec.py jls.csv gr/
# jls.csv has is the output created from python3 jls.py gr/


# function that takes the reference
# and the file to check if the reference is present

def referenceCheck(wordCheck, file):
    ref = wordCheck.split("/")
    class_file = ref[len(ref) - 1].split(".java")[0]
    with open(file) as fd:
        contents = fd.read()
        if class_file in contents:
            return True       
        return False


# function that for each class will loop over
# all the other classes to check the metric
# and will fill the dict if True
def csec(classList, csvFile):
    metric_counter = dict.fromkeys(classList, 0)   
    counter = 0
    while True:
        counter += 1
        for elem in range(1, len(classList)):
            if referenceCheck(classList[0], classList[elem]):
                metric_counter[classList[0]] = metric_counter[classList[0]] + 1
        if counter == len(classList):
            break
        
        tmp = classList[0]
        classList[0] = classList[counter]
        classList[counter] = tmp   
    
    pathCSV = csvFile


    # part overwrite csv file
    aa = pd.read_csv(pathCSV)
    values = []

    for v, i in metric_counter.items():
        values.append(i)

    aa[values[0]] = pd.Series(values[1:])
    aa.to_csv(pathCSV, index=False)



    
    
        


def lcsec(csvfile, dir):

    if csvfile and dir:
        directory = dir
        csvFile = csvfile
    else:
        directory = sys.argv[2]
        csvFile = sys.argv[1]

    classList = []
    for root, dirs, files in os.walk(directory):
        for file in files:
            path = "./" + root + "/"
            classList.append(path + file)
    csec(classList, csvFile)

if __name__ == "__main__":
    lcsec(None, None)