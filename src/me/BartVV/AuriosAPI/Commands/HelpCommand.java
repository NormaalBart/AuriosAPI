package me.BartVV.AuriosAPI.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class HelpCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			for (String str : Messages.HELP_MENU.getMessages()) {
				u.sendMessage(str);
			}
			return true;
		} else {
			for (String str : Messages.HELP_MENU.getMessages()) {
				cs.sendMessage(str);
			}
			return true;
		}
	}

}
