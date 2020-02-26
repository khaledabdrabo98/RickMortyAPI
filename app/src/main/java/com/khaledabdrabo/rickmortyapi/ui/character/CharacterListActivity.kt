package com.khaledabdrabo.rickmortyapi.ui.character

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.khaledabdrabo.rickmortyapi.R
import com.khaledabdrabo.rickmortyapi.databinding.ActivityMainBinding
import com.khaledabdrabo.rickmortyapi.injection.ViewModelFactory

class CharacterListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CharacterListViewModel
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.charactersList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(CharacterListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.refresh_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.btn_refresh_list) {
            viewModel.forceLoadCharacters(viewModel.generateRandomCharacters())
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    private fun showError(@StringRes errorMessage:Int){
        snackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        snackbar?.setAction(R.string.refresh, viewModel.errorClickListener)
        snackbar?.show()
    }

    private fun hideError(){
        snackbar?.dismiss()
    }
}