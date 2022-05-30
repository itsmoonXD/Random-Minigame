package com.moon.game.Listener;

import com.moon.game.PVPgame;
import com.moon.game.instance.Arena;
import com.moon.game.manage.ConfigManage;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerTwo implements Listener {

    private PVPgame pvpgame;
    public ListenerTwo(PVPgame pvpgame) {
        this.pvpgame = pvpgame;
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
        e.getPlayer().teleport(ConfigManage.getLobbySpawn());
        e.getPlayer().setPlayerListHeaderFooter(ChatColor.GOLD + "Minigame", "MoonXD");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Arena arena = pvpgame.getArenaManage().getArena(e.getPlayer());
        if (arena != null) {
            arena.removePlayer(e.getPlayer());
        }
    }
}
