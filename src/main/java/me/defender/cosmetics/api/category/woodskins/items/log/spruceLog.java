package me.defender.cosmetics.api.category.woodskins.items.log;

import com.cryptomorin.xseries.XMaterial;
import me.defender.cosmetics.api.category.woodskins.WoodSkin;
import me.defender.cosmetics.api.enums.RarityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class spruceLog extends WoodSkin {
    @Override
    public ItemStack getItem() {
        return XMaterial.SPRUCE_LOG.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "spruce-log";
    }

    @Override
    public String getDisplayName() {
        return "Spruce Log";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Select the Spruce Log Wood Skin", "&7to be used when placing wood", "&7blocks.");
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
        return XMaterial.SPRUCE_LOG.parseItem();
    }
}
