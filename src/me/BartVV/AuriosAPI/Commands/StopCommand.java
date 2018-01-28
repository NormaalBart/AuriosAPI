package me.BartVV.AuriosAPI.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class StopCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (!u.hasPermission("auriosAPI.stop")) {
				u.sendMessage(Messages.NO_PERMISSION);
				return true;
			}
			u.setAutoStop(true);
			u.sendMessage(Messages.STOP);
			return true;
		} else {
			Bukkit.shutdown();
			return true;
		}
	}

}
