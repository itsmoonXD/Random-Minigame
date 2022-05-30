package com.moon.game.instance;

import com.moon.game.PVPgame;
import com.moon.game.State;
import com.moon.game.manage.ConfigManage;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private PVPgame pvpgame;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(PVPgame pvpgame, Arena arena) {
        this.pvpgame = pvpgame;
        this.arena = arena;
        this.countdownSeconds = ConfigManage.getCountdownSeconds();
    }

    public void start() {
        arena.setState(State.COUNTDOWN);
        runTaskTimer(pvpgame, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            cancel();
            arena.start();
            arena.sendTitle("", "");
            return;
        }
        if (countdownSeconds <= 10 || countdownSeconds % 15 == 0) {
            arena.sendMessage(ChatColor.RED + "Game starts in" + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".");
        }
        arena.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s") + ".", ChatColor.GRAY + "until game starts");

        countdownSeconds--;
    }
}
