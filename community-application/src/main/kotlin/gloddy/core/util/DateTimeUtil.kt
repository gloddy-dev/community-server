package gloddy.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.*

fun LocalDateTime.toResponse(): String =
    this.format(ofPattern("yyyy-MM-dd HH:mm"))
        .replace(" ", "T")