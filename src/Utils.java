import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    
    private static String getFileExtension(File file){
        // Return extension of given file
        String extension = "";
        String fileName = file.toString();
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
    public static List<File> getJavaClasses(String directoryName) {
        // Return all java classes of given dir (includes sub dir)
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<>();

        File[] javaClassesList = directory.listFiles();
        assert javaClassesList != null;
        for (File file : javaClassesList) {
            if (file.isFile() && Objects.equals(getFileExtension(file), "java")) {
                resultList.add(file);
            } else if (file.isDirectory()) {
                resultList.addAll(getJavaClasses(file.getAbsolutePath()));
            }
        }
        return resultList;
    }

    public static boolean isValidPath(String path) {

        File file = new File(path);
        if (file.isDirectory() || file.isFile() || file.canRead() || file.canWrite()){
            return true;
        }
        else {
            return false;
        }
    }
}
