package me.BartVV.AuriosAPI.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.FileTypes;
import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class HubCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		Location loc = FileTypes.CONFIG.getFileManager().getLocation("Location.hub");
		if (loc == null) {
			cs.sendMessage(Messages.HUB_NOT_FOUND.getMessage());
			return true;
		}
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (args.length == 1) {
				if (u.hasPermission("AuriosAPI.hub.others")) {
					User t = User.getUser(args[0]);
					if (t == null) {
						u.sendMessage(Messages.NO_PLAYER);
						return true;
					}
					t.getBase().teleport(loc);
					t.sendMessage(Messages.HUB_TELEPORT);
					u.sendMessage(Messages.HUB_TELEPORT_OTHERS.getMessage().replace("(PLAYER)",
							t.getBase().getDisplayName()));
					return true;
				}
			}
			u.getBase().teleport(loc);
			u.sendMessage(Messages.HUB_TELEPORT);
			return true;
		} else {
			if (args.length == 1) {
				User t = User.getUser(args[0]);
				if (t == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}
				t.getBase().teleport(loc);
				t.sendMessage(Messages.HUB_TELEPORT);
				cs.sendMessage(
						Messages.HUB_TELEPORT_OTHERS.getMessage().replace("(PLAYER)", t.getBase().getDisplayName()));
				return true;
			}
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}

}
