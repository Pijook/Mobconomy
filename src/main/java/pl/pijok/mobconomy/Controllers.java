package pl.pijok.mobconomy;

import pl.pijok.mobconomy.account.AccountManager;

public class Controllers {

    private static AccountManager accountManager;

    public static void create(Mobconomy plugin){
        accountManager = new AccountManager(plugin);
    }

    public static AccountManager getAccountManager() {
        return accountManager;
    }
}
