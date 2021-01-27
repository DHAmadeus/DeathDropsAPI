package net.minefs.DeathDropsAPI;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static boolean newversion = false;

	@Override
	public void onEnable() {
		String packageName = this.getServer().getClass().getPackage().getName();
		String version = packageName.substring(packageName.lastIndexOf('.') + 1);
		getLogger().info("Spigot " + version + " detected.");
		if (!version.startsWith("v1_8_")) {
			newversion = true;
		}
		getServer().getPluginManager().registerEvents(new Listeners(), this);
	}
}
