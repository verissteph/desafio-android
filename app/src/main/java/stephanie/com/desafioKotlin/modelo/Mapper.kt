package stephanie.com.desafioKotlin.modelo

fun List<Repositorio>.transfomeEmListaItemRepositorio(): List<ItemRepositorio>{
  val resultado =  this.map { repositorio ->
        ItemRepositorio(
            nome_repositorio = repositorio.name,
            descricao_repositorio = repositorio.description,
            qde_forks_repositorio = (repositorio.forks_count).toString(),
            qde_stars_repositorios = (repositorio.stargazers_count).toString(),
            username_repositorio = repositorio.username ?: String(),
            fullname_repositorio = repositorio.full_name,
            owner_avatar_url = repositorio.owner.avatar_url,
            owner_id = repositorio.owner.id,
            owner_login = repositorio.owner.login,
        )
    }
    return resultado
}