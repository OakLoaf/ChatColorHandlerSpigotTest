package me.dave.chatcolorhandlertest;

import me.dave.chatcolorhandlertest.command.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatColorHandlerTest extends JavaPlugin {
    private static ChatColorHandlerTest plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("chatcolorhandlertest").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static ChatColorHandlerTest getInstance() {
        return plugin;
    }
}
