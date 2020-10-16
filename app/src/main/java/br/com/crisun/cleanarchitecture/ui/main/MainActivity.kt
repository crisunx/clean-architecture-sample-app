package br.com.crisun.cleanarchitecture.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.crisun.architecture.domain.model.Message
import br.com.crisun.cleanarchitecture.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {
    private val fragment: MainFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }

        initializeListeners()
    }

    private fun initializeListeners() {
        reload_button.setOnClickListener {
            fragment.viewModel.requestMessage()
        }

        save_button.setOnClickListener {
            fragment.viewModel.saveMessage(Message(text = "foo", date = Date()))
        }

        load_button.setOnClickListener {
            fragment.viewModel.messageReport()
        }
    }
}
