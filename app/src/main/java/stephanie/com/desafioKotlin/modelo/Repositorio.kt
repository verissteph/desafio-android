package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName


data class Repositorio(
    val id: Int,
    @SerializedName("name")
     val nomeRepo: String,
    @SerializedName("description")
     val descricaoRepo: String,
    @SerializedName("full_name")
     val fullNameRepo: String,
    @SerializedName("forks_count")
     val forksCountRepo: String,
    @SerializedName("owner")
     val owner: Owner,
    @SerializedName("stargazers_count")
     val stargazersCountRepo: String,


    )
