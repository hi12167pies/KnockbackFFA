package me.hi12167pies.kbffa.Utils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class ItemBuilder {
    ItemStack itemStack;
    ItemMeta meta;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        meta = itemStack.getItemMeta();
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder unBreak() {
        meta.spigot().setUnbreakable(true);
        return this;
    }

    public ItemBuilder hideUnBreak() {
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        return this;
    }

    public ItemBuilder hideAttr() {
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }

    public ItemBuilder ench(Enchantment[] enchantments, int[] levels) {
        for (int i = 0; i < enchantments.length; i++) {
            meta.addEnchant(enchantments[i], levels[i], true);
        }
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}