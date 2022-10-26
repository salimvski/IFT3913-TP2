def calculateAverageCC():
    file="CC_metric.txt"
    with open(file) as file:
            lines = file.readlines()
            print(len("".join(lines).split("=====================================================================\n")))
            count = 0
            lineCount = 0
            # for line in lines:
            #     line = line.replace("\n", "")
            #     count += float(line.split(": ")[1])
            #     lineCount += 1.0
            # print("the average of comment density metric for jfreechart is {:2f}".format(count/float(lineCount)))



calculateAverageCC()