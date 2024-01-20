package gloddy.internal.client

import gloddy.internal.client.payload.UserPreviewPayload
import gloddy.internal.client.payload.UserPreviewsPayload
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "UserGetClient", url = "\${internal.api.base-url}")
interface UserQueryClient {

    @GetMapping("/users/{userId}")
    fun getUserPreview(
        @PathVariable("userId") userId: Long,
    ): UserPreviewPayload

    @GetMapping("/users")
    fun getUserPreviews(
        @RequestParam("ids") ids:Set<Long>
    ): UserPreviewsPayload
}