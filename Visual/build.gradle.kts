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

    // Parent
    implementation(rootProject)
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
