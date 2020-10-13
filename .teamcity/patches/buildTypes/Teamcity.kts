package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Teamcity'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("Teamcity")
    name = "Teamcity"
}))

