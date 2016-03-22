package net.minefs.DeathDropsAPI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public final class Listeners implements Listener {

	@EventHandler (priority = EventPriority.MONITOR)
	public void onDie(PlayerDeathEvent e) {
		if (e.getKeepInventory())
			return;
		Player p = e.getEntity();
		if (p.hasPermission("deathdropsapi.keep")) {
			e.setKeepInventory(true);
			e.setKeepLevel(true);
			return;
		}
		ItemStack[] armors = p.getInventory().getArmorContents();
		for (int a = 0; a < armors.length; a++) {
			ItemStack i = armors[a];
			if(i==null || i.getType().equals(Material.AIR))
				continue;
			PlayerDeathDropEvent ev = new PlayerDeathDropEvent(p, i, true);
			Bukkit.getPluginManager().callEvent(ev);
			ItemStack i2 = ev.getItem();
			if (ev.isCancelled())
				armors[a] = i2;
			else {
				armors[a] = null;
				p.getWorld().dropItemNaturally(p.getLocation(), i2);
			}
		}

		ItemStack[] inventory = p.getInventory().getContents();
		for (int a = 0; a < inventory.length; a++) {
			ItemStack i = inventory[a];
			if(i==null || i.getType().equals(Material.AIR))
				continue;
			PlayerDeathDropEvent ev = new PlayerDeathDropEvent(p, i);
			Bukkit.getPluginManager().callEvent(ev);
			ItemStack i2 = ev.getItem();
			if (ev.isCancelled())
				inventory[a] = i2;
			else {
				inventory[a] = null;
				p.getWorld().dropItemNaturally(p.getLocation(), i2);
			}
		}

		p.getInventory().setArmorContents(armors);
		p.getInventory().setContents(inventory);
		e.setKeepInventory(true);
	}
}
