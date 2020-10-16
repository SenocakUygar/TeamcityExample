package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.BuildSteps
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

fun BuildSteps.setCredentials() {
    gradleStep("Set Username", "addCredentials", "--key georgeUser", "--value %NEXUS_USERNAME%")
    gradleStep("Set Password", "addCredentials", "--key georgePassword", "--value %NEXUS_PASSWORD%")
}

fun BuildSteps.cancelOlderBuilds() {
    script {
        name = "Cancel older builds"
        scriptContent =
            """curl -v -u %system.teamcity.auth.userId%:%system.teamcity.auth.password% --request POST "%teamcity.serverUrl%/app/rest/buildQueue?locator=branch:pull%2F1747" --data "<buildCancelRequest comment='Canceled by newer build' readdIntoQueue='false' />" --header "Content-Type: application/xml""""
    }
}

fun BuildSteps.setBuildNumber() {
    script {
        name = "Set Build Number"
        scriptContent = """
                PROJECT_VERSION="${'$'}(./gradlew -q printVersion)"
                echo "Project Version: ${'$'}{PROJECT_VERSION}"
                echo "##teamcity[buildNumber '${'$'}{PROJECT_VERSION}+%build.counter%']"
            """.trimIndent()
    }
}

fun BuildSteps.gradleTest() {
    gradle {
        name = "Tests"
        tasks = "clean test jar -Pnologging"
        buildFile = "build.gradle.kts"
        jdkHome = "%env.JDK_11%"
        param("gradle.init.script.name", "platform.init.gradle.kts")
        param("teamcity.coverage.jacoco.classpath", "**/build/classes/*")
        coverageEngine = jacoco {
            classLocations = "**/build/classes/*"
            excludeClasses = "+:*"
            jacocoVersion = "%teamcity.tool.jacoco.0.8.4%"
        }

    }
}

fun BuildSteps.gradleStep(stepName: String, vararg taskList: String) {
    gradle {
        name = stepName
        tasks = "clean " + taskList.reduce { acc, task -> "$acc $task" }
        buildFile = "build.gradle.kts"
        jdkHome = "%env.JDK_11%"
        gradleParams = "--refresh-dependencies"
        param("gradle.init.script.name", "platform.init.gradle.kts")
    }
}