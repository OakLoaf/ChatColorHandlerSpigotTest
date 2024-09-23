package org.lushplugins.chatcolorhandlertest.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import org.lushplugins.chatcolorhandlertest.spigot.command.MainCommand;

public final class ChatColorHandlerTest extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("chatcolorhandlertest").setExecutor(new MainCommand(this));
    }
}
