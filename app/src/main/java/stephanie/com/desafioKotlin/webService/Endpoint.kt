package stephanie.com.desafioKotlin.webService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import stephanie.com.desafioKotlin.modelo.ItemRepositorio
import stephanie.com.desafioKotlin.modelo.PullRequest

interface Endpoint {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getRepo(@Query("page") page: Int): Call<ItemRepositorio>

    @GET("repos/{owner}/{repositorio}/pulls")

    fun getPulls(
        @Path("owner") owner: String,
        @Path("repositorio") repositorio: String
    ): Call<List<PullRequest>>
}