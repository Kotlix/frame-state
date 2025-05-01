import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    api(project(":frame-state-client"))
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
