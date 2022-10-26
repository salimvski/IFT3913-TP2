package tp2java.metrics;


import java.io.File;
import java.util.List;
import tp2java.utils.Utils;

public class TPC {
    // Test par class (Ratio nombre de class, nombre de class test)
    public static int getNombreClass(String dirPath){
        List<File> classList = Utils.getJavaClasses(dirPath);
        return classList.size();
    }

    public static int getTPC (int classCount, int testClassCount){
        // En fonction du ratio, et du seuil fixé, on peut valider ou non
        return classCount / testClassCount;
    }


}
