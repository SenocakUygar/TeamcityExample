import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

version = "2020.1"

project {
    buildType(HelloWorld)
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
    }
})

object HelloWorld: BuildType({
    name = "Hello world"
    steps {
        script {
            scriptContent = "echo 'Hello world!'"
        }
    }
})
