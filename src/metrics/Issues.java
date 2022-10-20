package metrics;

import org.json.JSONArray;
import utils.Utils;


public class Issues {

    public static Integer getBugIssues(String URL) {
        // Use Github API to return number of opened issues labeled as bug
        // DOC : https://docs.github.com/en/rest/issues

        // URL for jfreechart is :
        // https://api.github.com/repos/jfree/jfreechart/issues?labels=bug

        String response = Utils.getRequest(URL);
        JSONArray array = new JSONArray(response);

        return array.length();
    }

}
