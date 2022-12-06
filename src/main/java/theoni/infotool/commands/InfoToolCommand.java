package theoni.infotool.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemStick;
import cn.nukkit.utils.Config;
import theoni.infotool.Main;

public class InfoToolCommand extends Command {

    Main plugin;
    Config config;
    Config mobs;

    public InfoToolCommand(Main plugin) {
        super("infotool", "Info tool");
        this.setAliases(new String[]{"info"});
        this.plugin = plugin;
        this.config = plugin.getConfig();

        commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
                CommandParameter.newEnum("subcommand", new CommandEnum("Subcommand", "help", "about", "info"))
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(config.getString("messages.ingame"));
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.hasPermission("infotool.use")) {
                this.giveTool(player);
                player.sendMessage(config.getString("messages.received"));
            } else {
                player.sendMessage(config.getString("messages.permission"));
            }
        } else {
            switch(args[0]) {
                case "help":
                    sender.sendMessage("§fUsage:\n/infotool §8- §7Get an info tool\n§f/infotool about §8- §7About the plugin\n§f/infotool help §8- §7Help\n\n§aUse the information tool on blocks or entities to get more information about them.");
                    break;
                case "about":
                case "info":
                    if (player.hasPermission("infotool.about")) {
                        sender.sendMessage("§aUse the information tool on blocks or entities to get more information about them.\n\n§fThis plugin was written for free distribution and can be downloaded at §7https://cloudburstmc.org/resources/info-tool.862/.\n§fDeveloper: MEFRREEXX");
                    } else {
                        player.sendMessage(config.getString("messages.permission"));
                    }
                    break;
            }
        }
        return false;
    }

    public void giveTool(Player player) {
        Item item = new ItemStick();
        item.setCustomName(config.getString("tool.name"));
        player.getInventory().addItem(item);
    }
}
