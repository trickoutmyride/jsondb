package jsondb;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class CommandDAO {
    private static final String DIRECTORY = "commands";

    void clear() {
        FileManager.clearDirectory(getDirectory());
    }

    ArrayList<String> getCommands(int gameID) {
        ArrayList<String> commands = new ArrayList<>();
        File directory = getDirectory();
        if (directory == null) return commands;
        directory = new File(directory, Integer.toString(gameID));
        if (directory.exists() && directory.isDirectory()) {
            for (File file : sortFiles(FileManager.getJSONFiles(directory))) {
                try {
                    commands.add(FileManager.readFromFile(file));
                } catch (FileNotFoundException exception) {
                    System.out.println("Could not read file " + file.getName());
                }
            }
        }
        return commands;
    }

    private File getDirectory() {
        File root = new File(JSONDB.ROOT);
        File directory = new File(root, DIRECTORY);
        if (!directory.exists() && !directory.mkdirs()) {
            directory = null;
        }
        return directory;
    }

    boolean saveCommand(int gameID, int commandID, String commandJSON) {
        File directory = getDirectory();
        if (directory == null) return false;
        directory = new File(directory, Integer.toString(gameID));
        if (!directory.exists() && !directory.mkdirs()) return false;
        File file = new File(directory, FileManager.filenameFromID(commandID));
        try {
            FileManager.writeToFile(file, commandJSON);
        } catch (IOException exception) {
            System.out.println("Could not save command");
            return false;
        }
        return true;
    }

    private ArrayList<File> sortFiles(File[] files) {
        ArrayList<File> filtered = new ArrayList<>();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".json")) {
                filtered.add(file);
            }
        }
        filtered.sort(new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return FileManager.idFromFile(file1) - FileManager.idFromFile(file2);
            }
        });
        return filtered;
    }
}
