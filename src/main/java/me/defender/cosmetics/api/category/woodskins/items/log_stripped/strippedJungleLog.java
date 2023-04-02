package me.defender.cosmetics.api.category.woodskins.items.log_stripped;

import com.cryptomorin.xseries.XMaterial;
import me.defender.cosmetics.api.category.woodskins.WoodSkin;
import me.defender.cosmetics.api.enums.RarityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class strippedJungleLog extends WoodSkin {
    @Override
    public ItemStack getItem() {
        return XMaterial.STRIPPED_JUNGLE_LOG.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "stripped-jungle-log";
    }

    @Override
    public String getDisplayName() {
        return "Stripped Jungle Log";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Select the Stripped Jungle Log Wood Skin", "&7to be used when placing wood", "&7blocks.");
    }

    @Override
    public int getPrice() {
        return 60000;
    }

    @Override
    public RarityType getRarity() {
        return RarityType.LEGENDARY;
    }

    @Override
    public ItemStack woodSkin() {
        return XMaterial.STRIPPED_JUNGLE_LOG.parseItem();
    }
}
