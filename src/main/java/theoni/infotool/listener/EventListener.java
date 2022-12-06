package theoni.infotool.listener;

import java.text.DecimalFormat;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;
import theoni.infotool.Main;

public class EventListener implements Listener {

    Main plugin;
    Config config;
    Config mobs;

    public EventListener(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Item item = event.getItem();
        if (item.getName().equals(config.getString("tool.name"))) {
            if (block instanceof BlockAir) return;
            player.sendActionBar(config.getString("block-info")
                .replace("{name}", block.getName())
                .replace("{id}", String.valueOf(block.getId()))
                .replace("{meta}", String.valueOf(block.getDamage()))

                .replace("{color.red}", String.valueOf(block.getColor().getRed()))
                .replace("{color.green}", String.valueOf(block.getColor().getGreen()))
                .replace("{color.blue}", String.valueOf(block.getColor().getBlue()))
                .replace("{color.alpha}", String.valueOf(block.getColor().getAlpha()))

                .replace("{x}", String.valueOf(block.getFloorX()))
                .replace("{y}", String.valueOf(block.getFloorY()))
                .replace("{z}", String.valueOf(block.getFloorZ()))
                .replace("{distance}", new DecimalFormat("#0.000").format(block.distance(player)))

                .replace("{biome}", String.valueOf(block.getLevel().getBiomeId(block.getFloorX(), block.getFloorZ())))
                .replace("{world}", block.getLevel().getName())
                .replace("{light}", String.valueOf(block.getLightLevel()))

                .replace("{isSolid}", String.valueOf(block.isSolid()))
                .replace("{canHarvestWithHand}", String.valueOf(block.canHarvestWithHand()))
                .replace("{canPassThrough}", String.valueOf(block.canPassThrough()))
                .replace("{isNormal}", String.valueOf(block.isNormalBlock()))
            );
            event.setCancelled();
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getEntity();
        Item item = event.getItem();
        if (item.getName().equals(config.getString("tool.name"))) {
            player.sendActionBar(config.getString("entity-info")
                .replace("{name}", entity.getName())
                .replace("{tag}", entity.getNameTag())
                .replace("{id}", String.valueOf(entity.getNetworkId()))

                .replace("{x}", new DecimalFormat("#0.000").format(entity.getX()))
                .replace("{y}", new DecimalFormat("#0.000").format(entity.getY()))
                .replace("{z}", new DecimalFormat("#0.000").format(entity.getZ()))
                .replace("{distance}", new DecimalFormat("#0.000").format(entity.distance(player)))

                .replace("{biome}", String.valueOf(entity.getLevel().getBiomeId(entity.getFloorX(), entity.getFloorZ())))
                .replace("{world}", entity.getLevel().getName())
            );
            event.setCancelled();
        }
    }
}
