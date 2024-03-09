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
    //runtimeOnly("io.r2dbc:r2dbc-h2") conflict with jpa --> todo: resolve
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    implementation("io.mateu:embedded-front:0.0.1-MATEU")
    annotationProcessor("io.mateu:annotation-processing:0.0.1-MATEU")

    implementation(project(":sdks:milter"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
