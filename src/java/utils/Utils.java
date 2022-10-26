package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static boolean isValidPath(Path path) {
        // Return true if given path is valid, else return false
        File file = new File(String.valueOf(path));
        if (file.isDirectory() || file.isFile() || file.canRead() || file.canWrite()){
            return true;
        }
        else {
            return false;
        }
    }

    public static String getPackName(String path) throws IOException {
        // if class is in some package, return package name as String

        if(isValidPath(Path.of(path)) == false){
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

    private static HttpURLConnection conn;
    public static String getRequest(String URL) {

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            java.net.URL url =
                    new URL(URL);
            conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            conn.setReadTimeout(5000);

            // Test if the response from the server is successful
            int status = conn.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

//          System.out.println("response code: " + status);
            String r = responseContent.toString();

            return  r;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        // if error, return -1
        return "-1";
    }

    public static List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }
}
