package stephanie.com.desafioKotlin.modelo


data class ItemRepositorio(
    var nome_repositorio: String,
    var descricao_repositorio: String,
    var qde_forks_repositorio: String,
    var qde_stars_repositorios: String,
    var username_repositorio: String,
    var fullname_repositorio: String,
    var owner_id: Int,
    var owner_login: String,
    var owner_avatar_url: String,
)

