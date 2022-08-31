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
	public static List<Material> unbreak_items = new ArrayList<Material>();
	public static final File file_ = new File(new File("").getAbsolutePath().toString() + "/plugins/item_safety/");

	public static void loadconfig() {
		if (!(file_.exists())) file_.mkdir();
		try {
			createfile(file_ + "/main_menu.json");
			createfile(file_ + "/unbreak_main_menu.json");

			Reader reader = Files.newBufferedReader(Paths.get(file_ + "/main_menu.json"));
			items = new Gson().fromJson(reader, new TypeToken<List<Material>>() {
			}.getType());

			Reader unbreak_reader = Files.newBufferedReader(Paths.get(file_ + "/unbreak_main_menu.json"));
			unbreak_items = new Gson().fromJson(unbreak_reader, new TypeToken<List<Material>>() {
			}.getType());

			reader.close();
			unbreak_reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createfile(String string) {
		try {
			Files.createFile(Paths.get(string));
		} catch (IOException e) {

		}
	}

	public static void saveconfig(String string) {
		try {
			FileWriter writer = new FileWriter(file_ + "/main_menu.json");

			writer.write(string);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setinventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Item_Safety");

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
		} catch (NullPointerException items) {
			player.openInventory(inventory);
		}
	}

	public static void closeInventory(Inventory inventory) {

		List<Material> cashitems = new ArrayList<Material>();
		Gson gson = new Gson();

		for (int i = 0, size = inventory.getSize(); i < size; ++i) {
			if (inventory.getItem(i) == null) continue;
			cashitems.add(inventory.getItem(i).getType());
		}

		items = new ArrayList<Material>(new LinkedHashSet<>(cashitems));
		saveconfig(gson.toJson(items));

	}

	public static boolean getitem(Player player, Material mate) {
		try {
			if (unbreak_items.contains(mate) || items.contains(mate)) {
				player.sendMessage("ÅòaÅ° Åò7This block is not accept debug Stick");
				return true;
			}
			return false;
		} catch (NullPointerException items) {
			return true;
		}
	}
}
