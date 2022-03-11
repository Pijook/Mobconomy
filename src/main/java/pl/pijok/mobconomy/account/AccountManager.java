package pl.pijok.mobconomy.account;

import pl.pijok.api.ConfigProvider;
import pl.pijok.mobconomy.API;

public class AccountManager {

    private final ConfigProvider configProvider;

    public AccountManager(){
        configProvider = API.getConfigProvider();
    }

    public void loadAccount(String nickname){

    }

    public void saveAccount(String nickname){

    }

}
