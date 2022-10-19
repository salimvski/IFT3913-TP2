package metrics;

import utils.Utils;

import java.io.File;
import java.util.List;

public class TPC {
    // Test par class (Ratio nombre de class, nombre de class test)
    private int getNombreClass (String dirPath){
        List<File> classList = Utils.getJavaClasses(dirPath);
        return classList.size();
    }

    public static int getTPC (int classNum, int testClassNum){
        // En fonction du ratio, et du seuil fixé, on peut valider ou non
        return classNum / testClassNum;
    }
}