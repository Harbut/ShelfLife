package den.harbut.shelflife.domain.repository

import den.harbut.shelflife.domain.model.Product
import den.harbut.shelflife.domain.model.Screen
import kotlinx.coroutines.flow.Flow

interface ScreenRepository {
    fun getAllScreens(): Flow<List<Screen>>
    suspend fun getScreenById(id: Long): Screen?
    suspend fun addScreen(screen: Screen)
    suspend fun updateScreen(screen: Screen)
    suspend fun deleteScreen(screenId: Long)
}
