package net.minefs.DeathDropsAPI;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathDropEvent extends Event implements Cancellable{
	private Player p;
	private ItemStack item;
	private boolean cancelled;
	private boolean armor;
	private static final HandlerList handlers = new HandlerList();

	public PlayerDeathDropEvent(Player p, ItemStack item) {
		this.p = p;
		this.item = item;
		this.cancelled = false;
		this.armor = true;
	}
	
	public PlayerDeathDropEvent(Player p, ItemStack item, boolean isArmor) {
		this.p = p;
		this.item = item;
		this.cancelled = false;
		this.armor = isArmor;
	}

	public boolean isArmor(){
		return this.armor;
	}
	
	public ItemStack getItem() {
		return this.item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public Player getPlayer() {
		return this.p;
	}
	
	@Override
	public boolean isCancelled(){
		return this.cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled){
		this.cancelled = cancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
