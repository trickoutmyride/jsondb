package jsondb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class PlayerDAO {
    private static final String DIRECTORY = "players";

    void clear() {
        FileManager.clearDirectory(getDirectory());
    }

    private File getDirectory() {
        File root = new File(JSONDB.ROOT);
        File directory = new File(root, DIRECTORY);
        if (!directory.exists() && !directory.mkdirs()) {
            directory = null;
        }
        return directory;
    }

    String getPlayer(String username) {
        File directory = getDirectory();
        if (directory == null) return "";
        File file = new File(directory, FileManager.filenameFromName(username));
        if (!file.exists()) return "";
        try {
            return FileManager.readFromFile(file);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return "";
        }
    }

    ArrayList<String> getPlayers() {
        File directory = getDirectory();
        ArrayList<String> players = new ArrayList<>();
        if (directory == null) return players;
        for (File player : FileManager.getJSONFiles(directory)) {
            players.add(FileManager.nameFromFile(player));
        }
        return players;
    }

    boolean savePlayer(String username, String playerJSON) {
        File directory = getDirectory();
        if (directory == null) return false;
        File game = new File(directory, FileManager.filenameFromName(username));
        try {
            FileManager.writeToFile(game, playerJSON);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }
}
