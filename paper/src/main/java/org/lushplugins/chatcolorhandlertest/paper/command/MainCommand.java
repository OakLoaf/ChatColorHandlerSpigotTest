package org.lushplugins.chatcolorhandlertest.paper.command;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.ChatColorHandler;
import org.lushplugins.chatcolorhandler.ModernChatColorHandler;
import org.lushplugins.chatcolorhandlertest.paper.ChatColorHandlerTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCommand extends Command {
    private final ChatColorHandlerTest plugin;

    public MainCommand(ChatColorHandlerTest plugin) {
        super("chatcolorhandlertest");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (args.length == 1 && args[0].equals("version")) {
            ChatColorHandler.sendMessage(sender, "&#a8e1ffYou are currently running ChatColorHandlerTest version &#58b1e0" + plugin.getDescription().getVersion());
            return true;
        } else if (args.length >= 1 && args[0].equals("parse")) {
            Audience audience = Audience.audience(sender);

            Component message;
            if (sender instanceof Player player) {
                message = ModernChatColorHandler.translate(String.join(" ", Arrays.copyOfRange(args, 1, args.length)), player);
            } else {
                message = ModernChatColorHandler.translate(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            }

            audience.sendMessage(message);
            return true;
        } else if (args.length >= 1 && args[0].equals("parse-legacy")) {
            String message;
            if (sender instanceof Player player) {
                message = ChatColorHandler.translate(String.join(" ", Arrays.copyOfRange(args, 1, args.length)), player);
            } else {
                message = ChatColorHandler.translate(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            }

            sender.sendMessage(message);
            return true;
        } else if (args.length >= 1 && args[0].equals("run-tests")) {
            ChatColorHandlerTest.runTest(sender instanceof Player player ? player : null);
        }

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        List<String> tabComplete = new ArrayList<>();
        List<String> wordCompletion = new ArrayList<>();
        boolean wordCompletionSuccess = false;

        if (args.length == 1) {
            tabComplete.add("version");
            if (sender.hasPermission("chatcolorhandlertest.parse")) {
                tabComplete.add("parse");
                tabComplete.add("parse-legacy");
                tabComplete.add("run-tests");
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
