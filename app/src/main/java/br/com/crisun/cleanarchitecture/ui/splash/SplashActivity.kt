package br.com.crisun.cleanarchitecture.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import br.com.crisun.cleanarchitecture.R
import br.com.crisun.cleanarchitecture.ui.main.MainActivity
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({ start() }, TimeUnit.SECONDS.toMillis(2))
    }

    private fun start() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        super.onKeyDown(keyCode, event)
        return keyCode != KeyEvent.KEYCODE_BACK
    }
}
