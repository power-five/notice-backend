package powerfive.integration

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import powerfive.util.SlackService


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @MockBean
    private lateinit var restTemplate: SlackService

    @LocalServerPort
    var port = 0

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }
}
