import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

version = "2020.1"

project {
    buildType(BuildAndDeploy)
}

object BuildAndDeploy : BuildType({
    name = "Build And Deploy"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        print("Maven clean test is runned!")
        maven {
            goals = "clean install"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }

        script {
            scriptContent = "echo Hello World"
        }
    }
})
