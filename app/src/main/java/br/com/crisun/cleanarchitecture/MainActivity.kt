package br.com.crisun.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.crisun.cleanarchitecture.ui.main.MainFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val fragment by inject<MainFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }

        reload_button.setOnClickListener {
            fragment.viewModel.process()
        }
    }
}
