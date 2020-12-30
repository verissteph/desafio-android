package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName


data class Repositorio(
     val id: Int,
     @SerializedName("name")
     val nome_repo: String,
     @SerializedName("description")
     val descricao_repo: String,
     @SerializedName("username")
     val username_repo: String?,
     @SerializedName("full_name")
     val full_name_repo: String,
     @SerializedName("forks_count")
     val forks_count_repo: String,
     @SerializedName("owner")
     val owner: Owner,
     @SerializedName("stargazers_count")
     val stargazers_count_repo: String,


    )
