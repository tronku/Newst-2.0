package tronku.projects.newst.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tronku.projects.newst.R
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread {
            try {
               Thread.sleep(1500)
            } catch (e: Exception) {

            } finally {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.start()
    }
}
