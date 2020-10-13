package at.teamcity.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeamcityExampleApplicationTests {

    @Autowired
    private val testRestTemplate = TestRestTemplate()

    @Test
    fun contextLoads() {
        val response = testRestTemplate.exchange("/", HttpMethod.GET, null, String::class.java)
        assertEquals(HttpStatus.OK,response.statusCode)
        assertEquals("Hello World", response.body)
    }
}
