package com.moon.game.kits.Type;

import com.moon.game.PVPgame;
import com.moon.game.kits.Kit;
import com.moon.game.kits.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.UUID;

public class Teleporter extends Kit {
    public Teleporter(PVPgame pvpgame, UUID uuid) {
        super(pvpgame, KitType.TELEPORTER, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        player.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 5));
        player.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 5));
    }
}
