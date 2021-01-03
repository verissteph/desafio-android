package stephanie.com.desafioKotlin.webService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InicializadorAPIRepo {
    fun start(): EndpointRepo{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EndpointRepo::class.java)
    }

}