package tp2java.metrics;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import tp2java.utils.Utils;

public class Age {

    private Age() {
        throw new IllegalStateException("Metric class");
    }
    public static String getLastModified (Path filePath) throws IOException {
        // Returns last modified time (locally) of given file
        if (Utils.isValidPath(filePath) == false){
            System.out.println("Path might be invalid");
            throw new FileNotFoundException("Invalid Path");
        }
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        return df.format(attr.lastModifiedTime().toMillis());
    }

    public static String getLastCommitDate (Path filePath) throws IOException {
        // Returns last commit date of repo in Github (date is the same of every files)
        if (Utils.isValidPath(filePath) == false){
            System.out.println("Path might be invalid");
            throw new FileNotFoundException("Invalid Path");
        }
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        return df.format(attr.creationTime().toMillis());
    }

    public static int getLastYearCommitCount (String URL){

        //TODO : Ask for USER and REPO only instead of URL to simplify

        // Returns number of commits of project last year

        // DOC : https://docs.github.com/en/rest/metrics/statistics#get-the-last-year-of-commit-activity
        // URL for jfree is : https://api.github.com/repos/jfree/jfreechart/stats/commit_activity

        String r = Utils.getRequest(URL);

        if (r == "{\n" + "\n" + "}"){
            // Means Github API having troubles
            return 0;
        }
        int i = 0;
        for (String s : Utils.getValuesForGivenKey(r,"total")) {
            i += Integer.valueOf(s);
        }
        return i;
    }
}
