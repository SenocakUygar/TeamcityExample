package patches.buildTypes

object Constants {

    // Enterprise Github URL
    const val GITHUB_API_URL = "https://github.beeone.at/api/v3"

    // Token that has rights to access github repository with R/W rights, also needs to have access to releases
    const val GITHUB_API_TOKEN_PLACEHOLDER = "%GITHUB_TOKEN%"

    // Token that has rights to access gitlab repository with R/W rights
    const val GITLAB_API_TOKEN_PLACEHOLDER = "%GITLAB_TOKEN%"

    // Chop suey token to perform automated deployments
    const val ECP_CS_API_TOKEN_PLACEHOLDER = "%ECP_CS_API_TOKEN%"

    const val SONAR_URL_PLACEHOLDER = "%env.SONAR_URL%"

    const val SONAR_TOKEN_PLACEHOLDER = "%SONAR_TOKEN%"
}