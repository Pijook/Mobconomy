package pl.pijok.mobconomy;

import org.bukkit.plugin.java.JavaPlugin;
import pl.pijok.api.Debugger;

public class Mobconomy extends JavaPlugin {

    private Debugger debugger;

    @Override
    public void onEnable() {

        API.setupAPI(this);

        debugger = API.getDebugger();

        if(!loadStuff(false)){
            debugger.log("&cSomething went wrong while enabling " + getDescription().getName() + "! Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        else{
            debugger.log("&aEverything loaded!");
        }

    }

    @Override
    public void onDisable() {

    }

    public boolean loadStuff(boolean reload){
        try{

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
