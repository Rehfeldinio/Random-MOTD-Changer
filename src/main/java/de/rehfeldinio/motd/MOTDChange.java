package de.rehfeldinio.motd;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public class MOTDChange extends JavaPlugin implements Listener {

    private final Random random = new Random();

    @Override
    public void onEnable() {
        LoadConfig();
        if (this.getConfig().getBoolean("Motd.Enabled") && !this.getConfig().getStringList("Motd.Texts").isEmpty()) {
            this.getServer().getPluginManager().registerEvents(this, this);
        }
        getCommand("motd-reload").setExecutor(new ReloadCommand(this));
        getLogger().info("MOTDChange has been enabled!");
    }

    @Override
    public void onDisable() {
    }

    public void LoadConfig() {
        reloadConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void setMotd(ServerListPingEvent event) {
        List<String> motds = this.getConfig().getStringList("Motd.Texts");
        if (motds.isEmpty()) return;

        String selected = motds.get(random.nextInt(motds.size()));
        Component motdComponent = GradientUtil.process(selected);
        event.motd(motdComponent);
    }
}
