package com.moon.game.instance;

import com.moon.game.State;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;
    public Game(Arena arena) {
        this.arena = arena;
    }
    public void start() {
        arena.setState(State.RUNNING);
        arena.sendMessage(ChatColor.GREEN + "This game has started, kill your opponent to complete the game.");
        for (UUID uuid : arena.getKits().keySet()) {
            arena.getKits().get(uuid).onStart(Bukkit.getPlayer(uuid));
        }
        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
            Bukkit.getPlayer(uuid).closeInventory();
        }
    }
    public void addPoint(Player player) {
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if (playerPoints == 20) {
            arena.sendMessage(ChatColor.GREEN + player.getName() + " has won.");
            arena.reset(true);
            return;
        }
        player.sendMessage(ChatColor.GREEN + "You have killed your opponent.");
        points.replace(player.getUniqueId(), playerPoints);
    }
}
