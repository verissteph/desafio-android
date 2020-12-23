package stephanie.com.desafioKotlin.modelo

data class Owner(
    private val id: Int,
    private val login: String,
    private val avatar_url: String,
    private val stargazers_count: Int,
    private val forks_count:Int,


) {

}
