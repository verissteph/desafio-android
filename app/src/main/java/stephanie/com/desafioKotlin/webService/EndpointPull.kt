package stephanie.com.desafioKotlin.webService

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import stephanie.com.desafioKotlin.modelo.ItemPullRequest

interface EndpointPull {
    @GET("{criador}/{repositorio}/pulls")
    suspend fun getPulls(
        @Path("criador") criador: String,
        @Path("repositorio") repositorio: String
    ): Response<List<ItemPullRequest>>
}