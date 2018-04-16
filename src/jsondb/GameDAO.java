package jsondb;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class GameDAO {
    private static final String DIRECTORY = "games";

    void clear() {
        FileManager.clearDirectory(getDirectory());
    }

    String getGame(int gameID) {
        File directory = getDirectory();
        if (directory == null) return "";
        File file = new File(directory, FileManager.filenameFromID(gameID));
        if (!file.exists()) return "";
        try {
            return FileManager.readFromFile(file);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return "";
        }
    }

    private File getDirectory() {
        File root = new File(JSONDB.ROOT);
        File directory = new File(root, DIRECTORY);
        if (!directory.exists() && !directory.mkdirs()) {
            directory = null;
        }
        return directory;
    }

    ArrayList<Integer> getGameIDs() {
        File directory = getDirectory();
        ArrayList<Integer> ids = new ArrayList<>();
        if (directory == null) return ids;
        for (File game : FileManager.getJSONFiles(directory)) {
            ids.add(FileManager.idFromFile(game));
        }
        return ids;
    }

    boolean saveGame(int gameID, String gameJSON) {
        File directory = getDirectory();
        if (directory == null) return false;
        File game = new File(directory, FileManager.filenameFromID(gameID));
        try {
            FileManager.writeToFile(game, gameJSON);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }
}
