package den.harbut.shelflife.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import den.harbut.shelflife.data.local.db.ShelfDatabase
import den.harbut.shelflife.data.local.db.dao.GroupDao
import den.harbut.shelflife.data.local.db.dao.ProductDao
import den.harbut.shelflife.data.local.db.dao.ScreenDao
import den.harbut.shelflife.data.local.db.dao.TimerDao
import den.harbut.shelflife.data.repository.*
import den.harbut.shelflife.domain.repository.*
import den.harbut.shelflife.domain.usecase.group.*
import den.harbut.shelflife.domain.usecase.product.*
import den.harbut.shelflife.domain.usecase.screen.*
import den.harbut.shelflife.domain.usecase.timer.*
import den.harbut.shelflife.presentation.viewmodel.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Room DB
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ShelfDatabase {
        return Room.databaseBuilder(
            app,
            ShelfDatabase::class.java,
            "shelf_db"
        ).fallbackToDestructiveMigration().build()
    }

    // DAO
    @Provides fun provideTimerDao(db: ShelfDatabase) = db.timerDao()
    @Provides fun provideProductDao(db: ShelfDatabase) = db.productDao()
    @Provides fun provideGroupDao(db: ShelfDatabase) = db.groupDao()
    @Provides fun provideScreenDao(db: ShelfDatabase) = db.screenDao()

    // Repositories
    @Provides
    @Singleton
    fun provideTimerRepository(dao: TimerDao): TimerRepository =
        TimerRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideProductRepository(dao: ProductDao): ProductRepository =
        ProductRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideGroupRepository(dao: GroupDao): GroupRepository =
        GroupRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideScreenRepository(dao: ScreenDao): ScreenRepository =
        ScreenRepositoryImpl(dao)

    // Timer use cases
    @Provides fun provideAddTimerUseCase(repo: TimerRepository) = AddTimerUseCase(repo)
    @Provides fun provideDeleteTimerUseCase(repo: TimerRepository) = DeleteTimerUseCase(repo)
    @Provides fun provideUpdateTimerUseCase(repo: TimerRepository) = UpdateTimerUseCase(repo)
    @Provides fun provideStartStopTimerUseCase(repo: TimerRepository) = StartStopTimerUseCase(repo)
    @Provides fun provideGetTimersByGroupUseCase(repo: TimerRepository) = GetTimersByGroupUseCase(repo)

    // Product use cases
    @Provides fun provideAddProductUseCase(repo: ProductRepository) = AddProductUseCase(repo)
    @Provides fun provideGetAllProductsUseCase(repo: ProductRepository) = GetAllProductsUseCase(repo)
    @Provides fun provideGetProductByIdUseCase(repo: ProductRepository) = GetProductByIdUseCase(repo)
    @Provides fun provideUpdateProductUseCase(repo: ProductRepository) = UpdateProductUseCase(repo)
    @Provides fun provideDeleteProductUseCase(repo: ProductRepository) = DeleteProductUseCase(repo)

    // Group use cases
    @Provides fun provideAddGroupUseCase(repo: GroupRepository) = AddGroupUseCase(repo)
    @Provides fun provideDeleteGroupUseCase(repo: GroupRepository) = DeleteGroupUseCase(repo)
    @Provides fun provideUpdateGroupUseCase(repo: GroupRepository) = UpdateGroupUseCase(repo)
    @Provides fun provideGetGroupsByScreenUseCase(repo: GroupRepository) = GetGroupsByPageUseCase(repo)

    // Screen use cases
    @Provides fun provideAddScreenUseCase(repo: ScreenRepository) = AddScreenUseCase(repo)
    @Provides fun provideDeleteScreenUseCase(repo: ScreenRepository) = DeleteScreenUseCase(repo)

    // ViewModels — можуть використовуватись у HiltViewModel-фабриках
    @Provides fun provideTimerViewModel(
        timerRepository: TimerRepository
    ) = TimerViewModel(timerRepository)

    @Provides fun provideProductViewModel(
        addProductUseCase: AddProductUseCase,
        updateProductUseCase: UpdateProductUseCase,
        deleteProductUseCase: DeleteProductUseCase,
        getAllProductsUseCase: GetAllProductsUseCase,
        getProductByIdUseCase: GetProductByIdUseCase
    ) = ProductViewModel(
        addProductUseCase,
        updateProductUseCase,
        deleteProductUseCase,
        getAllProductsUseCase,
        getProductByIdUseCase
    )

    @Provides fun provideGroupViewModel(
        getGroupsByScreenUseCase: GetGroupsByPageUseCase,
        addGroupUseCase: AddGroupUseCase,
        updateGroupUseCase: UpdateGroupUseCase,
        deleteGroupUseCase: DeleteGroupUseCase
    ) = GroupViewModel(
        getGroupsByScreenUseCase,
        addGroupUseCase,
        updateGroupUseCase,
        deleteGroupUseCase
    )

    @Provides fun provideScreenViewModel(
        screenRepository: ScreenRepository,
        addScreenUseCase: AddScreenUseCase,
        deleteScreenUseCase: DeleteScreenUseCase
    ) = ScreenViewModel(
        screenRepository,
        addScreenUseCase,
        deleteScreenUseCase
    )
}
