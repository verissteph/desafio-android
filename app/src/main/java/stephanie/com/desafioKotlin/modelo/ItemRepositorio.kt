package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName


data class ItemRepositorio(
    @SerializedName("items")
    val items: List<Repositorio>
)

