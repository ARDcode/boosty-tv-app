plugins {
    alias(libs.plugins.kotlin.jvm)
}

// Pure-JVM module: there is no AGP `compileOptions` block here, so javac would
// otherwise default to the JDK running Gradle (17) while Kotlin targets 11.
// The Kotlin plugin's JVM-target consistency check fails the build on that
// mismatch, so both halves are pinned explicitly.
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        // Emit Java 11 bytecode. The Gradle daemon itself still runs on JDK 17+
        // because AGP 8 requires it -- this only sets the output class version.
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
