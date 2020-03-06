plugins {
    java
    `java-library`
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

dependencies {
    api("com.wire.bots", "lithium", "2.34.5")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
