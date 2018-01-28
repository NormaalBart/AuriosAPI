package me.BartVV.AuriosAPI.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerMSGEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	private Player from, to;
	private String msg;
	private Boolean cancelled = false;

	public PlayerMSGEvent(Player from, Player to, String msg) {
		this.from = from;
		this.to = to;
		this.msg = msg;
	}

	public Player from() {
		return this.from;
	}

	public Player to() {
		return this.to;
	}

	public String getMessage() {
		return this.msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
