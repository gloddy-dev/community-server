package gloddy.inMessage.sqs

import gloddy.inMessage.payload.handler.UserMessagePayloadHandler
import gloddy.inMessage.sqs.util.MessageParser
import gloddy.user.service.UserCommandService
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class SqsSubscriber(
    private val userMessagePayloadHandler: UserMessagePayloadHandler
) {

    @SqsListener(value = ["\${sqs.queue.user}"])
    fun handleApplyEvent(message: String) {
        val payload = MessageParser.parseUserMessage(message)
        userMessagePayloadHandler.handle(payload)
    }
}