package com.mefrreex.infotool.listener;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerInteractEvent.Action;
import cn.nukkit.item.Item;
import com.mefrreex.infotool.InfoTool;
import com.mefrreex.infotool.utils.Format;

public class EventListener implements Listener {

    private final InfoTool main;

    public EventListener(InfoTool plugin) {
        this.main = plugin;
    }

    @EventHandler
    public void onInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Item item = event.getItem();

        if (this.isInfoTool(item)) {
            if (block instanceof BlockAir || event.getAction() == Action.PHYSICAL) return;
            player.sendActionBar(Format.formatBlock(main.getBlockInfo(), block, player));
            event.setCancelled();
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();
        Item item = event.getItem();

        if (this.isInfoTool(item)) {
            event.setCancelled();
            player.sendActionBar(Format.formatEntity(main.getEntityInfo(), entity, player));
        }
    }

    private boolean isInfoTool(Item item) {
        return item != null && item.getName().equals(main.getToolName());
    }
}
