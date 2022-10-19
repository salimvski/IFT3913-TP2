import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

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
        // Return true if given path is valid, else return false
        File file = new File(path);
        if (file.isDirectory() || file.isFile() || file.canRead() || file.canWrite()){
            return true;
        }
        else {
            return false;
        }
    }

    public static String getPackName(String path) throws IOException {
        // if class is in some package, return package name as String

        if(isValidPath(path) == false){
            System.out.println("Path might be invalid");
            throw new FileNotFoundException("Invalid Path");
        }

        String pack = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
        }catch (Exception e){
            System.out.println("Mauvais chemin vers le fichier.");
            return "";
        }
        try {
            String line = br.readLine();

            while (line != null) {
                if(line.split(" ").length != 0) {
                    if (line.split(" ")[0].equals("package")) {
                        pack = line.split(" ")[1].split(";")[0];
                    }
                }else {
                    return "";
                }
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return pack;
    }
}
