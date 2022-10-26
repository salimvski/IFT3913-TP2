import subprocess
import os
import sys
import csv
import platform


def countLines(file):
    linesCount = 0
    file_dir = os.path.dirname(os.path.realpath('__file__'))
    file_name = os.path.join(file_dir, file)
    with open(file_name) as file:
        lines = file.readlines()
        for line in lines:
            linesCount += 1
    return linesCount


def ratioMetric():
    try:
        file1 = sys.argv[1]
        path = "../../../jfreechart/src/test/java/org/jfree/"
        with open(file1) as file:
            lines = file.readlines()
            # print(lines)
            counter = 0
            ratioSum = 0
            for line in lines:
                if line == "END OF MAIN\n":
                    break
                pathTab = line.split("/")
                javaFile = pathTab[len(pathTab) - 1]
                filename = javaFile.split(".")[0]
                newPath = line.replace("main", "test")
                toCheck = newPath.replace(filename, filename + "Test")
                tmp = toCheck.split("\n")[0]
                tmp += "\n"
                if tmp in lines:
                    counter += 1
                    output1 = subprocess.check_output(
                        "wc -l " + tmp, shell=True)
                    output2 = subprocess.check_output(
                        "wc -l " + line, shell=True)
                    ratioSum += int(output1[0:3])/int(output2[0:3])
                    print("The ratio for {} is {:.2f}".format(
                        filename, int(output1[0:3])/int(output2[0:3])))
            print("The average of all ratios is {:2f}".format(ratioSum/counter))
    except:
        print("Please enter a directory")


if __name__ == "__main__":
    ratioMetric()
