import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.9.10"
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
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
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation("io.ktor:ktor-client-core:2.3.4")
                    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
                    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
                    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                }
            }
            val androidMain by getting {
                dependencies {
                    implementation("io.ktor:ktor-client-okhttp:2.3.4")
                }
            }
//            val iosMain by getting {
//                dependencies {
//                    implementation("io.ktor:ktor-client-darwin:2.3.4")
//                }
//            }
        }
    }
}

android {
    namespace = "com.nextgen.focusfight"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0") // <-- Required for `Json`
}
