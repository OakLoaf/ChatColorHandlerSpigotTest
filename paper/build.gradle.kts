plugins {
    java
    id("xyz.jpenilla.run-paper") version("2.3.1")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${rootProject.ext["minecraftVersion"]}-R0.1-SNAPSHOT")
    implementation("org.lushplugins:ChatColorHandler:${rootProject.ext["chatcolorhandlerVersion"]}")
    implementation("org.jetbrains:annotations:24.0.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        relocate("org.lushplugins.chatcolorhandler.", "org.lushplugins.chatcolorhandlertest.libraries.chatcolor.")

        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
    }

    processResources{
        expand(project.properties)

        inputs.property("version", rootProject.version)
        filesMatching("paper-plugin.yml") {
            expand("version" to rootProject.version)
        }
    }

    runServer {
        minecraftVersion("1.21")

        downloadPlugins {
            modrinth("viaversion", "5.0.3")
            modrinth("viabackwards", "5.0.3")
            hangar("PlaceholderAPI", "2.11.6")
        }
    }
}