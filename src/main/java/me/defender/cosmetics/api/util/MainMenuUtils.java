

package me.defender.cosmetics.api.util;

import me.defender.cosmetics.api.BwcAPI;
import me.defender.cosmetics.api.enums.CosmeticsType;
import me.defender.cosmetics.menu.CategoryMenu;
import me.defender.cosmetics.database.PlayerOwnedData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenuUtils {

    public static void saveLores() {
        List<String> sprays = Arrays.asList("&7Select a spray to show off all", "&7over the place! Sprays slot can", "&7be found on every spawn islands", "&7and some center islands.", "", "&7Unlocked:&a {ownedSpray}","&7Currently Selected:", "{spray}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Sprays.lore", sprays);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Sprays.name", "&aSprays");

        List<String> pts = Arrays.asList("&7Change your projectile particle", "&7trail effects.", "", "&7Unlocked:&a {ownedpt}","&7Currently Selected:", "{projectile}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Projectile-Trails.lore", pts);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Projectile-Trails.name", "&aProjectile Trails");

        List<String> finalke = Arrays.asList("&7A selection of various effects", "&7to chosse from that will trigger", "&7whenever you final kill an", "&7enemy!", "", "&7Unlocked:&a {ownedfinalkill}","&7Currently Selected:", "&a" + "{finalkill}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.FinalKill-Effects.lore", finalke);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.FinalKill-Effects.name", "&aFinal Kill Effects");

        List<String> km = Arrays.asList("&7Select a Kill Message package to", "&7replace chat messages when you", "&7kill players.", "", "&7Unlocked:&a {ownedkm}","&7Currently Selected:", "{killmsg}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Kill-Messages.lore", km);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Kill-Messages.name", "&aKill Messages");

        List<String> gly = Arrays.asList("&7Select a Glyph image which will", "&7appear when picking up diamonds and", "&7emeralds!", "", "&7Unlocked:&a {ownedgly}","&7Currently Selected:", "{glyphs}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Glyphs.lore", gly);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Glyphs.name", "&aGlyphs");

        List<String> bbe = Arrays.asList("&7Select from various Bed Destroy", "&7effects, which will occur when you", "&7break a bed!", "", "&7Unlocked:&a {ownedbbe}","&7Currently Selected:", "{bedbreak}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Bed-Destroys.lore", bbe);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Bed-Destroys.name", "&aBed Destroys");

        List<String> ws = Arrays.asList("&7Change the skin of wood", "&7in-game.", "", "&7Unlocked:&a {ownedws}","&7Currently Selected:", "{woodskin}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.WoodSkins.lore", ws);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.WoodSkins.name", "&aWood Skins");

        List<String> vd = Arrays.asList("&7Celebrate by gloating and", "&7showing off to other players", "&7whenever you win!", "", "&7Unlocked:&a {ownedvd}","&7Currently Selected:", "{victory}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Victory-Dances.lore", vd);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Victory-Dances.name", "&aVictory Dances");

        List<String> islandtoppers = Arrays.asList("&7Select an Island Topper to", "&7decorate your island with! In", "&7Doubles and Teams Mode a random", "&7player's choice from each team is choosen.","", "&7Unlocked:&a {ownedit}", "&7Currently Selected:", "&a{islandtopper}", "", "&eClick to select.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Island-Toppers.lore", islandtoppers);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Island-Toppers.name", "&aIsland Toppers");

        List<String> shopkeepers = Arrays.asList("&7Select from various ShopKeeper", "&7skin, which will replace how the", "&7ShopKeeper look in-game! In", "&7Doubles and Teams Mode a random", "&7player's choice from each team", "&7is choosen.", "", "&7Unlocked:&a {ownedshopkeeper}","&7Currently Selected:", "{shopkeeper}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.ShopKeeperSkins.lore", shopkeepers);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.ShopKeeperSkins.name", "&aShopKeeperSkins");

        List<String> dc = Arrays.asList("&7Let others know just how salty", "&7your tears are every time you", "&7die with these death cries!", "","&7Unlocked:&a {owneddc}","&7Currently Selected:", "{deathcries}", "", "&eClick to view.");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Death-Cries.lore", dc);
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Death-Cries.name", "&aDeath Cries");

        Utility.saveIfNotExistsLang("cosmetics.main-menu.Back.name", "&aBack");
        Utility.saveIfNotExistsLang("cosmetics.main-menu.Back.lore", List.of("&7Click to go back!"));

    	}


    public static List<String> formatLore(List<String> lores, Player p){
        BwcAPI api = new BwcAPI();
        PlayerOwnedData ownedData = Utility.playerOwnedDataList.get(p.getUniqueId());

        lores = lores.stream()
                .map(s -> s.replace("{islandtopper}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.IslandTopper))))
                .map(s -> s.replace("{spray}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.Sprays))))
                .map(s -> s.replace("{killmsg}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.KillMessage))))
                .map(s -> s.replace("{shopkeeper}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.ShopKeeperSkin))))
                .map(s -> s.replace("{woodskin}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.WoodSkins))))
                .map(s -> s.replace("{victory}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.VictoryDances))))
                .map(s -> s.replace("{deathcries}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.DeathCries))))
                .map(s -> s.replace("{glyphs}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.Glyphs))))
                .map(s -> s.replace("{bedbreak}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.BedBreakEffects))))
                .map(s -> s.replace("{projectile}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.ProjectileTrails))))
                .map(s -> s.replace("{finalkill}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.FinalKillEffects))))
                .map(s -> s.replace("{islandtopper}", "&a" + StringUtils.replaceHyphensAndCaptalizeFirstLetter(api.getSelectedCosmetic(p, CosmeticsType.IslandTopper))))
                .map(s -> s.replace("{ownedSpray}", "&a" + ownedData.getSpray() + "/" + StartupUtils.sprayList.size() + " &8(" + ((ownedData.getSpray() / StartupUtils.sprayList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedpt}", "&a" + ownedData.getProjectileTrail() + "/" + StartupUtils.projectileTrailList.size() + " &8(" + ((ownedData.getProjectileTrail() / StartupUtils.projectileTrailList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedfinalkill}", "&a" + ownedData.getFinalKillEffect() + "/" + StartupUtils.finalKillList.size() + " &8(" + ((ownedData.getFinalKillEffect() / StartupUtils.finalKillList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedkm}", "&a" + ownedData.getKillMessage() + "/" + StartupUtils.killMessageList.size() + " &8(" + ((ownedData.getKillMessage() / StartupUtils.killMessageList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedgly}", "&a" + ownedData.getGlyph() + "/" + StartupUtils.glyphsList.size() + " &8(" + ((ownedData.getGlyph() / StartupUtils.glyphsList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedbbe}", "&a" + ownedData.getBedDestroy() + "/" + StartupUtils.bedDestroyList.size() + " &8(" + ((ownedData.getBedDestroy() / StartupUtils.bedDestroyList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedws}", "&a" + ownedData.getWoodSkin() + "/" + StartupUtils.woodSkinsList.size() + " &8(" + ((ownedData.getWoodSkin() / StartupUtils.woodSkinsList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedvd}", "&a" + ownedData.getVictoryDance() + "/" + StartupUtils.victoryDancesList.size() + " &8(" + ((ownedData.getVictoryDance() / StartupUtils.victoryDancesList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedit}", "&a" + ownedData.getIslandTopper() + "/" + StartupUtils.islandTopperList.size() + " &8(" + ((ownedData.getIslandTopper() / StartupUtils.islandTopperList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{ownedshopkeeper}", "&a" + ownedData.getShopkeeperSkin() + "/" + StartupUtils.shopKeeperSkinList.size() + " &8(" + ((ownedData.getShopkeeperSkin() / StartupUtils.shopKeeperSkinList.size()) * 100) + "%)" ))
                .map(s -> s.replace("{owneddc}", "&a" + ownedData.getDeathCry() + "/" + StartupUtils.deathCryList.size() + " &8(" + ((ownedData.getDeathCry() / StartupUtils.deathCryList.size()) * 100) + "%)" ))
                .collect(Collectors.toList());
        return lores;
    }

    public static void openMenus(Player p, String name){
        switch (name){
            case "Sprays":
                new CategoryMenu(CosmeticsType.Sprays).open(p);
                break;
            case "Projectile-Trails":
                new CategoryMenu(CosmeticsType.ProjectileTrails).open(p);
                break;
            case "FinalKill-Effects":
                new CategoryMenu(CosmeticsType.FinalKillEffects).open(p);;
                break;
            case "Kill-Messages":
                new CategoryMenu(CosmeticsType.KillMessage).open(p);
                break;
            case "Glyphs":
                new CategoryMenu(CosmeticsType.Glyphs).open(p);
                break;
            case "Bed-Destroys":
                new CategoryMenu(CosmeticsType.BedBreakEffects).open(p);
                break;
            case "WoodSkins":
                new CategoryMenu(CosmeticsType.WoodSkins).open(p);
                break;
            case "Victory-Dances":
                new CategoryMenu(CosmeticsType.VictoryDances).open(p);
                break;
            case "Island-Toppers":
                new CategoryMenu(CosmeticsType.IslandTopper).open(p);
                break;
            case "ShopKeeperSkins":
                new CategoryMenu(CosmeticsType.ShopKeeperSkin).open(p);
                break;
            case "Death-Cries":
                new CategoryMenu(CosmeticsType.DeathCries).open(p);
                break;
            case "Back":
                String command = Utility.plugin().menuData.getConfig().getString("Main-Menu.Back.custom-command");
                if(command == null) {
                    p.getOpenInventory().close();
                }else{
                    Bukkit.dispatchCommand(p, command);
                }
                break;

        }
    }
}
