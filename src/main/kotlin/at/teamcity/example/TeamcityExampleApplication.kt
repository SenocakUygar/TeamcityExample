package at.teamcity.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeamcityExampleApplication

fun main(args: Array<String>) {
    
    runApplication<TeamcityExampleApplication>(*args)
}
