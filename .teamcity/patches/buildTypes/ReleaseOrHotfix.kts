package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2019_2.ParameterDisplay

object ReleaseOrHotfix : BuildType({
    name = "Patch/Hotfix"
    type = Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        checkbox(
            "env.dryRun", ""
        )
    }
    vcs {
        root(DslContext.settingsRoot)
        branchFilter = "+:refs/tags/(*)"
        showDependenciesChanges = true
    }

    steps {
        setBuildNumber()
        gradleStep(
            "Release",
            "release",
            "--githubToken ${Constants.Constants.GITHUB_API_TOKEN_PLACEHOLDER} --releaseType Patch"
        )
    }
    params {
        text("teamcity.ui.runButton.caption", "Release🚀", display = ParameterDisplay.HIDDEN, readOnly = true)
    }
})
