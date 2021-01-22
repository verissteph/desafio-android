package stephanie.com.desafioKotlin.webService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import stephanie.com.desafioKotlin.modelo.ItemRepositorio
import stephanie.com.desafioKotlin.modelo.PullRequest

// Ratorar estas interfaces para representarem um serviço, a API do Github e remover
// interfaces diferentes para cada endpoint
interface Endpoint {
    @GET("repos/{owner}/{repositorio}/pulls")
    fun getPulls(
        @Path("owner") owner: String,
        @Path("repositorio") repositorio: String
    ): Call<List<PullRequest>>

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepo(@Query("page") page: Int): Call<ItemRepositorio>

}