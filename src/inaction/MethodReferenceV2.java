package inaction;

import java.io.File;

public class MethodReferenceV2 {
    public static void main(String[] args) {
        File[] hiddenFile = new File(".").listFiles(File::isHidden);
        for (File file : hiddenFile) {
            System.out.println(file.getName());
        }
    }
}
