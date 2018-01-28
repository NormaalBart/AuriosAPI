package me.BartVV.AuriosAPI.Commands;

import java.util.Collection;
import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class ToggleCommand {

	protected Boolean matchToggleArgument(final String arg) {
		if (arg == null) {
			return null;
		}
		if (arg.equalsIgnoreCase("on") || arg.startsWith("ena") || arg.equalsIgnoreCase("1")
				|| arg.equalsIgnoreCase("true")) {
			return true;
		} else if (arg.equalsIgnoreCase("off") || arg.startsWith("dis") || arg.equalsIgnoreCase("0")
				|| arg.equalsIgnoreCase("false")) {
			return false;
		}
		return null;
	}

	protected String getConvertedArgument(final Boolean b) {
		if (b == null)
			return null;
		if (b) {
			return "on";
		} else if (!b) {
			return "off";
		}

		return null;
	}

	protected List<String> getToggleList(final String arg, final List<String> possibilities) {
		List<String> str = Lists.newArrayList();
		for (String p : possibilities) {
			p = p.toLowerCase();
			if (p.startsWith(arg.toLowerCase())) {
				str.add(p);
			}
		}
		return str;
	}

	protected List<String> getToggleList(String arg, Collection<? extends Player> onlinePlayers) {
		List<String> str = Lists.newArrayList();
		for (Player p : onlinePlayers) {
			if (p.getName().toLowerCase().startsWith(arg)) {
				str.add(p.getName());
			}
		}
		return str;
	}

}
