package item_safety.plugin.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

public class Main {

	static HashMap<Integer, ItemStack> items = new HashMap<>();
	public static final File file_ = new File(new File("").getAbsolutePath().toString() + "/plugins/item_safety/");

	public static void loadconfig() {
		if (!(file_.exists())) file_.mkdir();
		try(BufferedReader br = new BufferedReader(new FileReader(file_ + "/main_menu.json"))) {

            String line;
            while ((line = br.readLine()) != null) {
            }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveconfig(JSONObject json) {
		if (!(file_.exists())) file_.mkdir();
		try (FileWriter writer = new FileWriter(file_ + "/main_menu.json")) {
			writer.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setinventory(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, "Item_Safety");
		int conut = -1;
		try {
			for (java.util.Map.Entry<Integer, ItemStack> item1 : items.entrySet()) {
				inv.setItem(item1.getKey(), item1.getValue());
			}

			for (int i = 0, size = inv.getSize(); i < size; ++i) {
				if (inv.getItem(i) == null) continue;
				conut++;
				items.put(conut, inv.getItem(i));
			}
			player.openInventory(inv);
		} catch (ArrayIndexOutOfBoundsException items) {// hash map null
			player.openInventory(inv);
		}
	}

	public static void closeInventory(Player player, Inventory inventory) {
		// d•¡‚ðíœ‚µ‚½‚¢
		int conut = -1;
		items.clear();
		for (int i = 0, size = inventory.getSize(); i < size; ++i) {
			if (inventory.getItem(i) == null) continue;
			conut++;
			items.put(conut, inventory.getItem(i));
		}
		System.out.println(new JSONObject(items).toString());
		saveconfig(new JSONObject(items));
	}

	@SuppressWarnings("deprecation")
	public static boolean getitem(Player player) {
		if (player.getItemInHand().getType() == Material.AIR) return false;

		return false;
	}
}
