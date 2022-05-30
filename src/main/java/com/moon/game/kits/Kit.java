package com.moon.game.kits;

import com.moon.game.PVPgame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import java.util.UUID;

public abstract class Kit implements Listener {
    private KitType type;
    private UUID uuid;
    public Kit(PVPgame pvpgame, KitType type, UUID uuid) {
        this.type = type;
        this.uuid = uuid;

        Bukkit.getPluginManager().registerEvents(this, pvpgame);

    }
    public UUID getUUID() { return uuid; }
    public KitType getType() { return type; }
    public abstract void onStart(Player player);
    public void remove() {
        HandlerList.unregisterAll(this);
    }

}
