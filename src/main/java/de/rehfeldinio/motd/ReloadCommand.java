package de.rehfeldinio.motd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            sender.sendMessage(Component.text("Du hast keine Berechtigung diesen Command auszufuehren.", NamedTextColor.RED));
            return true;
        }

        plugin.LoadConfig();
        sender.sendMessage(Component.text("Die MOTDChange Konfiguration wurde neu geladen!", NamedTextColor.GREEN));
        return true;
    }
}
