plugins {
    java
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${rootProject.ext["minecraftVersion"]}-R0.1-SNAPSHOT")
    implementation("org.lushplugins:ChatColorHandler:${rootProject.ext["chatcolorhandlerVersion"]}")
    implementation("org.jetbrains:annotations:24.0.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        relocate("org.lushplugins.chatcolorhandler.", "org.lushplugins.chatcolorhandlertest.libraries.chatcolor.")

        val folder = System.getenv("pluginFolder")
        if (folder != null) {
            destinationDirectory.set(file(folder))
        }

        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
    }

    processResources {
        expand(project.properties)

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }
}