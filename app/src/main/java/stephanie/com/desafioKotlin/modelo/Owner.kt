package stephanie.com.desafioKotlin.modelo

data class Owner(
     val login: String,
     // Usar o padrão cammelCase e usar @SerializableName para quando o nome for diferente
     val avatarUrl: String,


     ) {

}
