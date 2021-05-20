package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName

data class Owner(
    val login: String,

    @SerializedName("avatar_url")

    // Usar o padrão cammelCase e usar @SerializableName para quando o nome for diferente
    val avatarUrl: String,


    ) {

}
