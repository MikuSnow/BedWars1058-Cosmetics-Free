package me.defender.cosmetics.api.category.bedbreakeffects.items;

import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.hakan.core.HCore;
import com.hakan.core.particle.Particle;
import com.hakan.core.particle.type.ParticleType;
import me.defender.cosmetics.api.category.bedbreakeffects.BedDestroy;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.Utility;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * Bed destroy effect.
 * Spawns a flying pig that you can ride!
 */
public class pigmissile extends BedDestroy {
    /** {@inheritDoc} */
    @Override
    public ItemStack getItem() {
        return XMaterial.COOKED_BEEF.parseItem();
    }
    /** {@inheritDoc} */
    @Override
    public String base64() {
        return null;
    }
    /** {@inheritDoc} */
    @Override
    public String getIdentifier() {
        return "pig-missile";
    }
    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return "Pig Missile";
    }
    /** {@inheritDoc} */
    @Override
    public List<String> getLore() {
        return Arrays.asList("&7Spawns a flying pig that", "&7you can ride!");
    }
    /** {@inheritDoc} */
    @Override
    public int getPrice() {
        return 5000;
    }
    /** {@inheritDoc} */
    @Override
    public RarityType getRarity() {
        return RarityType.COMMON;
    }
    /** {@inheritDoc} */
    @Override
    public void execute(Player player, Location bedLocation, ITeam victimTeam) {
        final Pig pig = (Pig)player.getWorld().spawnEntity(bedLocation, EntityType.PIG);
        final ArmorStand stand = (ArmorStand)player.getWorld().spawnEntity(bedLocation, EntityType.ARMOR_STAND);
        pig.setSaddle(true);
        stand.setGravity(false);
        stand.setPassenger(pig);
        stand.setVisible(false);
        new BukkitRunnable() {
            int i1 = 0;

            public void run() {
                ++this.i1;
                stand.eject();
                stand.teleport(stand.getLocation().add(0.0, 0.5, 0.0));
                stand.setPassenger(pig);
                Particle flame = new Particle(ParticleType.FLAME, 1, 0.0f, new Vector(0.0f, 0.0f, 0.0f));
                HCore.playParticle(player, stand.getLocation(), flame);
                XSound.ENTITY_CHICKEN_EGG.play(stand.getLocation(), 1.0f, 1.0f);
                if (this.i1 == 13) {
                    final Firework fw = stand.getWorld().spawn(stand.getLocation(), Firework.class);
                    final FireworkMeta fm = fw.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.BLACK).withFade(Color.BLACK).build());
                    fw.setFireworkMeta(fm);
                }
                if (this.i1 == 25) {
                    stand.remove();
                    pig.remove();
                    this.i1 = 0;
                    this.cancel();
                }
            }
        }.runTaskTimer(Utility.plugin(), 4L, 1L);
    }
}
