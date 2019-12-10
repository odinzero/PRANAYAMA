package com.pranayama.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class utils2 {

    public static String[] listFilesForFolder(final File folder) {
        List<String> fileList = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
               // System.out.println(fileEntry.getName());
               fileList.add(fileEntry.getName());
            }
        }
        String[] files = new String[fileList.size()];
        files =  fileList.toArray(files);
        
        return files;
    }
    
    // path - "./src/main/recources/sound"
    public static void listFilesForFolder2(String path) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }
    }

    public static void main(String[] args) throws IOException {

        final File folder = new File("./src/main/recources/sound");
        String[] f = listFilesForFolder(folder);
        
        for (int i = 0; i < f.length; i++) {
            String string = f[i];
            System.out.println(string);
        }
    }

}
