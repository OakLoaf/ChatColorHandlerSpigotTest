package org.lushplugins.chatcolorhandlertest.paper;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.lushplugins.chatcolorhandler.ChatColorHandler;
import org.lushplugins.chatcolorhandler.ModernChatColorHandler;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandlertest.paper.command.MainCommand;
import org.lushplugins.chatcolorhandlertest.paper.test.TestRunner;

public final class ChatColorHandlerTest extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getCommandMap().register("chatcolorhandlertest", new MainCommand(this));
    }

    public static void runTest(Player player) {
        String inputOne = "&#A4D3F9This is a test string &rusing &lbold, <u>underline</u>&r and shows your name: <#F9CAA4>%player_name%</#F9CAA4>";

        new TestRunner(player)
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r§r and shows your name: ",
                ChatColorHandler::translate)
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r§r and shows your name: §x§f§9§c§a§a§4" + (player != null ? player.getName() : "%player_name%"),
                (input) -> ChatColorHandler.translate(input, player))
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r§r and shows your name: §x§f§9§c§a§a§4%player_name%",
                (input) -> ChatColorHandler.translate(input, ParserTypes.color()))
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r§r and shows your name: §x§f§9§c§a§a§4%player_name%",
                (input) -> ChatColorHandler.translate(input, player, ParserTypes.color()))
            .addTest(
                inputOne,
                "&#A4D3F9This is a test string &rusing &lbold, <u>underline</u>&r and shows your name: <#F9CAA4>" + (player != null ? player.getName() : "%player_name%") + "</#F9CAA4>",
                (input) -> ChatColorHandler.translate(input, player, ParserTypes.placeholder()))
            .run();

        player.sendMessage(" ");
        player.sendMessage(ModernChatColorHandler.translate(inputOne));
        player.sendMessage(ModernChatColorHandler.translate(inputOne, player));
        player.sendMessage(ModernChatColorHandler.translate(inputOne, ParserTypes.color()));
        player.sendMessage(ModernChatColorHandler.translate(inputOne, player, ParserTypes.color()));
        player.sendMessage(ModernChatColorHandler.translate(inputOne, player, ParserTypes.placeholder()));
    }
}
