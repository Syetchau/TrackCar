package com.example.cartrack

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cartrack.Adapter.UserAdapter
import com.example.cartrack.Model.User
import com.example.cartrack.Service.GetData
import com.example.cartrack.Service.RetrofitClient.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var userAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initRetrofit()
    }

    private fun initRetrofit(){
        val service = retrofitInstance!!.create(GetData::class.java)
        val call = service.allUserList
        call!!.enqueue(object : Callback<List<User?>?> {
            override fun onResponse(call: Call<List<User?>?>, response: Response<List<User?>?>) {
                loadDataList(response.body())
            }

            override fun onFailure(call: Call<List<User?>?>, throwable: Throwable) {
                Log.d("throwable", throwable.toString())
                Toast.makeText(this@HomePageActivity, "Unable to load users", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadDataList(body: List<User?>?) {
        recyclerView = findViewById(R.id.rv_list)
        userAdapter = UserAdapter(baseContext, body)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@HomePageActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = userAdapter
    }
}