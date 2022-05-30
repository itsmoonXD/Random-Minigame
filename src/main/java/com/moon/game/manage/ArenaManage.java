package com.moon.game.manage;

import com.moon.game.instance.Arena;
import com.moon.game.PVPgame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public class ArenaManage {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManage(PVPgame pvpgame) {
        FileConfiguration config = pvpgame.getConfig();
        for (String s : config.getConfigurationSection("arenas.").getKeys(false)) {
            arenas.add(new Arena(pvpgame, Integer.parseInt(s), new Location(
                    Bukkit.getWorld(config.getString("arenas." + s + ".world")),
                    config.getDouble("arenas." + s + ".x"),
                    config.getDouble("arenas." + s + ".y"),
                    config.getDouble("arenas." + s + ".z"),
                    (float) config.getDouble("arenas." + s + ".yaw"),
                    (float) config.getDouble("arenas." + s + ".pitch"))));
        }
    }
    public List<Arena> getArenas() { return arenas; }
    public Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayers().contains(player.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }
    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }
}
