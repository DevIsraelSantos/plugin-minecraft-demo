package br.com.israel.inventories;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import br.com.israel.models.PlayerStats;
import net.kyori.adventure.text.Component;

public class StatsInventory {

    public static final String TITLE = "Perfil do Herói";

    public void open(Player player, PlayerStats stats) {

        Inventory inventory = Bukkit.createInventory(null, 27, Component.text(TITLE));

        fillBorder(inventory);

        inventory.setItem(11, createItem(Material.DIAMOND_PICKAXE, "§6⛏ Blocos Quebrados",
                List.of("§fTotal: §a" + stats.getBlocksBroken())));

        inventory.setItem(13,
                createItem(Material.GOLDEN_APPLE, "§6🏆 Par ou Ímpar", List.of("§fVitórias: §a" + stats.getWins(),
                        "§fDerrotas: §c" + stats.getLosses())));

        inventory.setItem(15,
                createItem(Material.REDSTONE_TORCH, "§6📺 Scoreboard",
                        List.of("§fStatus: " + (stats.isScoreboardEnabled() ? "§aLigado" : "§cDesligado"), "",
                                "§eClique para alternar")));

        inventory.setItem(22,
                createItem(Material.NETHER_STAR, "§6📈 Taxa de Vitória", List.of("§f" + stats.getPercentage())));

        player.openInventory(inventory);
    }

    private void fillBorder(Inventory inventory) {
        ItemStack item = new ItemStack(
                Material.BROWN_STAINED_GLASS_PANE);

        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(" "));
        item.setItemMeta(meta);

        int[] slots = {
                0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 17,
                18, 19, 20, 21, 23, 24, 25, 26
        };

        for (int slot : slots) {
            inventory.setItem(slot, item);
        }
    }

    private ItemStack createItem(
            Material material,
            String name,
            List<String> lore) {

        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }
}
