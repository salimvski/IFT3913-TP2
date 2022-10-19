package metrics;

import utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

public class Age {

    private Age() {
        throw new IllegalStateException("Metric class");
    }
    public static String getLastModified (Path filePath) throws IOException {
        // Return last modified time of given file
        if (Utils.isValidPath(filePath) == false){
            System.out.println("Path might be invalid");
            throw new FileNotFoundException("Invalid Path");
        }
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        return df.format(attr.lastModifiedTime().toMillis());
    }

    public static String getCreationDate (Path filePath) throws IOException {
        // Return last modified time of given file
        if (Utils.isValidPath(filePath) == false){
            System.out.println("Path might be invalid");
            throw new FileNotFoundException("Invalid Path");
        }
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        return df.format(attr.creationTime().toMillis());
    }
}
