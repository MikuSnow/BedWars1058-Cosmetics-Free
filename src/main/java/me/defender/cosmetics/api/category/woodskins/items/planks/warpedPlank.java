package me.defender.cosmetics.api.category.woodskins.items.planks;

import com.cryptomorin.xseries.XMaterial;
import me.defender.cosmetics.api.category.woodskins.WoodSkin;
import me.defender.cosmetics.api.enums.RarityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class warpedPlank extends WoodSkin {
    @Override
    public ItemStack getItem() {
        return XMaterial.WARPED_PLANKS.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "warped-plank";
    }

    @Override
    public String getDisplayName() {
        return "Warped Plank";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Select the Warped Plank Wood Skin", "&7to be used when placing wood", "&7blocks.");
    }

    @Override
    public int getPrice() {
        return 60000;
    }

    @Override
    public RarityType getRarity() {
        return RarityType.RARE;
    }

    @Override
    public ItemStack woodSkin() {
        return XMaterial.WARPED_PLANKS.parseItem();
    }
}
