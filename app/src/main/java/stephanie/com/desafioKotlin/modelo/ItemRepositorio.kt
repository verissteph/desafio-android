package stephanie.com.desafioKotlin.modelo


data class ItemRepositorio(val nome_repositorio:String,
                           val descricao_repositorio:String,
                           val fork_repositorio:Int,
                           val qde_forks_repositorio:String,
                           val stars_repositorio:Int,
                           val qde_stars_repositorios:String,
                           val foto_repositorio:Int,
                           val username_repositorio:String,
                           val fullname_repositorio:String)

    //As visualizações na lista são representadas por objetos fixadores de visualização,
    // que sao instancias desta classe
    //O adapter vai gerenciar cada instancia dessa clsse

