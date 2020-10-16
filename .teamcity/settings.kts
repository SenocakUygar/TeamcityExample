import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import patches.buildTypes.Release.Release
import patches.buildTypes.ReleaseOrHotfix.ReleaseOrHotfix

version = "2020.1"

project {
    buildType(BuildAndDeploy)

    subProject {
        id = AbsoluteId("GeorgeLifeCycle_george-beancounter-producer_c4a83492a7663dc0a0471d88a6683df8_Release")
        name = "Releases"
        description = "Release Jobs"
        buildType(ReleaseOrHotfix)
        buildType(Release)
    }
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
            scriptContent = "echo 'Hello world!'"
        }
    }
})

