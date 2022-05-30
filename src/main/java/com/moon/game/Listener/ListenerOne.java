package com.moon.game.Listener;

import com.moon.game.PVPgame;
import com.moon.game.State;
import com.moon.game.instance.Arena;
import com.moon.game.kits.KitType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenerOne implements Listener {

    private PVPgame pvpgame;
    public ListenerOne(PVPgame pvpgame) {
        this.pvpgame = pvpgame;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().contains("Kit Selection:") && e.getInventory() != null && e.getCurrentItem() != null) {
            e.setCancelled(true);
            KitType type = KitType.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
            Arena arena = pvpgame.getArenaManage().getArena(player);
            if (arena != null) {
                KitType activated = arena.getKitType(player);
                if (activated != null && activated == type) {
                    player.sendMessage(ChatColor.RED + "You already have a kit.");
                } else {
                    player.sendMessage(ChatColor.GREEN + "You have selected the " + type.getDisplay() + ChatColor.GREEN + " kit.");
                    arena.setKit(player.getUniqueId(), type);
                }
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Arena arena = pvpgame.getArenaManage().getArena(e.getEntity());
        if (arena != null && arena.getState().equals(State.RUNNING)) {
                arena.getGame().addPoint(e.getEntity());
        }
        {
            String killed = e.getEntity().getName();
            String killer = e.getEntity().getKiller().getName();
            e.setDeathMessage(ChatColor.RED + killed + "has been slain by" + killer);
        }
    }
}
