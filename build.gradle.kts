plugins {
    java
    `java-library`
    application
    distribution
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.wire.bots.simulation"
version = "0.0.1"

repositories {
    jcenter()

    // lithium
    maven {
        url = uri("https://packagecloud.io/dkovacevic/lithium/maven2")
    }

    // transitive dependency for the lithium
    maven {
        url = uri("https://packagecloud.io/dkovacevic/cryptobox4j/maven2")
    }
}
val mClass = "com.wire.bots.simulation.server.SimulationService"

shadow {
    applicationDistribution.from("src/dist")
}

application {
    mainClassName = mClass
}

dependencies {
    api("com.wire.bots", "lithium", "2.34.5")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    shadowJar {
        mergeServiceFiles()
        manifest {
            attributes(
                mapOf(
                    "Main-Class" to mClass
                )
            )
        }
    }

    register<Jar>("fatJar") {
        manifest {
            attributes["Main-Class"] = "com.wire.bots.simulation.server.SimulationService"
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("simulation.jar")
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        from(sourceSets.main.get().output)
    }
}
