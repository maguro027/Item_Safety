package item_safety.plugin.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class Event implements Listener {
	public Event(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getItem() == null) return;
		if (e.getClickedBlock() == null) return;
		if (e.getClickedBlock().getType() == Material.AIR) return;
		if (e.getItem().getType() == Material.DEBUG_STICK) e.setCancelled(item_safety.plugin.main.Main.getitem(e.getPlayer(), e.getClickedBlock().getType()));
//		e.getPlayer().sendMessage(e.getItem().getEnchantments().toString());
//		if (e.getItem().getType() == Material.STICK && e.getItem().getEnchantments().containsKey(Enchantment.DURABILITY)) {
//			e.getPlayer().sendMessage("a");
//			e.setCancelled(item_safety.plugin.main.Main.getitem(e.getPlayer(), e.getClickedBlock().getType()));
//		}

	}

	@EventHandler
	public void onInventoryClickEvent(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals("Item_Safety")) item_safety.plugin.main.Main.closeInventory(e.getInventory());

	}

//	@EventHandler
//	public void onInventoryClickEvent(InventoryClickEvent e) {
//		if (e.getView().getTitle().equals("Item_Safety"))
//			if (!((Player) e.getWhoClicked()).hasPermission("wp.item_safety.edit") || !((Player) e.getWhoClicked()).hasPermission("wp.debug")) e.setCancelled(true);
//	}

}
