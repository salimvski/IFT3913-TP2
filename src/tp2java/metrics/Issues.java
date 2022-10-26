package tp2java.metrics;

import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import tp2java.utils.Utils;


public class Issues {

    public static Integer getBugIssues(String URL) {

        //TODO : Ask for USER and REPO only instead of URL to simplify

        // Use Github API to return number of opened issues labeled as bug
        // DOC : https://docs.github.com/en/rest/issues

        // URL for jfreechart is :
        // https://api.github.com/repos/jfree/jfreechart/issues?labels=bug

        String response = Utils.getRequest(URL);
        JSONArray array = new JSONArray(response);

        return array.length();
    }

    public static class lcsec {

        private String path;
        private String pack;
        private String className;
        private int lcsecVal;
        private int nvlocVal;

        public String getPath() {
            return path;
        }

        public String getPack() {
            return pack;
        }

        public String getClassName() {
            return className;
        }

        public int getLcsecVal() {
            return lcsecVal;
        }

        public int getNvlocVal() {
            return nvlocVal;
        }

        public lcsec(String path, String pack, String className, int lcsecVal, int nvlocVal){
            this.path = path;
            this.pack = pack;
            this.className = className;
            this.lcsecVal = lcsecVal;
            this.nvlocVal = nvlocVal;
        }
        public static ArrayList getLCSEC(String path, Boolean createFile, String csvPath) throws IOException {

            ArrayList<lcsec> lcsecList = new ArrayList<>();

            List<File> classesList = Utils.getJavaClasses(path);

            ArrayList<String> fileNames = new ArrayList<>();
            for (int i = 0; i < Objects.requireNonNull(classesList).size(); i++) {
                if (classesList.get(i).isFile()) {
                    if (classesList.get(i).getName().split("\\.")[1].equals("java")) {
                        String fileName = classesList.get(i).getName();
                        fileNames.add(fileName);
                    }
                }
            }


            return lcsecList;
        }

        public static int getCsec(String filePath, ArrayList fileNames) throws IOException {
            int csec = 0;
            ArrayList<String> copy = new ArrayList<String>(fileNames);
            String[] currentNames =  filePath.split("/");
            String currentName =  currentNames[currentNames.length -1];
            copy.removeIf(n -> n.equals(currentName));
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(filePath));
            }catch (Exception e){
                System.out.println("Mauvais chemin vers le fichier.");
                return 0;
            }
            try {
                String line = br.readLine();

                while (line != null) {
                        ArrayList<String> toRemove = new ArrayList<>();
                    for (String name : copy) {
                        if (line.contains(name.split("\\.")[0])) {
                            csec++;
                            toRemove.add(name);
                        }
                    }
                        for(Object remove : toRemove){
                            copy.remove(remove);
                        }

                    line = br.readLine();
                }
            } finally {
                br.close();
            }
            return csec;
        }
    }
}
