rootProject.name = "frame-state"

pluginManagement {
    plugins {
        val jvmPluginVersion: String by settings
        val springBootVersion: String by settings
        val springDependencyManagementVersion: String by settings
        val ktlintVersion: String by settings

        kotlin("jvm") version jvmPluginVersion
        kotlin("plugin.spring") version jvmPluginVersion
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
    }

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include("frame-state-api")
include("frame-state-client")
include("frame-state-client-starter")
include("frame-state-server")