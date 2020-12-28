package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

data class RepositorioResponse(
    val items: List<Repositorio>
)

data class Repositorio(
    //@SerializedName é usado para facilitar
    // a conversao e serializaçao


    internal val id: Int,


    internal val name: String,


    internal val description: String,


    internal val username: String?,

    internal val full_name: String,

    internal val forks_count: Int,


    internal val owner: Owner,

    internal val stargazers_count: Int,


    ) {}
interface Endpoint {
    // criando um endpoint que irá utilizar o
    // verbo GET e irá retornar um objeto
    // retrofit do tipo Call que será nosso
    // container de uma lista de objetos do tipo Repositorio

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    suspend fun getRepo(): Response<RepositorioResponse>
}