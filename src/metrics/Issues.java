package metrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Issues {

    private static HttpURLConnection conn;
    public static Integer getIssues(String URL) {
        // Use Githun API to return number of opened issues
        // DOC : https://docs.github.com/en/rest/issues/issues#list-repository-issues

//        URL for jfreechart is :
//        https://api.github.com/search/issues?q=jfreechart/jfree+type:issue+state:open

        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            URL url =
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
//          System.out.println(responseContent.toString());
            String str = responseContent.toString();
            String result = str.substring(str.indexOf("{\"") + 1, str.indexOf(","));
//          System.out.println(result);
            return Integer.valueOf(Arrays.toString(result.split(":")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        // if error, return -1
        return -1;
    }

}
