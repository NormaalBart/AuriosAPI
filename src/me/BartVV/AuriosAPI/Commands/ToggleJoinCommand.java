package me.BartVV.AuriosAPI.Commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class ToggleJoinCommand extends ToggleCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (args.length == 1) {
				Boolean enabled = matchToggleArgument(args[0]);
				u.setMayFollow(enabled);
				u.sendMessage(Messages.JOINING.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
				return true;
			} else if (args.length == 2) {
				if (!u.hasPermission("auriosapi.togglejoin.others")) {
					u.sendMessage(Messages.NO_PERMISSION);
					return true;
				}
				User t = User.getUser(args[1]);
				if (t == null) {
					u.sendMessage(Messages.NO_PLAYER);
					return true;
				}
				Boolean enabled = matchToggleArgument(args[0]);
				t.setMayJoin(enabled);
				t.sendMessage(Messages.JOINING.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
				u.sendMessage(Messages.JOINING_OTHER.getMessage().replace("(PLAYER)", t.getBase().getDisplayName())
						.replace("(MODE)", getConvertedArgument(enabled)));
				return true;
			} else {
				Boolean enabled = !u.mayJoin();
				u.setMayJoin(enabled);
				u.sendMessage(Messages.JOINING.getMessage().replace("(MODE)", getConvertedArgument(enabled)));	
				return true;
			}
		} else {
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("togglejoin")) {
			if (args.length == 1) {
				return getToggleList(args[0], Arrays.asList("on", "off", "enable", "disable"));
			}
		}
		return null;
	}

}
