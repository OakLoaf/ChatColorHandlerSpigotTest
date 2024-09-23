package org.lushplugins.chatcolorhandlertest.paper;

import org.bukkit.plugin.java.JavaPlugin;
import org.lushplugins.chatcolorhandlertest.paper.command.MainCommand;

public final class ChatColorHandlerTest extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getCommandMap().register("chatcolorhandlertest", new MainCommand(this));
    }
}
