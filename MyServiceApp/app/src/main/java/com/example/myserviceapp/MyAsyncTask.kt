package com.example.myserviceapp

import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

class MyAsyncTask(
    private val progressBar: ProgressBar,
    private val textView: TextView
) : AsyncTask<Void, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg params: Void?): String {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return "Результат загрузки"
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        progressBar.visibility = View.GONE
        textView.text = result
    }
}
