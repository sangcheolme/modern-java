package inaction;

import java.io.File;
import java.io.FileFilter;

public class MethodReference {
    public static void main(String[] args) {
        File[] hiddenFile = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isHidden();
            }
        });
        for (File file : hiddenFile) {
            System.out.println(file.getName());
        }
    }
}
