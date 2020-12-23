package stephanie.com.desafioKotlin.modelo


data class ItemRepositorio(var nome_repositorio:String,
                           var descricao_repositorio:String,
                           val fork_repositorio:Int,
                           var qde_forks_repositorio:String,
                           val stars_repositorio:Int,
                           var qde_stars_repositorios:String,
                           val foto_repositorio:Int,
                           var username_repositorio:String,
                           var fullname_repositorio:String,)

    //As visualizações na lista são representadas por objetos fixadores de visualização,
    // que sao instancias desta classe
    //O adapter vai gerenciar cada instancia dessa clsse

