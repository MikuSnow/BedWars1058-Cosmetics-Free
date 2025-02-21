package me.defender.cosmetics.api.category.killmessage.items;

import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import me.defender.cosmetics.api.category.killmessage.KillMessage;
import me.defender.cosmetics.api.enums.CosmeticsType;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.StringUtils;
import me.defender.cosmetics.api.configuration.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class KillMessageItems {


    public static void registerConfigItems(){
        ConfigurationSection section = CosmeticsType.KillMessage.getConfig().getYml().getConfigurationSection(CosmeticsType.KillMessage.getSectionKey());
        if(section == null) return;
        ConfigManager config = CosmeticsType.KillMessage.getConfig();

        for(String id : section.getKeys(false)){
            String path = CosmeticsType.KillMessage.getSectionKey() + "." + id + ".";
            KillMessage killMessage = new KillMessage() {
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
                    return List.of("&7Select the " + getDisplayName() + " Kill,", "&7Message for in-game chat", "&7messages!");
                }

                @Override
                public int getPrice() {
                    return config.getInt(path + "price");
                }

                @Override
                public RarityType getRarity() {
                    return RarityType.valueOf(config.getString(path + "rarity").toUpperCase());
                }

                // Do nothing because API currently doesn't support it
                @Override
                public String execute(Player player, PlayerKillEvent.PlayerKillCause killCause) {
                    return null;
                }
            };
            killMessage.register();
        }
    }
}
