import org.jetbrains.kotlin.fir.declarations.builder.buildTypeAlias
import org.jetbrains.kotlin.gradle.plugin.sources.dependsOnClosure

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-28"
    kotlin("plugin.serialization") version "1.9.20"
    id("app.cash.sqldelight") version "2.0.0"
    alias(libs.plugins.kotlinCocoapods)

}

kotlin {

    cocoapods {
        version = "1.0.0"

        name = "DailyPulse"

        summary = "Descrição resumida do seu KMM"
        homepage = "https://seuprojeto.com"
        authors = "Seu Nome ou Equipe"
        license = "MIT"

        extraSpecAttributes["libraries"] = "['sqlite3']"
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"

            }
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            linkerOpts("-lsqlite3")
        }

    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class).configureEach {
        binaries.all {
            linkerOpts("-lsqlite3")
        }
    }


    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            implementation(libs.ktor.client.core)
            implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
            implementation("io.ktor:ktor-client-cio:2.3.2")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation("io.insert-koin:koin-core:3.4.3")
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.0")
        }

        androidMain.dependencies {
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
            implementation("app.cash.sqldelight:android-driver:2.0.0")
        }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.2")
            implementation("app.cash.sqldelight:native-driver:2.0.0")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}


android {
    namespace = "com.example.dailypulse"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        buildConfigField("String", "BASE_URL", "\"${project.property("BASE_URL")}\"")
        buildConfigField("String", "API_KEY", "\"${project.property("API_KEY")}\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

sqldelight {
    databases {
        create(name = "DailyPulseDatabase") {
            packageName.set("com.example.dailypulse.db")
            migrationOutputDirectory.set(
                file("src/commonMain/sqldelight/com/example/dailypulse/db/migrations")
            )
            schemaOutputDirectory.set(
                file("src/commonMain/sqldelight/com/example/dailypulse/db/schema")
            )
            version = 1
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:+")
}
