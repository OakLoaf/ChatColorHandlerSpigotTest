plugins {
    java
    id("io.github.goooler.shadow") version("8.1.7")
}

allprojects {
    apply(plugin="io.github.goooler.shadow")

    group = "org.lushplugins"
    version = "1.0.0"

    repositories {
        mavenCentral()
        mavenLocal()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") // Spigot
        maven("https://repo.papermc.io/repository/maven-public/") // Paper
        maven("https://repo.lushplugins.org/snapshots") // ChatColorHandler
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
    }
}