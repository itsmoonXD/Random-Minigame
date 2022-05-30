package com.moon.game.Command;

import com.moon.game.PVPgame;
import com.moon.game.State;
import com.moon.game.instance.Arena;
import com.moon.game.kits.KitGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private PVPgame pvpgame;
    public ArenaCommand(PVPgame pvpgame) {
        this.pvpgame = pvpgame;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                player.sendMessage(ChatColor.AQUA + "Available arenas:");
                for (Arena arena : pvpgame.getArenaManage().getArenas()) {
                    player.sendMessage(ChatColor.WHITE + "- " + arena.getId() + "(" + arena.getState().name() + ")");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("kit")) {
                Arena arena = pvpgame.getArenaManage().getArena(player);
                if (arena != null) {
                    if (arena.getState() != State.RUNNING) {
                        new KitGUI(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "You can not select a kit.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in an arena.");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("quit")) {
                Arena arena = pvpgame.getArenaManage().getArena(player);
                if (arena != null) {
                    player.sendMessage(ChatColor.RED + "You left the arena.");
                    arena.removePlayer(player);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in an arena.");
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
                if (pvpgame.getArenaManage().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED + "You are already in an arena.");
                    return false;
                }
                int id;
                try {
                    id = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "You need a specific arena id.");
                    return false;
                }
                if (id >= 0 && id < pvpgame.getArenaManage().getArenas().size()) {
                    Arena arena = pvpgame.getArenaManage().getArena(id);
                    if (arena.getState() == State.PLAYERS || arena.getState() == State.COUNTDOWN) {
                        player.sendMessage(ChatColor.GREEN + "You are now in Arena" + id + ".");
                        arena.addPlayer(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "You can not join right now.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You need a specific arena id.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Not a valid command.");
                player.sendMessage(ChatColor.RED + "- /arena list");
                player.sendMessage(ChatColor.RED + "- /arena quit");
                player.sendMessage(ChatColor.RED + "- /arena join <id>");
                player.sendMessage(ChatColor.RED + "- /arena kit");
            }
        }
        return false;
    }
}
