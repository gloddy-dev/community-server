import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

tasks {
	withType<Jar> { enabled = true }
	withType<org.springframework.boot.gradle.tasks.bundling.BootJar> { enabled = false }
}

subprojects {

	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")

	dependencies{
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	}

	group = "gloddy"
	version = "0.0.1-SNAPSHOT"
	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	repositories {
		mavenCentral()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
