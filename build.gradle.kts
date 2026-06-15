plugins {
    java
    id("com.gradleup.shadow") version "9.0.0"
}

group = "br.com.israel"
version = "1.0.0"

repositories {
    mavenCentral()

    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.xerial:sqlite-jdbc:3.50.3.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveFileName.set("plugin-minecraft-demo.jar")
}
