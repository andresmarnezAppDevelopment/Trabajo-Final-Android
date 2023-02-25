package net.iessochoa.grupof.practicafinalandroid.network

import net.iessochoa.grupof.practicafinalandroid.model.SongResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongAPIService {
    @GET("https://api.spotify.com/v1/search")
    suspend fun getSongsByName(@Query("q") songName: String, @Query("type") searchType: String, @Query("market") countryMarket: String): Response<SongResponse>
}