plugins {
    base
    java
    `maven-publish`
    alias(libs.plugins.fabric.loom)
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
}

group = libs.versions.maven.group.get()
version = libs.versions.modpack.get()

base {
    archivesName.set(libs.versions.archives.name)
}

repositories {
    maven {
        name = "Modrinth Maven"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }

    maven {
        name = "JitPack"
        url = uri("https://jitpack.io/")
    }

    maven {
        url = uri("https://maven.ladysnake.org/releases")
        content {
            includeGroup("io.github.ladysnake")
            includeGroup("org.ladysnake")
            includeGroupByRegex("dev\\.onyxstudios.*")
        }
    }

    maven {
        url = uri("https://maven.jamieswhiteshirt.com/libs-release")
        content {
            includeGroup("com.jamieswhiteshirt")
        }
    }

    maven { url = uri("https://maven.quiltmc.org/repository/release") }
    maven { url = uri("https://mvn.devos.one/snapshots/") }
    maven { url = uri("https://maven.saps.dev/releases/") }
    maven { url = uri("https://aperlambda.github.io/maven") }
    maven { url = uri("https://dvs1.progwml6.com/files/maven") }
    maven { url = uri("https://maven.cafeteria.dev/releases") }
    maven { url = uri("https://maven.fabricmc.net") }
    maven { url = uri("https://maven.gegy.dev") }
    maven { url = uri("https://maven.kotlindiscord.com/repository/terraformers") }
    maven { url = uri("https://maven.parchmentmc.org") }
    maven { url = uri("https://maven.shedaniel.me") }
    maven { url = uri("https://maven.terraformersmc.com/releases") }
    maven { url = uri("https://maven.tterrag.com") }
    maven { url = uri("https://maven.wispforest.io") }
    maven { url = uri("https://modmaven.dev") }
    maven { url = uri("https://storage.googleapis.com/devan-maven") }

    mavenCentral()
}

dependencies {
    // Minecraft
    minecraft(libs.minecraft)

    // Mappings
    mappings(
        variantOf(libs.quilt.mappings) { classifier("intermediary-v2") }
    )

    // Fabric
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)

    // Implemented Mods
    modImplementation(libs.bundles.mods.from.dmearth)
    modImplementation(libs.bundles.mods.from.alphamode) { exclude(group = "com.github.AlphaMode") }
    modImplementation(libs.bundles.mods.from.ftb)
    modImplementation(libs.bundles.maven.modrinth)
    modImplementation(libs.bundles.maven.jitpack)

    modApi(libs.modmenu)
    modApi(libs.cloth.config)
    modApi(libs.rei)

    // Included
    modApi(libs.tags.binder)?.let {
        include(it)
    }
    modApi(libs.brrp)?.let {
        include(it)
    }
    api(libs.exp4j)?.let {
        include(it)
    }

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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
    }
}
