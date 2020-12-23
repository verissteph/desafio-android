package stephanie.com.desafioKotlin.webService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkingUtils {
    //inicia o retrofit pra aplicaçao
    companion object{
        fun getRetrofitInstance(path:String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}