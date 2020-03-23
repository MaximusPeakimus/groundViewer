package com.example.groundviewer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_home_screen.*
import okhttp3.*
import java.io.IOException


class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        teams_recycler_view.layoutManager = LinearLayoutManager(this)

        fetchJson()

    }

    private fun fetchJson() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                println(body)
                Log.d("TESTOUT", body)

                val gson = GsonBuilder().create()

                val teamList = gson.fromJson(body, TeamList::class.java)

                runOnUiThread {
                    teams_recycler_view.adapter = TeamsAdapter(teamList)
                }
            }
        })
    }

}

class TeamList(val teams: List<Team>)

class Team(val strTeam:String, val intFormedYear:String, val strTeamBadge:String, val strStadium:String)
