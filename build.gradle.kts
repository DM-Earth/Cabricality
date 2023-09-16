plugins {
    base
    java
    `maven-publish`
    alias(libs.plugins.quilt.loom)
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
}

group = property("maven_group").toString()
version = property("modpack_version").toString()

base {
    archivesName.set(rootProject.property("archives_name").toString())
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
        name = "Curse Maven"
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
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

    maven { url = uri("https://aperlambda.github.io/maven") }
    maven { url = uri("https://dvs1.progwml6.com/files/maven/") }
    maven { url = uri("https://maven.cafeteria.dev/releases/") }
    maven { url = uri("https://maven.fabricmc.net/") }
    maven { url = uri("https://maven.gegy.dev") }
    maven { url = uri("https://maven.kotlindiscord.com/repository/terraformers/") }
    maven { url = uri("https://maven.parchmentmc.org") }
    maven { url = uri("https://maven.shedaniel.me/") }
    maven { url = uri("https://maven.terraformersmc.com/releases/") }
    maven { url = uri("https://maven.tterrag.com/") }
    maven { url = uri("https://maven.wispforest.io") }
    maven { url = uri("https://modmaven.dev") }
    maven { url = uri("https://mvn.devos.one/snapshots/") }
    maven { url = uri("https://storage.googleapis.com/devan-maven/") }

    mavenCentral()
}

dependencies {
    // Minecraft
    minecraft(libs.minecraft)

    // Mappings
    mappings(
        variantOf(libs.quilt.mappings) { classifier("intermediary-v2") }
    )

    // Quilt
    modImplementation(libs.quilt.loader)
    modImplementation(libs.quilted.fabric.api)

    // Create
    modImplementation(
        "com.simibubi.create:create-fabric-" +
                "${property("minecraft_version")}" + ":" +
                "${property("create_version")}" +
                "+mc" + "${property("minecraft_version")}"
    ) { exclude(group = "com.github.AlphaMode") }

    // Implemented Mods
    // - Miscellaneous
    modImplementation(
        "slimeknights.mantle:Mantle:" +
                "${property("minecraft_version")}" + "-" +
                "${property("mantle_version")}"
    ) { exclude(group = "com.github.AlphaMode") }

    // - Modrinth Maven
    modImplementation("maven.modrinth:ad-astra:${property("ad_astra_version")}")
    modImplementation("maven.modrinth:architectury-api:${property("architectury_version")}+fabric")
    modImplementation("maven.modrinth:bits-and-chisels:${property("bits_and_chisels_version")}")
    modImplementation("maven.modrinth:farmers-delight-fabric:${property("farmers_delight_version")}")
    implementation("maven.modrinth:hephaestus:${property("minecraft_version")}-${property("hephaestus_version")}")

    // - Curse Maven
    modImplementation("curse.maven:industrial-revolution-391708:${property("indrev_version")}")
    modImplementation("curse.maven:ftb-quests-fabric-438496:${property("ftb_quests_version")}")
    modImplementation("curse.maven:ftb-library-fabric-438495:${property("ftb_library_version")}")
    //modImplementation("curse.maven:malum-quilt-627875:${property("malum_version")}")

    // - JitPack
    modImplementation("com.github.KrLite.Equator-v2:build:${property("minecraft_major_version")}-${property("equator_version")}")

    // Mod Apis
    modApi("com.terraformersmc:modmenu:${property("modmenu_version")}")
    modApi("me.shedaniel.cloth:cloth-config-fabric:${property("cloth_config_version")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    // Included
    modImplementation(include("com.github.DM-Earth:Tags-Binder:${property("tags_binder_version")}")!!)
    modImplementation(include("maven.modrinth:brrp:${property("brrp_version")}-mc${property("minecraft_version")}-fabric")!!)

    implementation(include("net.objecthunter:exp4j:${property("exp4j_version")}")!!)
    implementation(include("com.github.KrLite:Pierced:${property("pierced_version")}")!!)
    implementation(include("com.github.davidmoten:word-wrap:${property("word_wrap_version")}")!!)
    implementation(include("com.github.davidmoten:guava-mini:${property("guava_mini_version")}")!!)

    // Development
    compileOnlyApi("org.apiguardian:apiguardian-api:${property("apiguardian_version")}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-fabric:${property("rei_version")}")
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:${property("rei_version")}")
    modRuntimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:${property("rei_version")}")
}

tasks {
    processResources {
        inputs.property("modpack_version", version)

        filesMatching("quilt.mod.json") {
            expand(mapOf("modpack_version" to version))
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
    setSourceCompatibility(JavaVersion.VERSION_17)
    setTargetCompatibility(JavaVersion.VERSION_17)
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

loom {
    accessWidenerPath.set(file("src/main/resources/cabricality.accesswidener"))
}
