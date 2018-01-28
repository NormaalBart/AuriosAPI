package me.BartVV.AuriosAPI.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.FileTypes;
import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class AReloadCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (!u.hasPermission("AuriosAPI.reload")) {
				u.sendMessage(Messages.NO_PERMISSION);
				return true;
			}
			FileTypes.MESSAGES.getFileManager().reload();
			u.sendMessage(Messages.CONFIG_RELOADED);
			return true;
		} else {
			FileTypes.MESSAGES.getFileManager().reload();
			cs.sendMessage(Messages.CONFIG_RELOADED.getMessage());
			return true;
		}
	}
}
