package jsondb;

import java.io.*;
import java.util.Scanner;

class FileManager {
    static void clearDirectory(File directory) {
        if (directory == null) return;
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                clearDirectory(file);
            }
            if (!file.delete()) {
                System.out.println("Could not delete " + file.getPath());
            }
        }
    }

    static String filenameFromID(int id) {
        return Integer.toString(id) + ".json";
    }

    static String filenameFromName(String name) {
        return name + ".json";
    }

    static int idFromFile(File file) {
        return Integer.parseInt(file.getName().replaceAll("\\.json", ""));
    }

    static File[] getJSONFiles(File file) {
        return file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".json");
            }
        });
    }

    static String nameFromFile(File file) {
        return file.getName().replaceAll("\\.json", "");
    }

    static String readFromFile(File file) throws FileNotFoundException {
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    static void writeToFile(File file, String data) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
            writer.write(data);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
