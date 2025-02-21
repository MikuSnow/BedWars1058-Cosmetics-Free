package me.defender.cosmetics.api.category.victorydances.items;

import com.cryptomorin.xseries.XMaterial;
import me.defender.cosmetics.api.category.victorydances.VictoryDance;
import me.defender.cosmetics.api.category.victorydances.util.UsefulUtilsVD;
import me.defender.cosmetics.api.category.shopkeeperskins.ShopKeeperHandler;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.Utility;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class floatingLantern extends VictoryDance {
    @Override
    public ItemStack getItem() {
        return XMaterial.PLAYER_HEAD.parseItem();
    }

    @Override
    public String base64() {
        return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRjNzM2ODY0YTY0OWY2NjVmMDRmMjhiYjE0YTNjNGVhMGYyNDVlODQ2MDE3YWNmZTM2NmU3ZDEzNWI0ZmNhOCJ9fX0=";
    }

    @Override
    public String getIdentifier() {
        return "floating-lantern";
    }

    @Override
    public String getDisplayName() {
        return "Floating Lantern";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Spawn floating lanterns to", "&7brighten up the night.");
    }

    @Override
    public int getPrice() {
        return 30000;
    }

    @Override
    public RarityType getRarity() {
        return RarityType.RARE;
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
                    stand.setHelmet(UsefulUtilsVD.gethead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRjNzM2ODY0YTY0OWY2NjVmMDRmMjhiYjE0YTNjNGVhMGYyNDVlODQ2MDE3YWNmZTM2NmU3ZDEzNWI0ZmNhOCJ9fX0=", ""));
                }
                else {
                    this.cancel();
                }
            }
        }.runTaskTimer(Utility.plugin(), 1L, 8L);
    }
}
