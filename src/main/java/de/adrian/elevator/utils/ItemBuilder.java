package de.adrian.elevator.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {
        private ItemMeta itemMeta;
        private ItemStack itemStack;
        public ItemBuilder(Material mat){
            itemStack = new ItemStack(mat);
            itemMeta = itemStack.getItemMeta();
        }
        public ItemBuilder setDisplayname(String s){
            itemMeta.setDisplayName(s);
            return this;
        }
        public ItemBuilder setLocalizedName(String s){
            itemMeta.setLocalizedName(s);
            return this;
        }
        public ItemBuilder setLore(String... s){
            itemMeta.setLore(Arrays.asList(s));
            return this;
        }
        public ItemBuilder setUnbreakable(boolean s){
            itemMeta.setUnbreakable(s);
            return this;
        }
        public ItemBuilder addEnchantment(Enchantment enchantment, int lvl, boolean ignore) {
            itemMeta.addEnchant(enchantment,lvl,ignore);
            return this;
        }
        public ItemBuilder addItemFlags(ItemFlag... s){
            itemMeta.addItemFlags(s);
            return this;
        }
        public ItemBuilder setenchanted(boolean s) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            itemMeta.addEnchant(Enchantment.ARROW_INFINITE,1,true);
            return this;
        }
        public ItemBuilder setamount(int s) {
            itemStack.setAmount(s);
            return this;
        }
        public ItemBuilder setCustomModelData(int s) {
            itemMeta.setCustomModelData(s);
            return this;
        }

        @Override
        public String toString() {
            return "ItemBuilder{" +
                    "itemMeta=" + itemMeta +
                    ", itemStack=" + itemStack +
                    '}';
        }
        public ItemStack build(){
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
}
