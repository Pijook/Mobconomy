package pl.pijok.mobconomy;

import pl.pijok.api.ChatManager;
import pl.pijok.api.ConfigProvider;
import pl.pijok.api.Debugger;
import pl.pijok.api.PijokAPI;

public class API {

    private static PijokAPI api;

    public static void setupAPI(Mobconomy plugin){
        api = new PijokAPI(plugin);
        api.getDebugger().setPrefix("[Mobconomy]");
        api.getChatManager().setPrefix("&7[&6&lMobConomy&7] &r");
    }

    public static Debugger getDebugger(){
        return api.getDebugger();
    }

    public static ChatManager getChatManager(){
        return api.getChatManager();
    }

    public static ConfigProvider getConfigProvider(){
        return api.getConfigProvider();
    }

    public static PijokAPI getApi() {
        return api;
    }
}
