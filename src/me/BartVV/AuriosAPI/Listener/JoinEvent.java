package me.BartVV.AuriosAPI.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.BartVV.AuriosAPI.Manager.User;

public class JoinEvent implements Listener {

	@EventHandler
	public void on(PlayerJoinEvent e) {
		new User(e.getPlayer().getUniqueId());
	}

}
