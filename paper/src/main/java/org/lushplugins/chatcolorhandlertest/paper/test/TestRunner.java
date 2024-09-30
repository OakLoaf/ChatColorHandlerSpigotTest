package org.lushplugins.chatcolorhandlertest.paper.test;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

public class TestRunner {
    private boolean ran = false;
    private final @Nullable Player player;
    private final List<Test> tests = new ArrayList<>();

    public TestRunner() {
        this(null);
    }

    public TestRunner(@Nullable Player player) {
        this.player = player;
    }

    public TestRunner addTest(@NotNull String input, @Nullable String expectedOutput, @NotNull Function<String, String> translate) {
        this.tests.add(new Test(input, expectedOutput, translate));
        return this;
    }

    public void run() {
        if (ran) {
            throw new IllegalStateException("This test has already been ran");
        }
        ran = true;

        for (Test test : tests) {
            test.run(player);
        }
    }

    record Test(@NotNull String input, @Nullable String expectedOutput, @NotNull Function<String, String> translate) {

        private String run() {
            return run(null);
        }

        private String run(Player player) {
            String output = "";
            try {
                output = translate.apply(input);
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.WARNING, e.getMessage(), e);
            }

            if (expectedOutput == null || expectedOutput.equals(output)) {
                Bukkit.getLogger().info("Test passed:");
                Bukkit.getLogger().info("  Input '" + input + "' passed tests.");
                Bukkit.getLogger().info("  Received: '" + output + "'");

                if (player != null) {
                    player.sendMessage("§a✔§r: " + output);
                }
            } else {
                Bukkit.getLogger().info("Test failed:");
                Bukkit.getLogger().info("  Input '" + input + "'");
                Bukkit.getLogger().info("  Expected: '" + expectedOutput + "'");
                Bukkit.getLogger().info("  Received: '" + output + "'");

                if (player != null) {
                    player.sendMessage("§c✕§r: " + output);
                }
            }

            return output;
        }
    }
}
