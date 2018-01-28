package me.BartVV.AuriosAPI.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Events.PlayerMSGEvent;

public class PlayerMSG implements Listener {

	@EventHandler
	public void on(PlayerMSGEvent e) {
		e.setMessage(Messages.MSG_FORMAT.getMessage().replace("(FROM)", e.from().getDisplayName()).replace("(MESSAGE)",
				e.getMessage()));
	}
	
	

}
