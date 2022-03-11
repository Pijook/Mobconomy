package pl.pijok.mobconomy.account;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.pijok.api.ConfigProvider;
import pl.pijok.mobconomy.API;
import pl.pijok.mobconomy.Mobconomy;
import pl.pijok.mobconomy.settings.Settings;

import java.util.HashMap;

public class AccountManager {

    private final ConfigProvider configProvider;
    private final Mobconomy plugin;

    private final HashMap<Player, Account> accounts;

    public AccountManager(Mobconomy plugin){
        this.configProvider = API.getConfigProvider();
        this.plugin = plugin;

        this.accounts = new HashMap<>();
    }

    public void loadAccount(Player player){
        if(Settings.databaseUsage){
            //TODO Database integration
        }
        else{
            YamlConfiguration configuration = API.getConfigProvider().load("accounts.yml");

            String nickname = player.getName();

            if(configuration.contains("accounts." + nickname)){
                accounts.put(player, new Account(
                                nickname,
                                configuration.getDouble("accounts." + nickname + ".balance")
                ));
            }
            else{
                accounts.put(player, new Account(nickname, Settings.startingBalance));
            }
        }
    }

    public void saveAccount(Player player){
        if(Settings.databaseUsage){
            //TODO Database integration
        }
        else{
            YamlConfiguration configuration = API.getConfigProvider().load("accounts.yml");

            Account account = accounts.get(player);

            configuration.set("accounts." + player.getName() + ".balance", account.getBalance());
        }
    }

    public void withdrawCoins(CommandSender sender, Player player, double amount){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if(isLoaded(player)){
                Account account = accounts.get(player);
                account.decreaseBalance(amount);

                if(sender != null){
                    //TODO Send message
                }
            }
            else{
                //TODO Add offline support
            }
        });
    }

    public void depositCoins(CommandSender sender, Player player, double amount){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if(isLoaded(player)){
                Account account = accounts.get(player);
                account.increaseBalance(amount);

                if(sender != null){
                    //TODO Send message
                }
            }
            else{
                //TODO Add offline support
            }
        });
    }

    public Account getAccount(Player player){
        return accounts.get(player);
    }

    public boolean isLoaded(Player player){
        return accounts.containsKey(player);
    }

}
