package app.asgn.mvvmpractice.repository

import android.util.Log
import app.asgn.mvvmpractice.models.PullsDataClass
import app.asgn.mvvmpractice.network.ApiInterface
import app.asgn.mvvmpractice.network.RetrofitClient

class MainRepository {
    val retrofit = RetrofitClient.getInstance()
    val apiInterface = retrofit.create(ApiInterface::class.java)

    var pullsInfoList : PullsDataClass? = null
    suspend fun fetchPullList(state:String) {
        try{
            val response = apiInterface.getPullsInfo(state)
            if(response.isSuccessful){
                pullsInfoList = response.body()
            }
        }
        catch (e:Exception) {
            e.localizedMessage?.let { Log.e("apiException", it) }
        }

    }

}