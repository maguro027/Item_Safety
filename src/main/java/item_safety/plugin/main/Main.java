package item_safety.plugin.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main {

	public static List<Material> items = new ArrayList<Material>();
	public static final File file_ = new File(new File("").getAbsolutePath().toString() + "/plugins/item_safety/");

	public static void loadconfig() {
		if (!(file_.exists())) file_.mkdir();
		items.clear();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(file_ + "/main_menu.json"));
			items = new Gson().fromJson(reader, new TypeToken<List<Material>>() {
			}.getType());

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveconfig(String string) {
		if (!(file_.exists())) file_.mkdir();
		try (FileWriter writer = new FileWriter(file_ + "/main_menu.json")) {
			writer.write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setinventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Item_Safety");
		if (items == null) player.openInventory(inventory);

		try {
			int inv_i = 0;
			for (Material item : items) {
				inventory.setItem(inv_i, new ItemStack(item, 1));
				inv_i++;
			}

			for (int i = 0, size = inventory.getSize(); i < size; ++i) {
				if (inventory.getItem(i) == null) continue;
				items.add(inventory.getItem(i).getType());
			}
			player.openInventory(inventory);
		} catch (ArrayIndexOutOfBoundsException items) {// hash map null
			player.openInventory(inventory);
		}
	}

	public static void closeInventory(Player player, Inventory inventory) {
		Gson gson = new Gson();
		int conut = -1;
		items.clear();
		for (int i = 0, size = inventory.getSize(); i < size; ++i) {
			if (inventory.getItem(i) == null) continue;
			conut++;
			items.add(inventory.getItem(i).getType());
		}
		items = new ArrayList<Material>(new LinkedHashSet<>(items));
		if (conut == -1) return;
		saveconfig(gson.toJson(items));
	}

	@SuppressWarnings("deprecation")
	public static boolean getitem(Player player) {
		if (player.getItemInHand().getType() == Material.AIR) return false;

		return false;
	}
}
