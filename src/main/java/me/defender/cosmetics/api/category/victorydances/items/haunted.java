package me.defender.cosmetics.api.category.victorydances.items;

import com.cryptomorin.xseries.XMaterial;
import me.defender.cosmetics.api.category.victorydances.VictoryDance;
import me.defender.cosmetics.api.category.shopkeeperskins.ShopKeeperHandler;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.Utility;
import me.defender.cosmetics.api.category.victorydances.util.UsefulUtilsVD;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class haunted extends VictoryDance {
    @Override
    public ItemStack getItem() {
        return XMaterial.GHAST_TEAR.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "haunted";
    }

    @Override
    public String getDisplayName() {
        return "Haunted";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7It's spooky time! The Victory", "&7Dance releases the souls of a", "&7bunch of poor players you killed", "&7in past games to celebrate your", "&7victory.");
    }

    @Override
    public int getPrice() {
        return 30000;
    }

    @Override
    public RarityType getRarity() {
        return RarityType.LEGENDARY;
    }

    @Override
    public void execute(Player winner) {
        new BukkitRunnable() {
            public void run() {
                if (ShopKeeperHandler.arenas.containsKey(winner.getWorld().getName())) {
                    final Location loc = UsefulUtilsVD.getRandomLocation(winner.getLocation(), 10);
                    final Bat bat = (Bat)winner.getWorld().spawnEntity(loc, EntityType.BAT);
                    bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 19999980, 1));
                    final ArmorStand stand = (ArmorStand)winner.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
                    stand.setVisible(false);
                    bat.setPassenger(stand);
                    bat.setNoDamageTicks(Integer.MAX_VALUE);
                    final ItemStack leather = new ItemStack(Material.LEATHER_CHESTPLATE);
                    final LeatherArmorMeta lm = (LeatherArmorMeta)leather.getItemMeta();
                    lm.setColor(Color.GRAY);
                    leather.setItemMeta(lm);
                    stand.setChestplate(leather);
                    stand.setHelmet(UsefulUtilsVD.gethead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhkMjE4MzY0MDIxOGFiMzMwYWM1NmQyYWFiN2UyOWE5NzkwYTU0NWY2OTE2MTllMzg1NzhlYTRhNjlhZTBiNiJ9fX0=", ""));
                }
                else {
                    this.cancel();
                }
            }
        }.runTaskTimer(Utility.plugin(), 1L, 8L);
    }
}
