package item_safety.plugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

public class Event implements Listener {
	public Event(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void debug(PlayerJoinEvent e) {
		e.getPlayer().sendMessage("0");
		if (e.getPlayer().getName().equals("maguro027")) {
			e.getPlayer().sendMessage("1");
			if (e.getPlayer().hasPermission("wp.debug")) e.getPlayer().sendMessage("2");
		}
		if (e.getPlayer().getName().equals("maguro027")) e.getPlayer().isPermissionSet("wp.debug");
		if (e.getPlayer().getName().equals("maguro027")) {
			e.getPlayer().sendMessage("3");
			if (e.getPlayer().hasPermission("wp.debug")) e.getPlayer().sendMessage("4");
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {

		if (item_safety.plugin.main.Main.getitem(e.getPlayer())) {
			if (e.getHand() == EquipmentSlot.HAND) return;
			e.getPlayer().sendMessage(e.getAction().toString());
		}

	}

	@EventHandler
	public void onInventoryClickEvent(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals("Item_Safety"))
			if (e.getPlayer().hasPermission("wp.item_safety") || (e.getPlayer().hasPermission("wp.debug"))) item_safety.plugin.main.Main.closeInventory((Player) e.getPlayer(), e.getInventory());

	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getView().getTitle().equals("Item_Safety"))
			if (!((Player) e.getWhoClicked()).hasPermission("wp.item_safety") || !((Player) e.getWhoClicked()).hasPermission("wp.debug")) e.setCancelled(true);

	}

}
