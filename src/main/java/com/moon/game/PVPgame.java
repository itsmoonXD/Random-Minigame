package com.moon.game;

import com.moon.game.Command.ArenaCommand;
import com.moon.game.Listener.ListenerOne;
import com.moon.game.Listener.ListenerTwo;
import com.moon.game.manage.ArenaManage;
import com.moon.game.manage.ConfigManage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PVPgame extends JavaPlugin {

    private ArenaManage arenaManage;

    @Override
    public void onEnable() {
        ConfigManage.configsetup(this);
        arenaManage = new ArenaManage(this);
        Bukkit.getPluginManager().registerEvents(new ListenerOne(this), this);
        Bukkit.getPluginManager().registerEvents(new ListenerTwo(this), this);

        getCommand("arena").setExecutor(new ArenaCommand(this));
    }
    public ArenaManage getArenaManage() { return arenaManage; };
}
