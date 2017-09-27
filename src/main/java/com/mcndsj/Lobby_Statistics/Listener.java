package com.mcndsj.Lobby_Statistics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Matthew on 10/06/2016.
 */
public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent evt){
        lobby_Statistics.getInstance().getApi().clearCache(evt.getPlayer().getName());
    }
}
