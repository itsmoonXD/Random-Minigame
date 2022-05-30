package com.moon.game.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum KitType {

    TELEPORTER(ChatColor.GOLD + "Teleporter", Material.ENDER_EYE, "Teleporting kit."),
    SORCERER(ChatColor.GOLD + "Sorcerer", Material.END_ROD, "Lots of effects.");

    private String display, description;
    private Material material;

    KitType(String display, Material material, String description) {
        this.display = display;
        this.material = material;
        this.description = description;
    }
    public String getDisplay() { return display; }
    public Material getMaterial() { return material; }
    public String getDescription() { return description; }
}
