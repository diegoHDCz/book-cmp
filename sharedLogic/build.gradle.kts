import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidxRoom)
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "SharedLogic"
            isStatic = true
        }
    }
    
    js {
        browser()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    
    android {
       namespace = "br.com.ajudafio.sharedLogic"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        // "mobileMain" não é um source set da hierarquia do Kotlin: é só uma
        // pasta com código Room compartilhado entre Android e iOS (Entity,
        // Dao, Converters, mappers — tudo sem expect/actual). O plugin
        // com.android.kotlin.multiplatform.library (androidMultiplatformLibrary)
        // ainda não respeita dependsOn() de source sets intermediários custom
        // na compilação Android, então em vez de tentar entrar na hierarquia
        // via dependsOn, apontamos os kotlin.srcDirs de androidMain/iosMain
        // direto para essa pasta. Room não publica artefatos para js/wasmJs,
        // então esse código não pode viver em commonMain (compilado também
        // para esses dois targets).
        val mobileSharedDir = "src/mobileMain/kotlin"

        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinxJson)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.get().kotlin.srcDir(mobileSharedDir)
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
            implementation(libs.androidx.room)
            implementation(libs.androidx.sqlite.bundled)
        }
        iosMain.get().kotlin.srcDir(mobileSharedDir)
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.androidx.room)
            implementation(libs.androidx.sqlite.bundled)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
        webMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
}