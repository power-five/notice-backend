package powerfive.integration

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import powerfive.dto.ImageRequest
import powerfive.dto.NoticeRequest
import powerfive.dto.NoticeResponse
import powerfive.dto.NoticesResponse

class NoticeIntegrationTest : IntegrationTest() {

    @Test
    fun `공지사항을 저장할 수 있다`() {
        val noticeRequest = NoticeRequest("제목", "내용", 1, listOf(ImageRequest("a"), ImageRequest("b")))
        val response: ExtractableResponse<Response> = postNotice(noticeRequest)
        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        Assertions.assertThat(response.header("Location")).isNotBlank()
    }

    @Test
    fun `공지사항을 단건 조회할 수 있다`() {
        // given
        val noticeRequest = NoticeRequest("제목", "내용", 1, listOf(ImageRequest("a"), ImageRequest("b")))
        val createdUrl = postNotice(noticeRequest)

        // when
        val response: ExtractableResponse<Response> = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().get(createdUrl.header("Location"))
                .then().log().all().extract()

        val noticeResponse: NoticeResponse = response.`as`(NoticeResponse::class.java)
        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(noticeResponse.title).isEqualTo(noticeRequest.title)
        Assertions.assertThat(noticeResponse.description).isEqualTo(noticeRequest.description)
    }

    @Test
    fun `공지사항을 모두 조회할 수 있다`() {
        // given
        val noticeRequest = NoticeRequest("제목", "내용", 1, listOf())
        val noticeRequest2 = NoticeRequest("제목2", "내용2", 1, listOf(ImageRequest("a")))
        val noticeRequest3 = NoticeRequest("제목3", "내용3", 1, listOf(ImageRequest("a"), ImageRequest("b")))
        val createdUrl = postNotice(noticeRequest)
        val createdUrl2 = postNotice(noticeRequest2)
        val createdUrl3 = postNotice(noticeRequest3)

        // when
        val response: ExtractableResponse<Response> = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().get("/notice")
                .then().log().all().extract()

        val noticeResponse: NoticesResponse = response.`as`(NoticesResponse::class.java)
        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(noticeResponse.notices).hasSize(3)
    }

    private fun postNotice(noticeRequest: NoticeRequest): ExtractableResponse<Response> {
        val response: ExtractableResponse<Response> = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(noticeRequest)
                .`when`().post("/notice")
                .then().log().all().extract()
        return response
    }
}
