package me.defender.cosmetics.api.category.killmessage.util;

import com.andrei1058.bedwars.api.language.Messages;
import com.hakan.core.utils.ColorUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import me.defender.cosmetics.api.BwcAPI;
import me.defender.cosmetics.api.category.killmessage.KillMessage;
import me.defender.cosmetics.api.enums.CosmeticsType;
import me.defender.cosmetics.api.enums.FieldsType;
import me.defender.cosmetics.api.enums.RarityType;
import me.defender.cosmetics.api.util.StartupUtils;
import me.defender.cosmetics.api.configuration.ConfigUtils;
import me.defender.cosmetics.api.util.Utility;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class KillMessageUtils {

    /**
     * Check if list is empty for id kill message
     *
     * @param id   selected or ID for the kill message, example is "none"
     * @param type Accepted values are, PvP, Explosion, Shoot and Void
     * @return true if exists, false otherwise.
     */
    public static boolean exists(String id, String type) {
        List<String> messages = ConfigUtils.getKillMessages().getYml().getStringList(CosmeticsType.KillMessage.getSectionKey() + "." + id + "." + type + "-Kill");
        return !messages.isEmpty();
    }

    /**
     * Check if a given id of kill message is none
     * @param id ID for the kill message
     * @return true if it is None, false if it isn't
     */
    public static boolean isNone(String id){
        for (KillMessage killMessage : StartupUtils.killMessageList) {
            if (killMessage.getIdentifier().equals(id)) {
                if (killMessage.getField(FieldsType.RARITY, null) == RarityType.NONE) return true;
            }
        }
        return false;
    }

    /**
     * Sends a kill message or preview message to a player based on the type of death.
     *
     * @param player            The player to send the message to
     * @param victim            The name of the victim
     * @param killer            The player object of the killer
     * @param finalKill         A flag indicating whether this was the final kill
     * @param victimColor       The color to use for the victim's name
     * @param killerColor       The color to use for the killer's name
     * @param type              The type of death. Accepted values: "PvP", "Void", "Shoot", "Explosion"
     * @param preview           If true, it will send all messages from the list.
     * @param previewID         leave null if not preview, if preview than pass the id for example, none.
     * @param previewKillerName leave null if not preview, if preview than pass the name for killer.
     * @param oldMessage        never used, leave as null.
     */
    public static void sendKillMessage(Player player, String victim, Player killer, boolean finalKill, ChatColor victimColor, ChatColor killerColor, String type, String oldMessage, boolean preview, String previewID, String previewKillerName) {
        String selectedMessage = new BwcAPI().getSelectedCosmetic(killer, CosmeticsType.KillMessage);
        List<String> messages;
        if (preview) {
            messages = ConfigUtils.getKillMessages().getYml().getStringList(CosmeticsType.KillMessage.getSectionKey() + "." + previewID + "." + type + "-Kill");
        } else {
            messages = ConfigUtils.getKillMessages().getYml().getStringList(CosmeticsType.KillMessage.getSectionKey() + "." + selectedMessage + "." + type + "-Kill");
        }
        for (KillMessage killMessage : StartupUtils.killMessageList) {
            if (preview) {
                if (killMessage.getIdentifier().equals(previewID)) {
                    if (killMessage.getField(FieldsType.RARITY, player) != RarityType.NONE) {
                        for (String message : messages) {
                            player.sendMessage(ColorUtil.colored(message
                                    .replace("{killer}", killerColor + previewKillerName)
                                    .replace("{victim}", victimColor + victim)));
                        }
                    }
                    return;
                }
            }
            if (killMessage.getIdentifier().equals(selectedMessage)) {
                if (killMessage.getField(FieldsType.RARITY, player) == RarityType.NONE) return;
            }
        }
        if (messages.isEmpty()) return;
        String message = messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
        message = message.replace("{victim}", victimColor + victim);
        message = message.replace("{killer}", killerColor + killer.getName());

        if (finalKill) {
            message += " &b&lFINAL KILL!";
        }
        player.sendMessage(ColorUtil.colored(message));
    }


    /**
     * Sends a kill message to a player based on the type of death.
     *
     * @param player            The player to send the message to
     * @param victim            The name of the victim
     * @param killer            The player object of the killer
     * @param finalKill         A flag indicating whether this was the final kill
     * @param victimColor       The color to use for the victim's name
     * @param killerColor       The color to use for the killer's name
     * @param type              The type of death. Accepted values: "PvP", "Void", "Shoot", "Explosion"
     * @param oldMessage        The old message will be used if list is empty
     */
    public static String sendKillMessage(Player player, String victim, Player killer, boolean finalKill, ChatColor victimColor, ChatColor killerColor, String type) {
        String selectedMessage = new BwcAPI().getSelectedCosmetic(killer, CosmeticsType.KillMessage);
        List<String> messages = ConfigUtils.getKillMessages().getYml().getStringList(CosmeticsType.KillMessage.getSectionKey() + "." + selectedMessage + "." + type + "-Kill");;
        for (KillMessage killMessage : StartupUtils.killMessageList) {
            if (killMessage.getIdentifier().equals(selectedMessage)) {
                if (killMessage.getField(FieldsType.RARITY, player) == RarityType.NONE) return null;
            }
        }
        if (messages.isEmpty()) return "Message is empty!";
        String message = messages.get(ThreadLocalRandom.current().nextInt(messages.size()));
        message = message.replace("{victim}", victimColor + victim);
        message = message.replace("{killer}", killerColor + killer.getName());
        message = PlaceholderAPI.setPlaceholders(killer, message);
        if (finalKill) {
            message += " &b&lFINAL KILL!";
        }
        return ColorUtil.colored(message);
    }
}
