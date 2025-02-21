package me.defender.cosmetics.api.category.victorydances.items;

import com.cryptomorin.xseries.XMaterial;
import com.hakan.core.HCore;
import me.defender.cosmetics.api.category.victorydances.VictoryDance;
import me.defender.cosmetics.api.enums.RarityType;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class rainbowDolly extends VictoryDance {
    @Override
    public ItemStack getItem() {
        return XMaterial.WHITE_WOOL.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "rainbow-dolly";
    }

    @Override
    public String getDisplayName() {
        return "Rainbow Dolly";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Spawn Dolly the sheep, When you", "&7hit Dolly she, clones herself.");
    }

    @Override
    public int getPrice() {
        return 25000;
    }

    @Override
    public RarityType getRarity() {
        return RarityType.EPIC;
    }

    @Override
    public void execute(Player winner) {
        Sheep sheep1 = (Sheep) winner.getWorld().spawnEntity(winner.getLocation(), EntityType.SHEEP);
        sheep1.setColor(DyeColor.RED);
        sheep1.setCustomName("Dolly");
        sheep1.setNoDamageTicks(Integer.MAX_VALUE);

        // Event
        HCore.registerEvent(PlayerInteractAtEntityEvent.class).consume((event) -> {
            if (event.getRightClicked().getType() == EntityType.SHEEP) {
                Sheep sheep = (Sheep) event.getRightClicked();
                if (sheep.getCustomName() == null) {
                    return;
                }
                if (Objects.equals(sheep.getCustomName(), "Dolly")) {
                    final ArrayList<String> color = new ArrayList<String>();
                    color.add("RED");
                    color.add("BLUE");
                    color.add("BROWN");
                    color.add("GREEN");
                    color.add("PINK");
                    color.add("YELLOW");
                    color.add("GRAY");
                    color.add("LIME");
                    color.add("CYAN");
                    color.add("MAGENTA");
                    final int size = ThreadLocalRandom.current().nextInt(color.size());
                    final String colors = color.get(size);
                    final Sheep sheep2 = (Sheep)sheep.getWorld().spawnEntity(sheep.getLocation(), EntityType.SHEEP);
                    sheep2.getWorld().createExplosion(sheep2.getLocation(), 1.0f);
                    sheep2.setColor(DyeColor.valueOf(colors));
                    sheep2.setVelocity(sheep.getLocation().getDirection().multiply(-0.5).setY(1));
                    sheep2.setCustomName("Dolly");
                }
            }
        });

    }
}
