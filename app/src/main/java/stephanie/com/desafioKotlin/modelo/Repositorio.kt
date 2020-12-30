package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET



data class Repositorio(
    //@SerializedName é usado para facilitar
    // a conversao e serializaçao


    private val id: Int,


    private val name: String,


    private val description: String,


    private val username: String?,

    private val full_name: String,

    private val forks_count: Int,


    private val owner: Owner,

    private val stargazers_count: Int,


    ) {}
