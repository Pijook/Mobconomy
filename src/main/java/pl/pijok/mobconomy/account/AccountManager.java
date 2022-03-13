package pl.pijok.mobconomy.account;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import pl.pijok.api.ConfigProvider;
import pl.pijok.mobconomy.API;
import pl.pijok.mobconomy.Mobconomy;
import pl.pijok.mobconomy.account.transaction.TransactionData;
import pl.pijok.mobconomy.account.transaction.Transaction;
import pl.pijok.mobconomy.account.transaction.TransactionResult;
import pl.pijok.mobconomy.settings.Settings;

import javax.xml.transform.Result;
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

    public void loadPlayer(Player player){
        Account account = loadAccount(player);
        accounts.put(player, account);
    }

    public void savePlayer(Player player){
        saveAccount(accounts.get(player));
        accounts.remove(player);
    }

    /**
     * Deposit coins from player account
     * @param player Target
     * @param amount Amount of coins to withdraw
     * @param result Transaction result
     */
    public void withdrawCoins(OfflinePlayer player, double amount, Transaction result){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if(isLoaded(player.getPlayer())){
                Account account = accounts.get(player);
                /*
                Account not found result (Probably loading error)
                 */
                if(account == null){
                    callBackData(result, new TransactionData(
                            player.getName(),
                            -1.0,
                            TransactionResult.FAILED
                    ));
                    return;
                }

                /*
                Not enough money result
                 */
                if(account.getBalance() - amount < 0){
                    callBackData(result,
                            new TransactionData(
                                    player.getName(),
                                    account.getBalance(),
                                    TransactionResult.NOT_ENOUGH_MONEY));
                    return;
                }
                /*
                Successful transaction
                 */
                else{
                    account.decreaseBalance(amount);
                    callBackData(result,
                            new TransactionData(
                                    player.getName(),
                                    account.getBalance(),
                                    TransactionResult.SUCCESSFUL));
                    return;
                }
            }
            else{
                //TODO Add offline support

            }
        });
    }

    /**
     * Deposit coins to player account
     * @param player Target
     * @param amount Amount of coins to deposit
     * @param result Transaction result
     */
    public void depositCoins(OfflinePlayer player, double amount, Transaction result){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            if(isLoaded(player.getPlayer())){
                Account account = accounts.get(player);

                /*
                Account not found result (Probably loading error)
                 */
                if(account == null){
                    callBackData(result, new TransactionData(
                            player.getName(),
                            -1.0,
                            TransactionResult.FAILED
                    ));
                    return;
                }

                account.increaseBalance(amount);

                /*
                Successful transaction
                 */
                callBackData(result, new TransactionData(
                        player.getName(),
                        account.getBalance(),
                        TransactionResult.SUCCESSFUL));
                return;
            }
            else{
                Account account = loadAccount(player);

                if(account == null){
                    callBackData(result, new TransactionData(
                            player.getName(),
                            -1.0,
                            TransactionResult.FAILED
                    ));
                    return;
                }

                account.increaseBalance(amount);
                saveAccount(account);

                callBackData(result, new TransactionData(
                        player.getName(),
                        account.getBalance(),
                        TransactionResult.SUCCESSFUL
                ));
            }
        });
    }

    /**
     * Loads player account
     * @param player Target
     * @return Account
     */
    private Account loadAccount(OfflinePlayer player){
        if(Settings.databaseUsage){
            //TODO Database integration
        }
        else{
            YamlConfiguration configuration = API.getConfigProvider().load("accounts.yml");

            String nickname = player.getName();

            if(configuration.contains("accounts." + nickname)){
                return new Account(nickname, configuration.getDouble("accounts." + nickname + ".balance"));
            }
            else{
                return new Account(nickname, Settings.startingBalance);
            }
        }
        return null;
    }

    /**
     * Saves player account
     * @param account Account
     */
    private void saveAccount(Account account){
        if(Settings.databaseUsage){
            //TODO Database integration
        }
        else{
            YamlConfiguration configuration = API.getConfigProvider().load("accounts.yml");

            configuration.set("accounts." + account.getOwner() + ".balance", account.getBalance());

            API.getConfigProvider().save(configuration, "accounts.yml");
        }
    }

    /**
     * Throws callback on finished operation
     * @param result Transaction result
     * @param data Transaction data
     */
    private void callBackData(Transaction result, TransactionData data){
        Bukkit.getScheduler().runTask(plugin, () -> {
            result.onTransactionFinish(data);
        });
    }

    public Account getAccount(Player player){
        return accounts.get(player);
    }

    public boolean isLoaded(Player player){
        return accounts.containsKey(player);
    }

}
