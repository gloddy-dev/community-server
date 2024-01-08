import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":community-domain"))

    implementation("org.springframework:spring-tx")
    implementation("org.springframework:spring-context")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
    implementation("jakarta.validation:jakarta.validation-api")
    testImplementation(testFixtures(project(":community-domain")))
}
