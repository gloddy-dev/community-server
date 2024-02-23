package gloddy.inMessage.payload.handler

import gloddy.inMessage.payload.UserMessagePayload
import gloddy.internal.client.UserQueryClient
import gloddy.user.port.`in`.dto.UserSaveRequest
import gloddy.user.service.UserCommandService
import org.springframework.stereotype.Component

@Component
class UserMessagePayloadHandler(
    private val userQueryClient: UserQueryClient,
    private val userCommandService: UserCommandService
) {

    fun handle(payload: UserMessagePayload) {
        val userPreviewPayload = userQueryClient.getUserPreview(payload.userId)
        userCommandService.save(
            UserSaveRequest(
                id = userPreviewPayload.id,
                isCertifiedStudent = userPreviewPayload.isCertifiedStudent,
                profileImage = userPreviewPayload.profileImage,
                nickName = userPreviewPayload.nickName,
                countryName = userPreviewPayload.countryName,
                countryImage = userPreviewPayload.countryImage,
                reliabilityLevel = userPreviewPayload.reliabilityLevel
            )
        )
    }
}