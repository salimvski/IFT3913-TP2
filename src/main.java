import metrics.Age;
import metrics.Issues;
import metrics.TPC;
import utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static utils.Utils.getPackName;

public class main {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Entrez le chemin (absolue) du projet :");
        String path;
        while (true){
            path = reader.nextLine();
            if(Utils.isValidPath(Path.of(path))){
                break;
            }
            else {
                System.out.println("Ce chemin ne semble pas être valide, veuillez reessayer :  \n");
            }
        }


        // Prepare CSV File
        File csvFile = new File( "result.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        StringBuilder line = new StringBuilder();

        try {
            line.append("AGE").append(",");
            line.append("Issues").append(",");
            line.append("TPC").append("\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

        Integer lastYearCommitCount = Age
                .getLastYearCommitCount("https://api.github.com/repos/jfree/jfreechart/stats/commit_activity");

        Integer issuesCount = Issues
                .getBugIssues(" https://api.github.com/repos/jfree/jfreechart/issues?labels=bug");



        Integer tpc = TPC.getTPC(
                TPC.getNombreClass(path + "\\src\\main\\java"),
                TPC.getNombreClass(path + "\\src\\test\\java")
        );

        try {

            line.append(lastYearCommitCount).append(",");
            line.append(issuesCount).append(",");
            line.append(tpc).append("\n");
            fileWriter.write(line.toString());
            fileWriter.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

 }
}
