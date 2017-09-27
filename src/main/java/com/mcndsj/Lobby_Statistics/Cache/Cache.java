package com.mcndsj.Lobby_Statistics.Cache;

import com.mcndsj.Lobby_Statistics.Utils.SQLUtils;

import java.util.ArrayList;

/**
 * Created by Matthew on 2016/4/14.
 */
public class Cache {
    private String name = null;
    ArrayList<Data> data ;

    public Cache(){
        data = new ArrayList();
    }

    public Cache(String name,DataType type){
        this.name = name;
        data = new ArrayList();
        createCache(type);
    }

    public Data getCache(DataType type){
        synchronized (data) {
            for(Data temp : data) {
                if (temp.getType() == type) {
                    if (temp.isInvalid())
                        temp.update();
                    return temp;
                }
            }
            return createCache(type);
        }
    }

    private Data createCache(DataType type){
        Data data = SQLUtils.getFullRow(name, type);
        if (data != null) {
            this.data.add(data);
        }
        return data;
    }

    public void invalidCache(DataType type){
        getCache(type).setInvalid();
    }

}
