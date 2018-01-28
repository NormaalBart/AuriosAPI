package me.BartVV.AuriosAPI.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class IgnorePMCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (args.length == 0) {
				u.sendMessage(Messages.NOT_ENOUGH_ARGUMENT_IGNOREPM);
				return true;
			}
			if (args[0].equalsIgnoreCase("list")) {
				if (args.length == 2) {
					User t = User.getUser(args[1]);
					String list = "";
					for (String names : t.getNameIgnores()) {
						list = list + names + " ";
					}
					if (list.equals("")) {
						list = "None";
					}
					u.sendMessage(Messages.IGNORE_LIST_OTHER.getMessage().replace("(LIST)", list).replace("(PLAYER)",
							t.getBase().getName()));
					return true;
				} else {
					String list = "";
					for (String names : u.getNameIgnores()) {
						list = list + names + " ";
					}
					if (list.equals("")) {
						list = "None";
					}
					u.sendMessage(Messages.IGNORE_LIST.getMessage().replace("(LIST)", list));
					return true;
				}
			}
			User t = User.getUser(args[0]);
			if (t == null) {
				u.sendMessage(Messages.NO_PLAYER);
				return true;
			} else if (u.addIgnore(t.getUUID())) {
				u.sendMessage(Messages.ADD_IGNORE.getMessage().replace("(PLAYER)", t.getBase().getDisplayName()));
				return true;
			} else {
				u.removeIgnore(t.getUUID());
				u.sendMessage(Messages.REMOVE_IGNORE.getMessage().replace("(PLAYER)", t.getBase().getDisplayName()));
				return true;
			}
		} else {
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}

}
