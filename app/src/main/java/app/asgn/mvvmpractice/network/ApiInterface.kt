package app.asgn.mvvmpractice.network

import app.asgn.mvvmpractice.models.PullsDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("pulls")
    suspend fun getPullsInfo(@Query("state") state : String) : Response<PullsDataClass>
}