package pl.pijok.mobconomy.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.pijok.api.ChatManager;
import pl.pijok.mobconomy.API;
import pl.pijok.mobconomy.Controllers;
import pl.pijok.mobconomy.account.AccountManager;
import pl.pijok.mobconomy.account.transaction.TransactionData;
import pl.pijok.mobconomy.account.transaction.TransactionResult;

public class MobconomyCommand implements CommandExecutor {

    private final AccountManager accountManager;
    private final ChatManager chatManager;

    public MobconomyCommand(){
        accountManager = Controllers.getAccountManager();
        chatManager = API.getChatManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 3){
            if(args[0].equalsIgnoreCase("give")){
                Player target = Bukkit.getPlayer(args[1]);
                double amount = Double.parseDouble(args[2]);

                accountManager.depositCoins(target, amount, new TransactionResult() {
                    @Override
                    public void onTransactionFinish(TransactionData data) {
                        chatManager.sendMessage(sender, "&aNew account balance: &e" + data.getBalance());
                    }
                });
            }
        }

        return true;
    }
}
