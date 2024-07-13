plugins {
    base
    java
    alias(libs.plugins.fabric.loom)
    id("org.jetbrains.kotlin.jvm") version "2.0.20-Beta2"
}

group = libs.versions.maven.group.get()
version = libs.versions.modpack.get()

base {
    archivesName.set(libs.versions.archives.name)
}

repositories {
    maven {
        name = "devOS Maven"// Porting Lib
        url = uri("https://mvn.devos.one/releases/")
    }
    maven {
        name = "devOS Maven snapshot"// Porting Lib
        url = uri("https://mvn.devos.one/snapshots/")
    }
    maven {
        name = "Modrinth Maven"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }
    maven {
        name = "TerraformersMC Maven"// Mod Menu
        url = uri("https://maven.terraformersmc.com/releases")
    }
    maven {
        name = "Shedaniel Maven"// Cloth Config API, REI
        url = uri("https://maven.shedaniel.me")
    }
    maven {
        name = "tterrag maven"// Create
        url = uri("https://maven.tterrag.com/")
    }
    maven {
        name = "Team Resourceful Maven"// Ad Astra!
        url = uri("https://maven.teamresourceful.com/repository/maven-public/")
    }
    maven {
        name = "Architectury Maven"// Architectury API
        url = uri("https://maven.architectury.dev/")
    }
    maven {
        name = "Greenhouse Maven"// Farmer's Delight Refabricated
        url = uri("https://maven.greenhouseteam.dev/releases/")
    }
    maven {
        // Porting Lib, Mantle
        url = uri("https://maven.jamieswhiteshirt.com/libs-release")
        content {
            includeGroup("com.jamieswhiteshirt")
        }
    }
    maven {
        // FTB
        url = uri("https://maven.saps.dev/releases/")
    }
    maven {
        // Create
        name = "Fuzs Mod Resources"
        url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
    }
    maven {
        // Mantle
        name = "Ladysnake Mods"
        url = uri("https://maven.ladysnake.org/releases")
    }
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io/")
        content {
            includeGroup("com.github")
        }
    }
    maven {
        name = "Wisp Forest Maven"// Mantle
        url = uri("https://maven.wispforest.io/releases")
    }

    mavenCentral()
}

dependencies {
    // Minecraft
    minecraft(libs.minecraft)

    // Mappings
    mappings(loom.layered {
        mappings(file("mappings/mappings-fix.tiny"))
        mappings(variantOf(libs.quilt.mappings) { classifier("intermediary-v2") })
    })

    // Fabric
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)

    // Implemented Mods
    modImplementation(libs.ad.astra) { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation(libs.architectury.api) { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation(libs.farmers.delight) { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation(libs.bundles.mods.from.alphamode) { exclude(group = "com.github.AlphaMode") }
    modImplementation(libs.bundles.mods.from.ftb) { exclude(group = "net.fabricmc.fabric-api") }
    modImplementation(libs.bundles.maven.modrinth)
    modImplementation(libs.bundles.maven.jitpack) { exclude(group = "net.fabricmc.fabric-api") }

    modApi(libs.modmenu) { exclude(group = "net.fabricmc.fabric-api") }
    modApi(libs.cloth.config) { exclude(group = "net.fabricmc.fabric-api") }
    modApi(libs.rei) { exclude(group = "net.fabricmc.fabric-api") }
    modApi(libs.night.auto.config) { exclude(group = "net.fabricmc.fabric-api") }

    // Included
    modApi(libs.tags.binder) { exclude(group = "net.fabricmc.fabric-api") }
    include(libs.tags.binder)
    modApi(libs.brrp) { exclude(group = "net.fabricmc.fabric-api") }
    include(libs.brrp)
    api(libs.exp4j)
    include(libs.exp4j)

    // Development
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks {
    processResources {
        inputs.property("version", libs.versions.modpack.get())

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to libs.versions.modpack.get()))
        }
    }

    jar {
        from("LICENSE") {
            rename { "${it}_${base.archivesName}" }
        }
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

loom {
    accessWidenerPath.set(file("src/main/resources/cabricality.accesswidener"))
}
