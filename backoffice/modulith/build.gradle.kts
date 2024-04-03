plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "io.mateu.travel"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //implementation("org.springframework.boot:spring-boot-starter-data-r2dbc") conflict with jpa --> todo: resolve
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    compileOnly("org.projectlombok:lombok")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    //runtimeOnly("io.r2dbc:r2dbc-h2") conflict with jpa --> todo: resolve
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    implementation("io.mateu:embedded-front:2.4.75")
    implementation("io.mateu:jpa:2.4.75")

    annotationProcessor("io.mateu:annotation-processing:2.4.75")
    annotationProcessor("com.google.guava:guava:33.1.0-jre")

    implementation(project(":sdks:milter"))
    implementation(project(":sdks:openjpa"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
