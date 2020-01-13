package br.com.crisun.cleanarchitecture.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        viewModel.process()

        viewModel.errorLiveData.observe(this, Observer { msg ->
            error_view.text = msg?.toString()
        })

        viewModel.messageLiveData.observe(this, Observer { msg ->
            message_view.text = msg?.toString()
        })

        viewModel.statisticLiveData.observe(this, Observer { msgs ->
            history_view.text = msgs?.toString()
        })
    }
}
