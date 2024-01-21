package gloddy.category.port.service

import gloddy.category.port.`in`.dto.CategoryGetResponse
import gloddy.category.port.`in`.CategoryQueryUseCase
import gloddy.category.port.out.CategoryQueryPersistencePort
import org.springframework.stereotype.Service

@Service
class CategoryQueryService(
    private val categoryQueryPersistencePort: CategoryQueryPersistencePort,
) : CategoryQueryUseCase {

    override fun getAll(): List<CategoryGetResponse> {
        val categories = categoryQueryPersistencePort.findAll()
        return categories.map {
            CategoryGetResponse(
                id = it.id!!.value,
                name = it.name
            )
        }
    }
}