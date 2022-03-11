package pl.pijok.mobconomy.settings;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.pijok.mobconomy.API;
import pl.pijok.mobconomy.database.DatabaseCredentials;

public class Settings {

    public static boolean databaseUsage;
    public static DatabaseCredentials databaseCredentials;

    public static double startingBalance;
    public static double maxBalance;

    public static void load(){
        YamlConfiguration configuration = API.getConfigProvider().load("config.yml");

        databaseUsage = configuration.getBoolean("databaseUsage");
        databaseCredentials = new DatabaseCredentials(
                configuration.getString("databaseCredentials.host"),
                configuration.getString("databaseCredentials.port"),
                configuration.getString("databaseCredentials.user"),
                configuration.getString("databaseCredentials.password")
        );

        startingBalance = configuration.getDouble("startingBalance");
        maxBalance = configuration.getDouble("maxBalance");

    }

}
