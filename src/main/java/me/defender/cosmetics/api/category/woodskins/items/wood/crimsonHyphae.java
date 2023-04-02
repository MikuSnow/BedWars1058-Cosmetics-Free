package me.defender.cosmetics.api.category.woodskins.items.wood;

import com.cryptomorin.xseries.XMaterial;
import me.defender.cosmetics.api.category.woodskins.WoodSkin;
import me.defender.cosmetics.api.enums.RarityType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class crimsonHyphae extends WoodSkin {
    @Override
    public ItemStack getItem() {
        return XMaterial.CRIMSON_HYPHAE.parseItem();
    }

    @Override
    public String base64() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "crimson-hyphae";
    }

    @Override
    public String getDisplayName() {
        return "Crimson Hyphae";
    }

    @Override
    public List<String> getLore() {
        return List.of("&7Select the Crimson Hyphae Wood Skin", "&7to be used when placing wood", "&7blocks.");
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
        return XMaterial.CRIMSON_HYPHAE.parseItem();
    }
}
