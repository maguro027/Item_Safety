package item_safety.plugin.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import item_safety.plugin.event.Event;

public class Core extends JavaPlugin {

	@Override
	public void onEnable() {
		new Event(this);
		item_safety.plugin.main.Main.loadconfig();
		System.out.println("Item Safety Start");
	}

	@Override
	public void onDisable() {
		System.out.println("Item Steick Safety Stop");

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(cmd.getName().equalsIgnoreCase("item_safety"))) return false;
		if (!(sender instanceof Player)) return false;
		if (!(sender.isOp())) return false;
		item_safety.plugin.main.Main.setinventory((Player) sender);

		return false;
	}
}
