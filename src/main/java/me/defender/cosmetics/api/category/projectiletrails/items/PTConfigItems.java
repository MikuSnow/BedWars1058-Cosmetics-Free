package me.defender.cosmetics.api.category.projectiletrails.items;

import me.defender.cosmetics.api.category.projectiletrails.ProjectileTrail;
import me.defender.cosmetics.api.enums.CosmeticsType;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.StringUtils;
import me.defender.cosmetics.api.configuration.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PTConfigItems {

    public void registerConfigItems(){
        ConfigurationSection section = CosmeticsType.ProjectileTrails.getConfig().getYml().getConfigurationSection(CosmeticsType.ProjectileTrails.getSectionKey());
        if(section == null) return;
        ConfigManager config = CosmeticsType.ProjectileTrails.getConfig();
        for(String id : section.getKeys(false)){
            String path = CosmeticsType.ProjectileTrails.getSectionKey() + "." + id + ".";
            ProjectileTrail trails = new ProjectileTrail() {
                @Override
                public ItemStack getItem() {
                    return config.getItemStack(path + "item");
                }

                @Override
                public String base64() {
                    return null;
                }

                @Override
                public String getIdentifier() {
                    return id;
                }

                @Override
                public String getDisplayName() {
                    return StringUtils.replaceHyphensAndCaptalizeFirstLetter(id);
                }

                @Override
                public List<String> getLore() {
                    if(getRarity() == RarityType.NONE){
                        return List.of("&7Selecting this option disables your", "&7Projectile Trail.");
                    }
                    return List.of("&7Select " + getDisplayName() + " as your Projectile,", "&7trail!");
                }

                @Override
                public int getPrice() {
                    return config.getInt(path + "price");
                }

                @Override
                public RarityType getRarity() {
                    return RarityType.valueOf(config.getString(path + "rarity").toUpperCase().toUpperCase());
                }

                // Does nothing because API doesn't support it yet.
                @Override
                public String execute(Player player) {
                    return null;
                }
            };
            trails.register();
        }
    }
}
