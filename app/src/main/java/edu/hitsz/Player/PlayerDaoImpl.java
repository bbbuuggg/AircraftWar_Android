package edu.hitsz.Player;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.hitsz.R;

public class PlayerDaoImpl implements PlayerDao{
    private List<Player>players;

    protected String[][] data = {{"","","",""}};
    public PlayerDaoImpl(){
        players = new ArrayList<Player>();
    }
    public List<Player> getAllPlayer(FileInputStream file) {
        try (ObjectInputStream ois = new ObjectInputStream(file)) {

            players = (List<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
//                    throw new RuntimeException(e);
            e.printStackTrace();
        }
//        System.out.println("what???");
//        int j = 1;
//        for (Player player : players) {
//            data = insert(data,Integer.toString(j++),player.getPlayerId(), Integer.toString(player.getScore()),player.getTime());
//        }
//        System.out.println("now what???");
        return players;
    }
    public String[][] returnData(){
        return data;
    }
    public void outPutFile(FileOutputStream file) {
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
//        System.out.println("---------------------------------------\n" +
//                "************ Game  Ladder *************\n" +
//                "---------------------------------------\n"  );
//        for (Player player : players) {
//            System.out.println(" PlayerID: " + player.getPlayerId() +" \t "  + "\t" + "Score: " + player.getScore() + "\t" +  "time:" + player.getTime());
//        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(players);
        } catch (IOException e) {
//                    throw new RuntimeException(e);
            e.printStackTrace();
        }
    }
    public void doDelete(String playerId){
        for (Player item :players){
            if (item.getPlayerId() == playerId){
                players.remove(item);
                return;
            }
        }


    }
    public void doAdd(Player player){
        players.add(player);
    }
    public void scoreSort(){
        int len = players.size();
        for (int i = 0; i < len - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (players.get(j).getScore() < players.get(j+1).getScore()) {
                    Player tmp = players.get(j);
                    players.set(j,players.get(j+1));
                    players.set(j+1,tmp);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
//    private static String[][] insert(String[][] arr, String str1,String str2,String str3,String str4) {
//        int rows = arr.length; // 获取原数组长度
//        int columns = arr[0].length;
//        int i = 0;
//        // 新建临时字符串数组
//        String[][] tmp = new String[rows+1][columns];
//        // 先遍历将原来的字符串数组数据添加到临时字符串数组
//        for (; i < rows; i++) {
//            tmp[i] = arr[i];
//        }
//        // 在末尾添加上需要追加的数据
//        if(i == rows){
//            tmp[i][0] = str1;
//            tmp[i][1] = str2;
//            tmp[i][2] = str3;
//            tmp[i][3] = str4;
//
//        }
//        return tmp; // 返回拼接完成的字符串数组
//    }
public List<Map<String, Object>> getData() {
    ArrayList<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
    int j = 1;
    for(Player player : players) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("XuHao",j++);
        map.put("ID", player.getPlayerId());
        map.put("score", player.getScore());
        map.put("time", player.getTime());
        listitem.add(map);
    }

    return listitem;
}
    public String getAdr(int degree) {
        String rankingLIST;
        switch (degree) {
            case 2:
                rankingLIST = "PlayerList2.dat";
                break;
            case 3:
                rankingLIST = "PlayerList3.dat";
                break;
            default:
                rankingLIST = "PlayerList1.dat";
        }
        return rankingLIST;
    }



}
