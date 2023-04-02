package me.defender.cosmetics.language;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface LanguageUtil {

    /**
     * Save default data if not exists in the language files.
     * Save a default message on all languages files.
     *
     * @param path message path.
     * @param data message value.
     */
    void saveIfNotExists(String path, Object data);

    /**
     * Get installed language list.
     *
     * @return available languages list.
     */
    List<Language> getLanguages();

    /**
     * Get message in player's language.
     *
     * @param p    target player.
     * @param path message path.
     * @return color translated message.
     */
    String getMsg(Player p, String path);

    /**
     * Retrieve a player language.
     *
     * @param p target player.
     * @return player language.
     */
    Language getPlayerLanguage(Player p);

    /**
     * Get a string list in player's language.
     *
     * @param p    target player.
     * @param path list path.
     * @return translated list with translated colors.
     */
    List<String> getList(Player p, String path);

    /**
     * Check if a language is loaded.
     * Iso example: en, ro etc.
     *
     * @param iso language iso code.
     * @return true if the language is loaded.
     */
    boolean isLanguageExist(String iso);

    /**
     * Add a language to the list.
     *
     * @param language new language.
     * @return true if the language was added successfully.
     */
    boolean addLanguage(Language language);

    /**
     * Get a language by iso code.
     *
     * @param iso language code. Ex: ro, en.
     * @return NULL if not found.
     */
    Language getLang(String iso);

    /**
     * Change server default language.
     *
     * @param defaultLanguage language.
     */
    void setDefaultLanguage(Language defaultLanguage);


    /**
     * Get server default language.
     *
     * @return server default language.
     */
    Language getDefaultLanguage();
}
