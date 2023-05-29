package powerfive.util

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import powerfive.dto.NoticeRequest

@Aspect
@Component
class SlackAspect(
        private val slackService: SlackService
) {

    @AfterReturning(value = "execution(* powerfive.controller.NoticeController.create(..))", returning = "result")
    fun sendMessageAfterReturning(joinPoint: JoinPoint, result: Any) {
        val request: NoticeRequest = joinPoint.args[0] as NoticeRequest
        slackService.sendMessageToSlack(request)
    }
}
