import org.springframework.boot.gradle.tasks.bundling.BootJar

extra["springCloudVersion"] = "2022.0.4"

dependencies {
    implementation(project(":community-application"))
    implementation(project(":community-domain"))
    implementation(project(":community-in-api"))
    implementation(project(":community-infrastructure"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks {
    withType<Jar> { enabled = false }
    withType<BootJar> {
        enabled = true
        mainClass.set("gloddy.CommunityApplicationKt")
    }
}
