package me.BartVV.AuriosAPI.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class TogglePMCommand extends ToggleCommand implements CommandM {

	private static List<String> p = Lists.newArrayList();

	public TogglePMCommand() {
		p.add("on");
		p.add("off");
		p.add("enabled");
		p.add("disabled");
		p.add("true");
		p.add("false");
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (!u.hasPermission("AuriosAPI.togglepm")) {
				u.getBase().sendMessage(Messages.NO_PERMISSION.getMessage());
				return true;
			}
			if (args.length == 1) {
				Boolean enabled = matchToggleArgument(args[0]);
				if (enabled == null) {
					enabled = !u.mayMSG();
				}
				if (enabled) {
					u.setMayMSG(enabled);
					String toggle = getConvertedArgument(enabled);
					u.getBase().sendMessage(Messages.TOGGLE_PM.getMessage().replace("(MODE)", toggle));
					return true;
				} else if (!enabled) {
					u.setMayMSG(enabled);
					String toggle = getConvertedArgument(enabled);
					u.getBase().sendMessage(Messages.TOGGLE_PM.getMessage().replace("(MODE)", toggle));
					return true;
				}
				return true;
			} else {
				Boolean from = u.mayMSG();
				u.setMayMSG(!from);
				String toggle = getConvertedArgument(u.mayMSG());
				u.getBase().sendMessage(Messages.TOGGLE_PM.getMessage().replace("(MODE)", toggle));
				return true;
			}
		} else {
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("togglepm")) {
			if (args.length != 0) {
				try {
					return getToggleList(args[0], p);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}

}
