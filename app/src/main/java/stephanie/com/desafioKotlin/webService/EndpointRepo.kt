package stephanie.com.desafioKotlin.webService

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import stephanie.com.desafioKotlin.modelo.ItemRepositorio

interface EndpointRepo {

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepo(): Call<ItemRepositorio>


}