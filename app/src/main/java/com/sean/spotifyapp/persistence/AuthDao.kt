package com.sean.spotifyapp.persistence

import androidx.room.*
import com.sean.spotifyapp.repository.AuthRepository.Companion.TOKEN_PRIMARY_KEY
import com.sean.spotifyapp.models.AuthToken
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Dao
interface AuthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: AuthToken): Long

    @Query("SELECT * FROM auth_token WHERE account_pk = :pk")
    suspend fun getToken(pk: Long = TOKEN_PRIMARY_KEY): AuthToken?

    @Query("DELETE FROM auth_token WHERE account_pk = 0")
    suspend fun logOut()


}