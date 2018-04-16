package plugin;

import java.util.ArrayList;

public interface IPlugin {
    public boolean saveGame(int gameID, String gameJSON);
    public boolean saveCommand(int gameID, int commandID, String commandJSON);
    public boolean savePlayer(String username, String playerJSON);
    public ArrayList<Integer> getGameIDs();
    public String getGame(int gameID);
    public ArrayList<String> getCommands(int gameID);
    public ArrayList<String> getPlayers();
    public String getPlayer(String username);
    public void createDAOs();
    public void clear();
}
