package gloddy.category.port.service

import gloddy.category.port.dto.CategoryReadData
import gloddy.category.port.`in`.CategoryQueryUseCase
import gloddy.category.port.out.CategoryQueryPersistencePort
import org.springframework.stereotype.Service

@Service
class CategoryQueryService(
    private val categoryQueryPersistencePort: CategoryQueryPersistencePort,
) : CategoryQueryUseCase {

    override fun getAll(): List<CategoryReadData> {
        val categories = categoryQueryPersistencePort.findAll()
        return categories.map {
            CategoryReadData(
                id = it.id!!.value,
                name = it.name
            )
        }
    }
}