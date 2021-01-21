package stephanie.com.desafioKotlin.modelo

import com.google.gson.annotations.SerializedName

data class Owner(
     val login: String,
     // Usar o padrão cammelCase e usar @SerializableName para quando o nome for diferente
     @SerializedName("avatar_url")
     val avatarUrl: String,


     ) {

}
