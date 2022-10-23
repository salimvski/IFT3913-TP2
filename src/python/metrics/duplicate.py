import re
import sys


def duplicateMetric():
    try:
        filename = sys.argv[1]
    except:
        print("please enter a valid file")

    with open(filename) as file:
        lines = file.readlines()
        print(lines)

duplicateMetric()