package com.mefrreex.infotool.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemStick;
import com.mefrreex.infotool.InfoTool;

public class InfoToolCommand extends Command {

    private final InfoTool main;

    public InfoToolCommand(InfoTool main) {
        super("infotool", "Info tool");
        this.setAliases(new String[]{"info"});
        this.main = main;

        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[] {
                CommandParameter.newEnum("subcommand", new CommandEnum("Subcommand", "help", "about", "info"))
        });
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.getMessage("ingame"));
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            if (player.hasPermission("infotool.use")) {
                this.giveTool(player);
                player.sendMessage(main.getMessage("received"));
            } else {
                player.sendMessage(main.getMessage("permission"));
            }
            return false;
        }
            
        switch(args[0]) {
            case "help":
                sender.sendMessage("§fUsage:\n/infotool §8- §7Get an info tool\n§f/infotool about §8- §7About the plugin\n§f/infotool help §8- §7Help\n\n§aUse the information tool on blocks or entities to get more information about them.");
                break;
            case "about":
            case "info":
                if (player.hasPermission("infotool.about")) {
                    sender.sendMessage("§aUse the information tool on blocks or entities to get more information about them.\n\n§fThis plugin was written for free distribution and can be downloaded at §7https://cloudburstmc.org/resources/info-tool.862/.\n§fDeveloper: MEFRREEXX");
                } else {
                    player.sendMessage(main.getMessage("permission"));
                }
                break;
            }
        return false;
    }

    public void giveTool(Player player) {
        Item item = new ItemStick();
        item.setCustomName(main.getConfig().getString("tool.name"));
        player.getInventory().addItem(item);
    }
}
