package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class Repositorio(
    //@SerializedName é usado para facilitar
    // a conversao e serializaçao

    @SerializedName("id")
    private val id: Int,

    @SerializedName("name")
    private val name: String,

    @SerializedName("description")
    private val description: String,

    @SerializedName("username")
    private val username: String,

    @SerializedName("full_name")
    private val full_name: String,

    @SerializedName("forks_count")
    private val forks_count: Int,

    @SerializedName("owner")
    private val owner: Owner,

    @SerializedName("stargazers_count")
    private val stargazers_count: Int,


    ) {}
interface Endpoint {
    // criando um endpoint que irá utilizar o
    // verbo GET e irá retornar um objeto
    // retrofit do tipo Call que será nosso
    // container de uma lista de objetos do tipo Repositorio

    @GET("search/repositories?q=language:Java&sort=stars&page=1")
    fun getRepo(): Call<List<Repositorio>>
}