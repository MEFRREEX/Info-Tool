package com.mefrreex.infotool;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import com.mefrreex.infotool.command.InfoToolCommand;
import com.mefrreex.infotool.listener.EventListener;

public class InfoTool extends PluginBase implements Listener {

    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        this.getServer().getCommandMap().register("InfoTool", new InfoToolCommand(this));
    }

    public String getToolName() {
        return TextFormat.colorize(this.getConfig().getString("tool.name"));
    }

    public String getBlockInfo() {
        return this.getConfig().getString("block-info");
    }

    public String getEntityInfo() {
        return this.getConfig().getString("entity-info");
    }

    public String getMessage(String key) {
        return TextFormat.colorize(this.getConfig().getString("messages." + key));
    }
}