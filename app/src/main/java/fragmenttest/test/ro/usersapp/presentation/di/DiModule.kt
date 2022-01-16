package fragmenttest.test.ro.usersapp.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fragmenttest.test.ro.usersapp.domain.UsersRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideUsersRepository(): UsersRepository {
        return UsersRepository()
    }

}