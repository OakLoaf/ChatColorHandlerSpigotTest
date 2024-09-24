plugins {
    java
    id("xyz.jpenilla.run-paper") version("2.3.1")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    implementation("org.lushplugins:ChatColorHandler:${findProperty("chatcolorhandlerVersion")}")
    implementation("org.jetbrains:annotations:24.0.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        relocate("org.lushplugins.chatcolorhandler.", "org.lushplugins.chatcolorhandlertest.libraries.chatcolor.")

        archiveFileName.set("${project.name}-${project.version}.jar")
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
    }
}