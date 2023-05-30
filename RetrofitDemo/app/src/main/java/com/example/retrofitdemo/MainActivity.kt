package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService: AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        //getRequestWithPathParameters()
        getRequestWithQueryParameters()

    }

    private fun getRequestWithQueryParameters() {

        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + " ALBUM TITLE : ${albumsItem.title}" + "\n" +
                            " " + " ALBUM ID : ${albumsItem.id}" + "\n" +
                            " " + " USER ID : ${albumsItem.userId}" + "\n\n\n\n"
                    val txt = findViewById<TextView>(R.id.tv_data)
                    txt.append(result)
                }
            }

        })
    }

    private fun getRequestWithPathParameters() {
        //path parameter
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }
}
