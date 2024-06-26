package me.hi12167pies.BedClutch.Events;

import me.hi12167pies.BedClutch.Main;
import me.hi12167pies.BedClutch.Utils.Arenas;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
    @EventHandler
    public void event(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (!Arenas.isPlaying(player)) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void event(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (!Arenas.isPlaying(player)) return;

        if (player.getItemInHand().getAmount() > 31) {
            player.getItemInHand().setAmount(64);
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
            e.getBlockPlaced().getLocation().getChunk().load();
            e.getBlockPlaced().setType(Material.REDSTONE_BLOCK);
        }, 80);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
            e.getBlockPlaced().getLocation().getChunk().load();
            e.getBlockPlaced().setType(Material.AIR);
        }, 100);

    }
}
