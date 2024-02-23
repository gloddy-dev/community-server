package gloddy.inMessage.payload

import java.time.LocalDateTime

data class UserMessagePayload(
    val userId: Long,
    val eventType: UserMessagePayloadType,
    val eventDateTime: LocalDateTime
)