package me.BartVV.AuriosAPI.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Events.ConsoleMSGEvent;

public class ConsoleMSG implements Listener{
	
	@EventHandler
	public void on(ConsoleMSGEvent e) {
		e.setMessage(Messages.MSG_FORMAT.getMessage().replace("(FROM)", "Console").replace("(MESSAGE)",
				e.getMessage()));
	}

}
