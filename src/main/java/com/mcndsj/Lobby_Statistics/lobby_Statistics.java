package com.mcndsj.Lobby_Statistics;

import com.mcndsj.Lobby_Statistics.Api.Api;
import com.mcndsj.Lobby_Statistics.Api.QueryCallBack;
import com.mcndsj.Lobby_Statistics.Cache.Cache;
import com.mcndsj.Lobby_Statistics.Cache.DataType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

/**
 * Created by Matthew on 2016/4/14.
 */
public class lobby_Statistics extends JavaPlugin{

    private ExecutorService pool ;
    private HashMap<String,Cache> map;
    private static lobby_Statistics instance;
    private Api api;
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new Listener(),this);
        pool = Executors.newFixedThreadPool(5);
        map = new HashMap();
        api = new Api();
        instance = this;

    }

    public Api getApi(){
        return api;
    }

    public static lobby_Statistics getInstance(){
        return instance;
    }


    public void getDataMap(final QueryCallBack call, final String player, final DataType type){
        pool.execute(new Runnable() {
            public void run() {
                synchronized (map) {
                    if (!map.containsKey(player)) {
                        map.put(player, new Cache(player, type));
                        call.onResult(map.get(player).getCache(type));
                    } else {
                        call.onResult(map.get(player).getCache(type));
                    }
                }
            }
        });


    }

    public void invalidCache(DataType type, String player){
        if(map.containsKey(player)){
            map.get(player).invalidCache(type);
        }
    }

    public void clearCache(String player){
        map.remove(player);
    }

    public void clearAll(){
        map.clear();
    }


}
