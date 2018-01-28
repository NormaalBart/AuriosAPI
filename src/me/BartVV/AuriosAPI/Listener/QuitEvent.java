package me.BartVV.AuriosAPI.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.BartVV.AuriosAPI.Manager.User;

public class QuitEvent implements Listener {

	@EventHandler
	public void on(PlayerQuitEvent e) {
		User user = User.getUser(e.getPlayer().getUniqueId());
		if (user != null) {
			user.remove();
		}
	}

}
