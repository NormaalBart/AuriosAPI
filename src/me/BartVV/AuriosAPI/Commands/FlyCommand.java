package me.BartVV.AuriosAPI.Commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class FlyCommand extends ToggleCommand implements CommandM {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (args.length <= 1) {
				if (!u.hasPermission("auriosAPI.fly")) {
					u.sendMessage(Messages.NO_PERMISSION);
					return true;
				}
				Boolean enabled;
				if (args.length == 0) {
					enabled = !u.getBase().getAllowFlight();
				} else {
					enabled = matchToggleArgument(args[0]);
				}
				if (enabled == null) {
					if (u.hasPermission("AuriosAPI.fly.others")) {
						User t = User.getUser(args[0]);
						if (t != null) {
							enabled = matchToggleArgument(args[0]);
							if (enabled == null) {
								enabled = !t.getBase().getAllowFlight();
							}
							t.getBase().setFallDistance(0f);
							t.getBase().setAllowFlight(enabled);
							if(enabled){
								if(!t.getBase().isOnGround()){
									t.getBase().setFlying(enabled);
								}
							}else{
								t.getBase().setFlying(enabled);	
							}
							t.sendMessage(
									Messages.FLY_TOGGLE.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
							u.sendMessage(Messages.FLY_TOGGLE_OTHERS.getMessage()
									.replace("(MODE)", getConvertedArgument(enabled))
									.replace("(PLAYER)", t.getBase().getDisplayName()));
							return true;
						}
					}
					enabled = !u.getBase().getAllowFlight();
				}
				u.getBase().setFallDistance(0f);
				u.getBase().setAllowFlight(enabled);
				if(enabled){
					if(!u.getBase().isOnGround()){
						u.getBase().setFlying(enabled);
					}
				}else{
					u.getBase().setFlying(enabled);	
				}
				u.sendMessage(Messages.FLY_TOGGLE.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
				return true;
			} else {
				if (!u.hasPermission("AuriosAPI.fly.others")) {
					u.sendMessage(Messages.NO_PERMISSION);
					return true;
				}
				User t = User.getUser(args[0]);
				if (t == null) {
					u.sendMessage(Messages.NO_PLAYER);
					return true;
				}
				Boolean enabled = matchToggleArgument(args[1]);
				if (enabled == null) {
					enabled = !u.getBase().getAllowFlight();
				}
				t.getBase().setFallDistance(0f);
				t.getBase().setAllowFlight(enabled);
				if(enabled){
					if(!t.getBase().isOnGround()){
						t.getBase().setFlying(enabled);
					}
				}else{
					t.getBase().setFlying(enabled);	
				}
				t.sendMessage(Messages.FLY_TOGGLE.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
				u.sendMessage(Messages.FLY_TOGGLE_OTHERS.getMessage().replace("(MODE)", getConvertedArgument(enabled))
						.replace("(PLAYER)", t.getBase().getDisplayName()));
				return true;
			}
		} else {
			if (args.length == 1) {
				User u = User.getUser(args[0]);
				if (u == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}

				Boolean enabled = !u.getBase().getAllowFlight();
				u.getBase().setFallDistance(0f);
				u.getBase().setAllowFlight(enabled);
				if(enabled){
					if(!u.getBase().isOnGround()){
						u.getBase().setFlying(enabled);
					}
				}else{
					u.getBase().setFlying(enabled);	
				}
				u.sendMessage(Messages.FLY_TOGGLE.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
				cs.sendMessage(Messages.FLY_TOGGLE_OTHERS.getMessage().replace("(MODE)", getConvertedArgument(enabled))
						.replace("(PLAYER)", u.getBase().getDisplayName()));
				return true;
			} else if (args.length == 2) {
				Boolean enabled = matchToggleArgument(args[1]);
				User u = User.getUser(args[0]);
				if (u == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}
				if (enabled == null) {
					enabled = !u.getBase().getAllowFlight();
				}
				u.getBase().setFallDistance(0f);
				u.getBase().setAllowFlight(enabled);
				if(enabled){
					if(!u.getBase().isOnGround()){
						u.getBase().setFlying(enabled);
					}
				}else{
					u.getBase().setFlying(enabled);	
				}
				u.sendMessage(Messages.FLY_TOGGLE.getMessage().replace("(MODE)", getConvertedArgument(enabled)));
				cs.sendMessage(Messages.FLY_TOGGLE_OTHERS.getMessage().replace("(MODE)", getConvertedArgument(enabled))
						.replace("(PLAYER)", u.getBase().getDisplayName()));
				return true;
			}
			cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
			return true;

		}
	}

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (args.length == 0)
				return Arrays.asList("on", "off");
			List<String> str = null;
			if (args.length == 1) {
				str = getToggleList(args[0], Arrays.asList("on", "off"));
			} else if (args.length == 2) {
				str = getToggleList(args[1], Bukkit.getOnlinePlayers());
			}
			return str;
		}
		return null;
	}
}
