package stephanie.com.desafioKotlin.webService

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import stephanie.com.desafioKotlin.modelo.ItemPullRequest
import stephanie.com.desafioKotlin.modelo.RepositorioResponse

interface Endpoint {
    // criando um endpoint que irá utilizar o
    // verbo GET e irá retornar um objeto
    // retrofit do tipo Call que será nosso
    // container de uma lista de objetos do tipo Repositorio

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getRepo(): Response<RepositorioResponse>

    @GET("{criador}/{repositorio}/pulls")
    suspend fun getPulls(
        @Path("criador") criador: String,
        @Path("repositorio") repositorio: String
    ): Response<List<ItemPullRequest>>
}