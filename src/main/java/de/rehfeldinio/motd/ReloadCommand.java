package de.rehfeldinio.motd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final MOTDChange plugin;

    public ReloadCommand(MOTDChange plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("motdchange.reload")) {
            sender.sendMessage(ChatColor.RED + "Du hast keine Berechtigung diesen Command auszufuehren.");
            return true;
        }

        plugin.LoadConfig();
        sender.sendMessage(ChatColor.GREEN + "Die MOTDChange Konfiguration wurde neu geladen!");
        return true;
    }
}
