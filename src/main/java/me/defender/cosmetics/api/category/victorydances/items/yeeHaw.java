package me.defender.cosmetics.api.category.victorydances.items;

import com.cryptomorin.xseries.XMaterial;
import com.hakan.core.HCore;
import me.defender.cosmetics.api.category.victorydances.VictoryDance;
import me.defender.cosmetics.api.category.shopkeeperskins.ShopKeeperHandler;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.Utility;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class yeeHaw extends VictoryDance {
    @Override
    public ItemStack getItem() {
        return XMaterial.SADDLE.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "yee-haw";
    }

    @Override
    public String getDisplayName() {
        return "Yee Haw";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Ride a horse like it's 1876.");
    }

    @Override
    public int getPrice() {
        return 5000;
    }

    @Override
    public RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override
    public void execute(Player winner) {
        Horse horse = (Horse) winner.getWorld().spawnEntity(winner.getLocation(), EntityType.HORSE);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setJumpStrength(10.0);
        horse.setPassenger(winner);
        horse.setNoDamageTicks(Integer.MAX_VALUE);
        horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 190000000, 3));
        new BukkitRunnable() {
            public void run() {
                if (ShopKeeperHandler.arenas.containsKey(winner.getWorld().getName())) {
                    if (horse.getPassenger() != winner) {
                        horse.remove();
                    }
                }
                else {
                    horse.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(Utility.plugin(), 0L, 1L);

        // Horse Hit Event
        HCore.registerEvent(EntityDamageEvent.class).filter(event -> !horse.isDead()).consume((event) -> {
           if(event.getEntity() == horse){
               event.setCancelled(true);
           }
        });
    }
}
