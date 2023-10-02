plugins {
    base
    java
    `maven-publish`
    alias(libs.plugins.quilt.loom)
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
}

group = property("mavenGroup").toString()
version = property("modpackVersion").toString()

base {
    archivesName.set(rootProject.property("archivesName").toString())
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

    maven { url = uri("https://mvn.devos.one/snapshots/") }

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
    maven { url = uri("https://raw.githubusercontent.com/SolidBlock-cn/mvn-repo/main") }
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
                "${property("minecraftVersion")}" + ":" +
                "${property("createVersion")}" +
                "+mc" + "${property("minecraftVersion")}"
    ) { exclude(group = "com.github.AlphaMode") }

    // Implemented Mods
    // - Miscellaneous
    modImplementation(
        "slimeknights.mantle:Mantle:" +
                "${property("minecraftVersion")}" + "-" +
                "${property("mantleVersion")}"
    ) { exclude(group = "com.github.AlphaMode") }

    // - Modrinth Maven
    modImplementation("maven.modrinth:ad-astra:${property("adAstraVersion")}")
    modImplementation("maven.modrinth:architectury-api:${property("architecturyVersion")}+fabric")
    modImplementation("maven.modrinth:bits-and-chisels:${property("bitsAndChiselsVersion")}")
    modImplementation("maven.modrinth:farmers-delight-fabric:${property("farmersDelightVersion")}")
    modImplementation("maven.modrinth:hephaestus:${property("minecraftVersion")}-${property("hephaestusVersion")}")
    modImplementation("maven.modrinth:let:${property("letVersion")}-mc${property("minecraftMajorVersion")}")

    // - Curse Maven
    modImplementation("curse.maven:industrial-revolution-391708:${property("indrevVersion")}")
    modImplementation("curse.maven:ftb-quests-fabric-438496:${property("ftbQuestsVersion")}")
    modImplementation("curse.maven:ftb-library-fabric-438495:${property("ftbLibraryVersion")}")

    // - JitPack
    modImplementation("com.github.KrLite.Equator-v2:build:${property("equatorVersion")}-mc${property("minecraftMajorVersion")}")

    // Mod Apis
    modApi("com.terraformersmc:modmenu:${property("modmenuVersion")}")
    modApi("me.shedaniel.cloth:cloth-config-fabric:${property("clothConfigVersion")}") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    // Included
    include("com.github.DM-Earth:Tags-Binder:${property("tagsBinderVersion")}")?.let {
        modApi(it)
    }

    include("pers.solid:brrp-fabric:${property("brrpVersion")}-${property("minecraftVersion")}")?.let {
        modApi(it)
    }

    include("com.github.KrLite:Pierced:${property("piercedVersion")}")?.let {
        api(it)
    }

    include("net.objecthunter:exp4j:${property("exp4jVersion")}")?.let {
        implementation(it)
    }

    // Development
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    modCompileOnly("me.shedaniel:RoughlyEnoughItems-default-plugin-fabric:${property("reiVersion")}")
    modCompileOnly("me.shedaniel:RoughlyEnoughItems-api-fabric:${property("reiVersion")}")
    modRuntimeOnly("me.shedaniel:RoughlyEnoughItems-fabric:${property("reiVersion")}")
}

tasks {
    processResources {
        inputs.property("modpackVersion", version)

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
