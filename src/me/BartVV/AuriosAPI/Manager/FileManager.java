package me.BartVV.AuriosAPI.Manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Maps;

public class FileManager {

	private static HashMap<java.lang.String, me.BartVV.AuriosAPI.Manager.FileManager> files = Maps.newHashMap();

	public static FileManager getFileManager(String s) {
		return files.get(s);
	}

	JavaPlugin jp;

	File file;
	YamlConfiguration config;
	String filename;

	HashMap<String, String> searches;
	HashMap<String, Integer> isearches;
	HashMap<String, List<String>> lsearches;

	public FileManager(JavaPlugin jp, String filename, String forSearch) {
		this.jp = jp;
		filename = filename.endsWith(".yml") ? filename : filename + ".yml";
		this.filename = filename;
		if(!jp.getDataFolder().exists()){
			jp.getDataFolder().mkdirs();
		}
		this.file = new File(jp.getDataFolder(), this.filename);
		if (!file.exists()) {
			jp.saveResource(filename, false);
		}
		
		config = YamlConfiguration.loadConfiguration(file);
		this.searches = Maps.newHashMap();
		this.lsearches = Maps.newHashMap();
		this.isearches = Maps.newHashMap();
		this.locations = Maps.newHashMap();
		files.put(forSearch, this);
	}

	public YamlConfiguration getConfig() {
		return this.config;
	}

	public void set(String to, Object set, Boolean save) {
		
		if (set instanceof Location) {
			Location loc = (Location) set;
			Double x, y, z;
			Float yaw, pitch;
			World w = loc.getWorld();
			x = loc.getX();
			y = loc.getY();
			z = loc.getZ();
			yaw = loc.getYaw();
			pitch = loc.getPitch();
			set = x + "!-!" + y + "!-!" + z + "!-!" + yaw + "!-!" + pitch + "!-!" + w.getName();
			locations.put(to, loc);
		}
		this.config.set(to, set);
		if (save) {
			try {
				save();
			} catch (IOException iox) {
				iox.printStackTrace();
			}
		}
	}
	
	private HashMap<String, Location> locations;

	public Location getLocation(String from) {
		if(locations.containsKey(from)){
			return locations.get(from);
		}
		String str = this.config.getString(from);
		if(str == null){
			return null;
		}
		String[] coords = str.split("!-!");
		World w;
		Double x, y, z;
		Float yaw, pitch;
		w = Bukkit.getWorld(coords[5]);
		x = Double.parseDouble(coords[0]);
		y = Double.parseDouble(coords[1]);
		z = Double.parseDouble(coords[2]);
		yaw = Float.parseFloat(coords[3]);
		pitch = Float.parseFloat(coords[4]);
		if (w == null)
			return null;
		Location loc = new Location(w, x, y, z, yaw, pitch);
		locations.put(from, loc);
		return loc;
	}

	public String getString(String from) {
		String str = searches.get(from);
		if (str == null) {
			str = this.config.getString(from);
			searches.put(from, str);
		}
		if (str == null) {
			return null;
		}
		return str.replace("&", "§");
	}

	public Boolean getBoolean(String from) {
		return this.config.getBoolean(from);
	}

	public Integer getInteger(String from) {
		Integer str = isearches.get(from);
		if (str == null) {
			str = this.config.getInt(from);
			isearches.put(from, str);
		}
		return str;
	}

	private HashMap<String, Double> doubled = new HashMap<>();

	public Double getDouble(String from) {
		Double str = doubled.get(from);
		if (str == null) {
			str = this.config.getDouble(from);
			doubled.put(from, str);
		}
		return str;
	}

	public List<String> getStringList(String from) {
		List<String> str = lsearches.get(from);
		if (str == null) {
			str = this.config.getStringList(from);
			for (Integer i = 0; i < str.size(); i++) {
				String x = str.get(i);
				x = x.replace("&", "§");
				str.set(i, x);
			}
			lsearches.put(from, str);
		}
		return str;
	}

	public List<Integer> getIntegerList(String from) {
		return this.config.getIntegerList(from);
	}

	public boolean save() throws IOException {
		try {
			config.save(file);
			return true;
		} catch (IOException e) {
			throw new IOException("Failed to save " + this.filename + "!");
		}
	}

	public void reload() {
		config = YamlConfiguration.loadConfiguration(file);
		this.searches = Maps.newHashMap();
		this.lsearches = Maps.newHashMap();
		this.isearches = Maps.newHashMap();
	}
}
