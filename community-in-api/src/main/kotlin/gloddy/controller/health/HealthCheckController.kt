package gloddy.controller.health

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @Operation(hidden = true)
    @GetMapping("/health")
    fun healthCheck(): String {
        return "healthy"
    }
}