package com.arifudesu.kadeproject2.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.util.Log
import com.arifudesu.kadeproject2.R
import android.support.constraint.Constraints.TAG
import com.arifudesu.kadeproject2.activity.MainActivity
import com.arifudesu.kadeproject2.util.AppPreferences

/**
 * > with <3 by SyakirArif
 * say no to plagiarism
 */
class MyPreferenceFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener,
    Preference.OnPreferenceClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        val prefs = AppPreferences(activity)


        val leaguePref = findPreference(resources.getString(R.string.pref_league)) as ListPreference

        when (prefs.getLeagueFavorite()) {
            "4328" -> leaguePref.setValueIndex(0)
            "4331" -> leaguePref.setValueIndex(1)
            "4332" -> leaguePref.setValueIndex(2)
            "4334" -> leaguePref.setValueIndex(3)
            "4335" -> leaguePref.setValueIndex(4)
            "4344" -> leaguePref.setValueIndex(5)
            "4480" -> leaguePref.setValueIndex(5)
        }

        leaguePref.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            prefs.setLeagueFavorite(newValue as String)
            Log.d(TAG, "onPreferenceChange: " + prefs.getLeagueFavorite())

            val leagueId = resources!!.getStringArray(R.array.arr_league_id)

            for (i in leagueId.indices) {
                if (leagueId[i] == prefs.getLeagueFavorite()) {
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    activity.finish()
                    startActivity(intent)
                    break
                }
            }

            false
        }

    }

    override fun onPreferenceChange(preference: Preference, `object`: Any): Boolean {
//        val key = preference.key
//
//        val isOn = `object` as Boolean
//
//        //////////// setting untuk daily alarm ////////////////////
//
//        if (key == reminder_daily) {
//            if (isOn) {
//                alarmReceiver.setDailyAlarm(
//                    activity,
//                    AlarmReceiver.TYPE_DAILY,
//                    getString(R.string.message_alarm_daily_reminder)
//                )
//                Toast.makeText(activity, getString(R.string.label_activated), Toast.LENGTH_SHORT).show()
//            } else {
//                alarmReceiver.cancelAlarm(activity, AlarmReceiver.TYPE_DAILY)
//                Toast.makeText(activity, getString(R.string.label_deactivated), Toast.LENGTH_SHORT).show()
//            }
//
//            return true
//        }
//
//        //////////// setting untuk alarm release movie ////////////////////
//
//        if (key == reminder_playing) {
//            if (isOn) {
//                setReleaseAlarm()
//                Toast.makeText(activity, getString(R.string.label_activated_release), Toast.LENGTH_SHORT).show()
//            } else {
//                alarmReceiver.cancelAlarm(activity, AlarmReceiver.TYPE_RELEASE)
//                Toast.makeText(activity, getString(R.string.label_deactivated), Toast.LENGTH_SHORT).show()
//            }
//
//            return true
//        }

        return false
    }

    override fun onPreferenceClick(preference: Preference): Boolean {
        return false
    }

    /*private fun setReleaseAlarm() {

        val movieItemses = ArrayList<MovieItems>()

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date_now = Date()
        val dateNow = dateFormat.format(date_now)

        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" +
                BuildConfig.API_KEY + "&language=en-US&region=" + prefs!!.getRegion()
        Log.d(TAG, "URL: $url")
        client.get(url, object : AsyncHttpResponseHandler() {
            fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val arrayResult = jsonObject.getJSONArray("results")

                    for (i in 0 until arrayResult.length()) {

                        val movie = arrayResult.getJSONObject(i)

                        val movieItems = MovieItems(movie)
                        Log.d(TAG, "getReleaseDate: " + movieItems.getRelease_date())
                        Log.d(TAG, "dateNow: $dateNow")

                        ////////////// seleksi movie yang tayang di hari ini ///////////
                        if (movieItems.getRelease_date().equals(dateNow)) {
                            movieItemses.add(movieItems)
                        }

                        Log.d("movieReleaseToday:", " " + movieItemses.size)

                    }

                    alarmReceiver.setReleaseAlarm(activity, movieItemses, AlarmReceiver.TYPE_RELEASE)

                } catch (e: Exception) {

                    e.printStackTrace()
                }

            }

            fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Toast.makeText(activity, "Something went wrong while getting today release movie", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }*/
}

