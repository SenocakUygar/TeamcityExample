package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2019_2.ParameterDisplay
import patches.buildTypes.Constants.Constants.GITHUB_API_TOKEN_PLACEHOLDER

object Release : BuildType({
    name = "Release"
    type = Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        select(
            "env.releaseType", "", display = ParameterDisplay.PROMPT,
            options = listOf("Major", "Minor", "Patch")
        )
    }
    vcs {
        root(DslContext.settingsRoot)
        branchFilter = "+:<default>"
        showDependenciesChanges = true
    }

    steps {
        setBuildNumber()
        gradleStep("Release", "release", "--githubToken $GITHUB_API_TOKEN_PLACEHOLDER --releaseType %env.releaseType%")
    }
    params {
        text("teamcity.ui.runButton.caption", "Release🚀", display = ParameterDisplay.HIDDEN, readOnly = true)
    }
})