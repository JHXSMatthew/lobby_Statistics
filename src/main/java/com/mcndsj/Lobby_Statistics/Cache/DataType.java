package com.mcndsj.Lobby_Statistics.Cache;

/**
 * Created by Matthew on 2016/4/14.
 */
public enum DataType {
    walls("TheWalls","Name"),
    uhc("UHC","Name"),
    bedWar("bw_stats_players","name"),
    CSGO("CSGO_Bomb","Name"),
    arc("Arc_Games","Name");

    String tableName = null;
    String keyName = null;
    DataType(String dbName,String keyName){
        this.tableName = dbName;
        this.keyName = keyName;
    }

    public String  getSQLTableName(){
        return this.tableName;
    }

    public String getSQLKeyName(){
        return this.keyName;
    }
}
