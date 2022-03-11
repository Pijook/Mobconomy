package pl.pijok.mobconomy.settings;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.pijok.mobconomy.API;

import java.util.HashMap;

public class Lang {

    private static HashMap<String, String> texts;

    public static void load(){
        texts = new HashMap<>();

        YamlConfiguration configuration = API.getConfigProvider().load("lang.yml");

        for(String key : configuration.getConfigurationSection("lang").getKeys(false)){
            texts.put(
                    key,
                    configuration.getString("lang." + key)
            );
        }

        API.getChatManager().setPrefix(getText("PREFIX"));
    }

    public static String getText(String a){
        return texts.getOrDefault(a, "NULL");
    }

}
