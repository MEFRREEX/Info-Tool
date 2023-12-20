package com.mefrreex.infotool.utils;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.entity.Entity;
import cn.nukkit.utils.TextFormat;

import java.text.DecimalFormat;

@SuppressWarnings("deprecation")
public class Format {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.000");
    
    public static String formatBlock(String text, Block block, Player player) {
        text = text.replace("{name}", block.getName())
            .replace("{id}", String.valueOf(block.getId()))
            .replace("{meta}", String.valueOf(block.getDamage()))

            .replace("{color.red}", String.valueOf(block.getColor().getRed()))
            .replace("{color.green}", String.valueOf(block.getColor().getGreen()))
            .replace("{color.blue}", String.valueOf(block.getColor().getBlue()))
            .replace("{color.alpha}", String.valueOf(block.getColor().getAlpha()))

            .replace("{x}", String.valueOf(block.getFloorX()))
            .replace("{y}", String.valueOf(block.getFloorY()))
            .replace("{z}", String.valueOf(block.getFloorZ()))
            .replace("{distance}", DECIMAL_FORMAT.format(block.distance(player)))

            .replace("{biome}", String.valueOf(block.getLevel().getBiomeId(block.getFloorX(), block.getFloorZ())))
            .replace("{world}", block.getLevel().getName())
            .replace("{light}", String.valueOf(block.getLightLevel()))

            .replace("{isSolid}", String.valueOf(block.isSolid()))
            .replace("{canHarvestWithHand}", String.valueOf(block.canHarvestWithHand()))
            .replace("{canPassThrough}", String.valueOf(block.canPassThrough()))
            .replace("{isNormal}", String.valueOf(block.isNormalBlock()));
        return TextFormat.colorize(text);
    }

    public static String formatEntity(String text, Entity entity, Player player) {
        text = text.replace("{name}", entity.getName())
            .replace("{tag}", entity.getNameTag())
            .replace("{id}", String.valueOf(entity.getNetworkId()))

            .replace("{x}", new DecimalFormat("#0.000").format(entity.getX()))
            .replace("{y}", new DecimalFormat("#0.000").format(entity.getY()))
            .replace("{z}", new DecimalFormat("#0.000").format(entity.getZ()))
            .replace("{distance}", new DecimalFormat("#0.000").format(entity.distance(player)))

            .replace("{biome}", String.valueOf(entity.getLevel().getBiomeId(entity.getFloorX(), entity.getFloorZ())))
            .replace("{world}", entity.getLevel().getName());
        return TextFormat.colorize(text);
    }
}
