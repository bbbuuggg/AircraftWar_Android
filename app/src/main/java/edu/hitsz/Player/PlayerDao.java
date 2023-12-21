package edu.hitsz.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public interface PlayerDao {

    List<Player> getAllPlayer(FileInputStream file);
    void outPutFile(FileOutputStream file);
    void doAdd(Player player);
    void doDelete(String playerID);
    String[][] returnData();
    void scoreSort();
}
