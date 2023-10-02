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
    archivesName.set("${property("archivesName")}-${property("moduleBaseName")}")
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

    modImplementation("com.github.KrLite.Equator-v2:build:2.6.0-mc1.20")
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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
