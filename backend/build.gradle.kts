import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.asciidoctor.convert") version "1.5.8"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"
	kotlin("kapt") version "1.5.31"
}

group = "com.hyecheon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val snippetsDir = file("build/generated-snippets")
extra["testcontainersVersion"] = "1.16.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-security")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:kafka")
	testImplementation("org.testcontainers:mariadb")
	testImplementation("com.h2database:h2")
	testImplementation("org.springframework.security:spring-security-test")

	/*p6spy*/
	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.7.1")

//	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("com.auth0:java-jwt:3.16.0")

//	implementation(kotlin("stdlib-jdk8"))
	implementation("org.mapstruct:mapstruct:1.4.2.Final")
	kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")

	/*aop*/
	implementation("org.springframework.boot:spring-boot-starter-aop")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(snippetsDir)
}

tasks.asciidoctor {
	inputs.dir(snippetsDir)
	dependsOn(tasks.test)
}
