package me.BartVV.AuriosAPI.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PermissionCheckEvent extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	String permMSG;
	Boolean hasPerm;

	public PermissionCheckEvent(Player p, String permMSG) {
		super(p);
		this.permMSG = permMSG;
		this.hasPerm = false;
	}

	public String getPermission() {
		return this.permMSG;
	}

	public Boolean hasPerm() {
		return this.hasPerm;
	}

	public void setPerm(Boolean hasPerm) {
		this.hasPerm = hasPerm;
	}
}
