package theoni.infotool;

import cn.nukkit.command.Command;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import theoni.infotool.commands.*;
import theoni.infotool.listener.*;

public class Main extends PluginBase implements Listener {

    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents((Listener)new EventListener(this), (Main)this);
        this.getServer().getCommandMap().register("help", (Command) new InfoToolCommand(this));
    }
}
