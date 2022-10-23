# Program that calculate number of comments in .java files
# How to run : python3 DC.py <file.java>
# Output : <number of comments in file.java>
import sys

def dcMetric():
    try:
        filename = sys.argv[1]
    except:
        print("please enter a valid file")

    with open(filename) as file:
        lines = file.readlines()
        comments = 0
        lineCount = 0
        for line in lines:
            line = line.replace(" ", "")
            if line[:2] == "//":
                comments += 1
            if line[:2] == "/*":
                comments += 1
                for v in range(lineCount, len(lines)):
                    tmp = lines[v]
                    tmp = tmp.replace(" ", "")     
                    tmp = tmp.replace("\n", "") 
                    if tmp[2:] == "*/":
                        break            
                    if tmp[:1] == "*":
                        comments += 1                 
                    if tmp[:2] == "*/" or tmp == "*/":
                        break
            else:
                lineCount += 1
        print(comments)

dcMetric()












