package item_safety.plugin.event;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Event implements Listener {
	public Event(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void aa(BlockDamageEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL && e.getItemInHand().getType() == Material.DEBUG_STICK) e.setInstaBreak(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getItem() == null || e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR) return;
		if (e.getItem().getType() == Material.DEBUG_STICK) e.setCancelled(item_safety.plugin.main.Main.getitem(e.getPlayer(), e.getClickedBlock().getType()));

	}

	@EventHandler
	public void onInventoryCloseEvent(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals("Item_Safety")) item_safety.plugin.main.Main.closeInventory(e.getInventory());

	}
}
