buildscript {

  val kotlinVersion = "1.2.41"
  val springBootVersion = "2.0.2.RELEASE"

  repositories {
    mavenCentral()
    jcenter()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
  }
}

allprojects {
  ext {
    this["springStatemachineVersion"] = "2.0.1.RELEASE"
    this["kotlinVersion"] = "1.2.41"
  }
}

repositories {
  mavenCentral()
  jcenter()
}

plugins {
  val kotlinVersion = "1.2.41"
  val springBootVersion = "2.0.2.RELEASE"
  val springDependencyManagementVersion = "1.0.5.RELEASE"

  java
  idea

  id("org.jetbrains.kotlin.jvm") version kotlinVersion
  id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
  id("org.springframework.boot") version springBootVersion
  id("io.spring.dependency-management") version springDependencyManagementVersion
}

group = "net.lab0.treasure"
version = "0.0.1-SNAPSHOT"

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}


dependencies {
  compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")

  compile("com.google.guava:guava:25.1-jre")

  compile("org.springframework.shell:spring-shell-starter:2.0.0.RELEASE")
  compile("org.springframework.statemachine:spring-statemachine-starter")

  compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  compile("org.jetbrains.kotlin:kotlin-reflect")

  testCompile("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.statemachine:spring-statemachine-bom:${ext["springStatemachineVersion"]}")
  }
}
