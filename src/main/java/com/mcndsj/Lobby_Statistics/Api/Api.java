package com.mcndsj.Lobby_Statistics.Api;

import com.mcndsj.Lobby_Statistics.Cache.DataType;
import com.mcndsj.Lobby_Statistics.lobby_Statistics;

/**
 * Created by Matthew on 2016/4/14.
 */
public class Api {
    //Query Only
    public void getData(String playerName, DataType type,  QueryCallBack call){
        lobby_Statistics.getInstance().getDataMap(call,playerName,type);
    }

    public void invalidCache(String playerName,DataType type){
        lobby_Statistics.getInstance().invalidCache(type,playerName);
    }

    public void clearCache(String playerName){
        lobby_Statistics.getInstance().clearCache(playerName);
    }



}
