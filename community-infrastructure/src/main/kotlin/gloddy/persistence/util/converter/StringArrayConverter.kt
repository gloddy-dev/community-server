package gloddy.persistence.util.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringArrayConverter : AttributeConverter<List<String>, String> {

    companion object {
        private const val DELIMITER = ","
    }

    override fun convertToDatabaseColumn(attribute: List<String>?): String? {
        if (attribute.isNullOrEmpty()) {
            return null
        }
        return attribute.joinToString(DELIMITER)
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        if (dbData.isNullOrEmpty()) {
            return emptyList()
        }
        return dbData.split(DELIMITER)
            .filter { it.isNotEmpty() }
            .toList()
    }
}