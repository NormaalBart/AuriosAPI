package me.BartVV.AuriosAPI.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.BartVV.AuriosAPI.Enums.Messages;
import me.BartVV.AuriosAPI.Interfaces.CommandM;
import me.BartVV.AuriosAPI.Manager.User;

public class GamemodeCommand implements CommandM {

	private List<String> gamemodes = Lists.newArrayList();

	public GamemodeCommand() {
		gamemodes.add("creative");
		gamemodes.add("spectator");
		gamemodes.add("survival");
		gamemodes.add("adventure");
		gamemodes.add("gms");
		gamemodes.add("gmc");
		gamemodes.add("gma");
		gamemodes.add("gmsp");
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cs instanceof Player) {
			User u = User.getUser(((Player) cs).getUniqueId());
			if (args.length == 1) {
				if (!u.hasPermission("auriosapi.gamemode")) {
					u.sendMessage(Messages.NO_PERMISSION);
					return true;
				}
				GameMode gm = matchGameMode(args[0]);
				if (gm == null) {
					u.sendMessage(Messages.NO_GAMEMODE);
					return true;
				}
				u.getBase().setGameMode(gm);
				u.sendMessage(Messages.CHANGE_GAMEMODE.getMessage().replace("(GAMEMODE)", fromGameMode(gm)));
				return true;
			} else if (args.length == 2) {
				if (!u.hasPermission("auriosapi.gamemode.others")) {
					u.sendMessage(Messages.NO_PERMISSION);
					return true;
				}
				GameMode gm = matchGameMode(args[0]);
				if (gm == null) {
					u.sendMessage(Messages.NO_GAMEMODE);
					return true;
				}
				User t = User.getUser(args[1]);
				if (t == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}
				t.getBase().setGameMode(gm);
				t.sendMessage(Messages.CHANGE_GAMEMODE.getMessage().replace("(GAMEMODE)", fromGameMode(gm)));
				u.sendMessage(Messages.CHANGE_GAMEMODE_OTHERS.getMessage()
						.replace("(PLAYER)", t.getBase().getDisplayName()).replace("(GAMEMODE)", fromGameMode(gm)));
				return true;
			} else {
				u.sendMessage(Messages.WRONG_FORMAT_GAMEMODE);
				return true;
			}
		} else {
			if (args.length == 2) {
				GameMode gm = matchGameMode(args[0]);
				if (gm == null) {
					cs.sendMessage(Messages.NO_GAMEMODE.getMessage());
					return true;
				}
				User t = User.getUser(args[1]);
				if (t == null) {
					cs.sendMessage(Messages.NO_PLAYER.getMessage());
					return true;
				}
				t.getBase().setGameMode(gm);
				t.sendMessage(Messages.CHANGE_GAMEMODE.getMessage().replace("(GAMEMODE)", fromGameMode(gm)));
				cs.sendMessage(Messages.CHANGE_GAMEMODE_OTHERS.getMessage()
						.replace("(PLAYER)", t.getBase().getDisplayName()).replace("(GAMEMODE)", fromGameMode(gm)));
				return true;
			} else {
				cs.sendMessage(Messages.ONLY_PLAYER.getMessage());
				return true;
			}
		}
	}

	private String fromGameMode(GameMode gm) {
		switch (gm) {
		case CREATIVE:
			return "creative";
		case ADVENTURE:
			return "adventure";
		case SPECTATOR:
			return "spectator";
		case SURVIVAL:
			return "survival";
		}
		return null;
	}

	private GameMode matchGameMode(String modeString) {
		GameMode mode = null;
		if (modeString.equalsIgnoreCase("gmc") || modeString.equalsIgnoreCase("egmc") || modeString.contains("creat")
				|| modeString.equalsIgnoreCase("1") || modeString.equalsIgnoreCase("c")) {
			mode = GameMode.CREATIVE;
		} else if (modeString.equalsIgnoreCase("gms") || modeString.equalsIgnoreCase("egms")
				|| modeString.contains("survi") || modeString.equalsIgnoreCase("0")
				|| modeString.equalsIgnoreCase("s")) {
			mode = GameMode.SURVIVAL;
		} else if (modeString.equalsIgnoreCase("gma") || modeString.equalsIgnoreCase("egma")
				|| modeString.contains("advent") || modeString.equalsIgnoreCase("2")
				|| modeString.equalsIgnoreCase("a")) {
			mode = GameMode.ADVENTURE;
		} else if (modeString.equalsIgnoreCase("3") || modeString.equalsIgnoreCase("spec")
				|| modeString.equalsIgnoreCase("sp") || modeString.equalsIgnoreCase("spectator")) {
			mode = GameMode.SPECTATOR;
		}
		return mode;
	}

	@Override
	public List<String> onTabComplete(CommandSender cs, Command cmd, String cmdLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gamemode")) {
			List<String> str = Lists.newArrayList();
			if (args.length == 1) {
				for (String p : gamemodes) {
					if (p.toLowerCase().startsWith(args[0].toLowerCase())) {
						str.add(p);
					}
				}
			} else if (args.length == 2) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getName().toLowerCase().startsWith(args[1])) {
						str.add(p.getName());
					}
				}
			}
			return str;
		}
		return null;
	}

}
