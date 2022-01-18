// Imports:
import kotlin.text.Charsets.UTF_8

// Both 'group' and 'version' are default Gradle properties, so they need to be set here
group = "com.jordijaspers"
version = "0.0.1-SNAPSHOT"

// Other, non-default Gradle, properties need to be defined here
val artifactName = "webflux-api-annotations"
val artifactDescription = "experimenting with Spring WebFlux"
var vendor = "jordi-jaspers"
val user = null
val pwd = null

plugins {
	// Pretty obvious why this is needed.
	java

	// Quality plugin for checkstyle.
	checkstyle

	// Quality plugin for PMD.
	pmd

	// Adds intelliJ tasks to the build file and creates the settings in intellij correctly
	idea

	// Our tests are using groovy with the spock framework, this adds the compileTestGroovy task and the watch to the test/groovy folder
	groovy

	// embedded java code coverage by testing, formerly known as EclEmma.
	jacoco

	// The spring boot plugin reacts to other plugins such as the java plugin.
	// In case dependency management is also available, the Spring Bill Of Materials will be injected containing versions of spring libraries.
	// see https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/
	id("org.springframework.boot") version "2.6.2"

	// this plugin adds the integration test task to our build file with the default 'itest' directory.
	// see https://github.com/Softeq/itest-gradle-plugin
	id("com.softeq.gradle.itest") version "1.0.4"

	// non standard plugins quality plugins for gradle
	id("com.github.spotbugs") version "4.7.2"

	// The project-report plugin provides file reports on dependencies, tasks, etc.
	// See https://docs.gradle.org/current/userguide/project_report_plugin.html.
	id("project-report")

	// Dependency management plugin.
	// The Spring Boot plugin’s dependency on the dependency management plugin means that you can use the dependency management plugin
	// without having to declare a dependency on it. This also means that you will automatically use the same version of the
	// dependency management plugin as Spring Boot uses.
	// NOTE:   Each Spring Boot release is designed and tested against a specific set of third-party dependencies. Overriding versions may cause
	//         compatibility issues and should be done with care.
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

/**
 * Setting the metadata for the project.
 */
springBoot {
	buildInfo {
		properties {
			artifact = artifactName
			version = project.version.toString()
			group = project.group.toString()
			name = artifactDescription
		}
	}
}

/**
 * Java 17 is long term supported and therefore chosen as the default.
 */
java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

/**
 * The repositories used to download the dependencies.
 */
repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	// ======= COMPILE ONLY DEPENDENCIES =======

	// This brings in the SuppressFBWarnings
	compileOnly(group = "com.github.spotbugs", name = "spotbugs-annotations", version = "4.3.0")

	// ======= IMPLEMENTATION DEPENDENCIES =======

	// Starter for using MongoDB document-oriented database and Spring Data MongoDB Reactive
	implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-mongodb-reactive", version = "2.6.2")

	// Starter for building WebFlux applications using Spring Framework's Reactive Web support
	implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux", version = "2.6.2")
	implementation(group = "org.springframework", name = "spring-webflux", version = "5.3.15")

	// mapstruct is used to generate code to map from domain model classes to rest application model classes
	implementation(group = "org.mapstruct", name = "mapstruct", version = "1.4.2.Final")
	annotationProcessor(group = "org.mapstruct", name = "mapstruct-processor", version = "1.4.2.Final")

	// An embedded mongodb dependency.
	implementation(group = "de.flapdoodle.embed", name = "de.flapdoodle.embed.mongo", version = "3.2.8")

	// Logstash encoder for logging in JSON format
	implementation(group = "net.logstash.logback", name = "logstash-logback-encoder", version = "6.3")

	// Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients.
	implementation("io.projectreactor.netty:reactor-netty:1.0.15")


	// General data-binding functionality for Jackson: works on core streaming API
	implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.13.1")

	// contains the autoconfiguration for Hawaii logging.
//	implementation(group = "org.hawaiiframework", name = "hawaii-starter-logging", version = "3.0.0.M23")

	// provides the core of hawaii framework such as the response entity exception handling.
//	implementation("org.hawaiiframework", name = "hawaii-core", version = "3.0.0.M23")

	// provides an async framework which enables us to control query and rest endpoints timeouts.
//	implementation("org.hawaiiframework", name = "hawaii-async", version = "3.0.0.M23")

	// contains the autoconfiguration of the exception mapping to http error codes.
//	implementation(group = "org.hawaiiframework", name = "hawaii-starter-rest", version = "3.0.0.M23")

	// ======= TEST DEPENDENCIES =======

	// To have the @SpringBootTest annotation
	testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test", version = "2.6.2") {
		exclude(group = "com.vaadin.external.google")
	}

	// due to the dependency to spock, we also need groovy
	testImplementation(group = "org.codehaus.groovy", name = "groovy-all", version = "3.0.8")

	// www.spockframework.org is the groovy based test framework providing the specifications for our tests.
	testImplementation(group = "org.spockframework", name = "spock-core", version = "2.1-M2-groovy-3.0")

	// Spock Spring bindings
	// See https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing-spring-boot-applications-with-spock
	testImplementation(group = "org.spockframework", name = "spock-spring", version = "2.1-M2-groovy-3.0")

	// Reactor Test support.
	testImplementation(group = "io.projectreactor", name = "reactor-test", version = "3.4.14")
}

/**
 * Make the itest source set contain the main and test classpath, so we can use test (helper) code in itest as well.
 */
itestSourceSet {
	compileClasspath = sourceSets["main"].compileClasspath +
			sourceSets["main"].output +
			sourceSets["test"].compileClasspath +
			sourceSets["test"].output
	runtimeClasspath = sourceSets["main"].runtimeClasspath +
			sourceSets["main"].output +
			sourceSets["test"].runtimeClasspath +
			sourceSets["test"].output
}

/**
 * Spock 2 uses the new Junit platform to execute the tests.
 * @see "https://discuss.gradle.org/t/spock-tests-dont-run-with-gradle-7-groovy-3/40139"
 */
tasks.withType<Test> {
	useJUnitPlatform()
}

/**
 * Override default java compiler settings
 */
tasks.withType<JavaCompile> {
	// override default false
	options.isDeprecation = true
	// defaults to use the platform encoding
	options.encoding = UTF_8.name()
	// add Xlint to our compiler options (but disable processing because of Spring warnings in code)
	// and make warnings be treated like errors
	options.compilerArgs.addAll(arrayOf("-Xlint:all", "-Xlint:-processing", "-Werror"))
}

/**
 * Create manifest settings.
 */
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	manifest {
		attributes("Specification-Title" to artifactDescription)
		attributes("Specification-Vendor" to vendor)

		attributes("Name" to artifactDescription)
		attributes("Implementation-Title" to artifactDescription)
		attributes("Implementation-Version" to project.version.toString())
		attributes("Implementation-Vendor" to vendor)

	}
}

/**
 * Configuration of checkstyle.
 */
checkstyle {
	configFile = file("/config/checkstyle/checkstyle.xml")
}

/**
 * Configuration of PMD.
 */
pmd {
	// as a development team we want pmd failures to break the build and keep the code clean.
	isIgnoreFailures = false
	// directly show the failures in the output
	isConsoleOutput = true
	// the configuration of the custom rules
	ruleSetConfig = resources.text.fromFile(projectDir.path + "/config/pmd/pmd.xml")
	// clear the default list of rules, otherwise this will override our custom configuration.
	ruleSets = listOf<String>()
	toolVersion = "6.31.0"
}

/**
 * Configuration of spotbugs with our exclusion configuration to enable the report format on CI in XML and local in HTML.
 */
spotbugs {
	val format = findProperty("spotbugsReportFormat")
	val xmlFormat = (format == "xml")
	showProgress.set(true)
	excludeFilter.set(project.file("/config/spotbugs/exclude.xml"))

	tasks.spotbugsMain {
		reports.create("html") {
			isEnabled = !xmlFormat
		}
		reports.create("xml") {
			isEnabled = xmlFormat
		}
	}
	tasks.spotbugsTest {
		reports.create("html") {
			isEnabled = !xmlFormat
		}
		reports.create("xml") {
			isEnabled = xmlFormat
		}

	}
}

/**
 * Generates test report after test task.
 * Produces 'jacocoTestReport.xml' in `build/reports/jacoco/test/`.
 */
tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

/**
 * Make sure the report is in XML format, instead of binary.
 */
tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
	}
}
