package br.com.crisun.cleanarchitecture.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import br.com.crisun.cleanarchitecture.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.getError().observe(viewLifecycleOwner) {
            error_view.text = "${error_view.text}\n$it"
        }

        viewModel.getMessage().observe(viewLifecycleOwner) {
            message_view.text = "${message_view.text}\n$it"

            viewModel.saveMessage(it)
        }

        viewModel.getMessagesByHour().observe(viewLifecycleOwner) {
            history_view.text = it.toString()
        }
    }
}
