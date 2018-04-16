package jsondb;

import plugin.IPlugin;

import java.util.ArrayList;

public class JSONDB implements IPlugin {
    static final String ROOT = ".jsondb";
    private CommandDAO commandDAO = new CommandDAO();
    private GameDAO gameDAO = new GameDAO();
    private PlayerDAO playerDAO = new PlayerDAO();

    @Override
    public void clear() {
        commandDAO.clear();
        gameDAO.clear();
        playerDAO.clear();
    }

    @Override
    public void createDAOs() {}

    @Override
    public ArrayList<String> getCommands(int gameID) {
        return commandDAO.getCommands(gameID);
    }

    @Override
    public String getGame(int gameID) {
        return gameDAO.getGame(gameID);
    }

    @Override
    public ArrayList<Integer> getGameIDs() {
        return gameDAO.getGameIDs();
    }

    @Override
    public String getPlayer(String username) {
        return playerDAO.getPlayer(username);
    }

    @Override
    public ArrayList<String> getPlayers() {
        return playerDAO.getPlayers();
    }

    @Override
    public boolean saveCommand(int gameID, int commandID, String commandJSON) {
        return commandDAO.saveCommand(gameID, commandID, commandJSON);
    }

    @Override
    public boolean saveGame(int gameID, String gameJSON) {
        return gameDAO.saveGame(gameID, gameJSON);
    }

    @Override
    public boolean savePlayer(String username, String playerJSON) {
        return playerDAO.savePlayer(username, playerJSON);
    }
}
