package powerfive.util

import com.slack.api.Slack
import com.slack.api.model.Attachment
import com.slack.api.model.Field
import com.slack.api.webhook.Payload
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import powerfive.dto.NoticeRequest
import java.lang.String.valueOf
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class SlackService(
        @Value("\${slack.webhook}") private val webhook: String
) {

    fun sendMessageToSlack(request: NoticeRequest) {
        val slack = Slack.getInstance()

        val post = toPost(request)

        val payload: Payload = Payload.builder()
                .attachments(listOf(post))
                .build()

        slack.send(webhook, payload)
    }

    private fun toPost(request: NoticeRequest): Attachment {
        val contents: List<Field> = request.description
                .split(DELIMITER)
                .map { Field.builder().value(it).build() }
                .toList()

        val post = Attachment.builder()
                .pretext("$TAG ${request.title}")
                .color(CONTENT_COLOR)
                .fields(contents)
                .footer(CREATED)
                .ts(valueOf(Timestamp.valueOf(LocalDateTime.now()).time))

        return if (request.images.isEmpty()) post.build() else post.imageUrl(request.images[0].imageUrl).build()
    }

    companion object {
        const val DELIMITER: String = "\n"
        const val TAG: String = "<!channel>"
        const val CONTENT_COLOR: String = "#99CCF0"
        const val CREATED: String = "작성일"
    }
}
