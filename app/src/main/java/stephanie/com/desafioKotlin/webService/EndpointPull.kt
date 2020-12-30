package stephanie.com.desafioKotlin.webService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import stephanie.com.desafioKotlin.modelo.ItemPullRequest

interface EndpointPull {
    @GET("{owner}/{repositorio}/pulls")
     fun getPulls(
        @Path("owner") owner: String,
        @Path("repositorio") repositorio: String
    ): Call<List<ItemPullRequest>>
}