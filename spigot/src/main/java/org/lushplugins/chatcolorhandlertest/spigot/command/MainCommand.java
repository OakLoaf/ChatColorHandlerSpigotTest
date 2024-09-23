package org.lushplugins.chatcolorhandlertest.spigot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.ChatColorHandler;
import org.lushplugins.chatcolorhandlertest.spigot.ChatColorHandlerTest;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    private final ChatColorHandlerTest plugin;

    public MainCommand(ChatColorHandlerTest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1 && args[0].equals("version")) {
            ChatColorHandler.sendMessage(sender, "&#a8e1ffYou are currently running ChatColorHandlerTest version &#58b1e0" + plugin.getDescription().getVersion());
            return true;
        } else if (args.length >= 1 && args[0].equals("parse")) {
            ChatColorHandler.sendMessage(sender, String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            return true;
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabComplete = new ArrayList<>();
        List<String> wordCompletion = new ArrayList<>();
        boolean wordCompletionSuccess = false;

        if (args.length == 1) {
            tabComplete.add("version");
            if (sender.hasPermission("chatcolorhandlertest.parse")) {
                tabComplete.add("parse");
            }
        }

        for (String currTab : tabComplete) {
            int currArg = args.length - 1;
            if (currTab.startsWith(args[currArg])) {
                wordCompletion.add(currTab);
                wordCompletionSuccess = true;
            }
        }

        return wordCompletionSuccess ? wordCompletion : tabComplete;
    }
}