package com.moon.game.kits.Type;

import com.moon.game.PVPgame;
import com.moon.game.kits.Kit;
import com.moon.game.kits.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.UUID;

public class Sorcerer extends Kit {
    public Sorcerer(PVPgame pvpgame, UUID uuid) {
        super(pvpgame, KitType.SORCERER, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        player.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
    }

}
