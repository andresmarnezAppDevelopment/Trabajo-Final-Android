package net.iessochoa.grupof.practicafinalandroid.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.iessochoa.grupof.practicafinalandroid.databinding.FragmentSongSearchBinding
import net.iessochoa.grupof.practicafinalandroid.network.SongAPIService
import net.iessochoa.grupof.practicafinalandroid.network.SongHeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class SongSearchFragment: Fragment() {
    private lateinit var binding: FragmentSongSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = FragmentSongSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabTester.setOnClickListener(){
            searchSongByName("Wake me Up before")
        }
    }

    //Montamos un objeto Retrofit configurado para poder realizar la llamada a la API y recoger los datos que queramos
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    //Con esta función, introduciremos los parámetros y la autentificación requeridas para la realización de la petición GET
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .addInterceptor(SongHeaderInterceptor())
            .build()
    }

    private fun searchSongByName(songName: String){
        CoroutineScope(Dispatchers.IO).launch {
            val llamadaApi = getRetrofit().create(SongAPIService::class.java).getSongsByName(songName, "track", "ES")
            val songsObtained = llamadaApi.body()

            //Con RunOnUiThread, nos aseguramos que el código que se encuentre en su interior se ejecute en el hilo principal de la aplicación, pese a que
            //se encuentre dentro de una corrutina
            getActivity()?.runOnUiThread{
                if (songsObtained != null) {
                    binding.textView2.text = songsObtained.tracks.name
                }

                if(llamadaApi.isSuccessful){
                    Log.w("cojonesOstiaPuta", "vivaviva")
                }
                else{
                    Log.w("cojonesOstiaPuta", "a morirse todos")
                }
            }
        }
    }
}