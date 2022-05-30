package com.moon.game.instance;

import com.moon.game.PVPgame;
import com.moon.game.State;
import com.moon.game.kits.Kit;
import com.moon.game.kits.KitType;
import com.moon.game.kits.Type.Sorcerer;
import com.moon.game.kits.Type.Teleporter;
import com.moon.game.manage.ConfigManage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private PVPgame pvpgame;
    private int id;
    private Location spawn;
    private State state;
    private List<UUID> players;
    private HashMap<UUID, Kit> kits;
    private Countdown countdown;
    private Game game;

    public Arena(PVPgame pvpgame, int id, Location spawn) {
        this.pvpgame = pvpgame;
        this.id = id;
        this.spawn = spawn;
        this.state = State.PLAYERS;
        this.players = new ArrayList<>();
        this.kits = new HashMap<>();
        this.countdown = new Countdown(pvpgame, this);
        this.game = new Game(this);
    }

    public void start() {
        game.start();
    }

    public void reset(boolean kickPlayers) {
        if (kickPlayers) {
            Location loc = ConfigManage.getLobbySpawn();
            for (UUID uuid : players) {
                Player player = Bukkit.getPlayer(uuid);
                player.teleport(loc);
                removeKit(player.getUniqueId());
            }
            players.clear();
        }
        kits.clear();
        sendTitle("", "");
        countdown.cancel();
        countdown = new Countdown(pvpgame, this);
        game = new Game(this);
    }

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }
    public void sendTitle(String title, String subtitle) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }

    public void addPlayer (Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);
        player.sendMessage(ChatColor.AQUA + "Choose your kit '/arena kit'.");

        if (state.equals(State.PLAYERS) && players.size() >= ConfigManage.getRequiredPlayers()) {
            countdown.start();
        }
    }
    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManage.getLobbySpawn());
        player.sendTitle("", "");
        removeKit(player.getUniqueId());
        if (state == State.COUNTDOWN && players.size() < ConfigManage.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "Not enough players.");
            reset(false);
            return;
        }
        if (state == State.RUNNING && players.size() < ConfigManage.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "Not enough players.");
            reset(false);
        }
    }

    public int getId() {return id; }
    public State getState() {return state; }
    public List<UUID> getPlayers() {return players; }
    public Game getGame() { return game; }
    public void setState(State state) {
        this.state = state;
    }
    public HashMap<UUID, Kit> getKits() { return kits; }
    public void removeKit(UUID uuid) {
        if (kits.containsKey(uuid)) {
            kits.get(uuid).remove();
            kits.remove(uuid);
        }
    }
    public void setKit(UUID uuid, KitType type) {
        removeKit(uuid);
        if (type == KitType.TELEPORTER) {
            kits.put(uuid, new Teleporter(pvpgame, uuid));
        } else if (type == KitType.SORCERER) {
            kits.put(uuid, new Sorcerer(pvpgame, uuid));

        }
    }
    public KitType getKitType(Player player) {
        return kits.containsKey(player.getUniqueId()) ? kits.get(player.getUniqueId()).getType() : null;
    }
}
