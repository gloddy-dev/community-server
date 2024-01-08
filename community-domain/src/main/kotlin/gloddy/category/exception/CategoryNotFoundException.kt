package gloddy.category.exception

import gloddy.category.CategoryErrorCode
import gloddy.core.GloddyCommunityException

class CategoryNotFoundException : GloddyCommunityException(CategoryErrorCode.NOT_FOUND)