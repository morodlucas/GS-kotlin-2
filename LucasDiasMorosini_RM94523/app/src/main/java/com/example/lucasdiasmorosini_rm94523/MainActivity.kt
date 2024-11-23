package com.example.lucasdiasmorosini_rm94523

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.lucasdiasmorosini_rm94523.viewmodel.ItemsAdapter
import com.example.lucasdiasmorosini_rm94523.viewmodel.ItemsViewModel
import com.example.lucasdiasmorosini_rm94523.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        recyclerView.adapter = itemsAdapter

        val buttonIntegrantes = findViewById<Button>(R.id.buttonIntegrantes)
        val button = findViewById<Button>(R.id.button)
        val titulo = findViewById<EditText>(R.id.titulo)
        val desc = findViewById<EditText>(R.id.desc)

        button.setOnClickListener {
            if (titulo.text.isEmpty()) {
                titulo.error = "Preencha o campo"
                return@setOnClickListener
            }
            if (desc.text.isEmpty()) {
                desc.error = "Preencha o campo"
                return@setOnClickListener
            }

            //viewModel.addItem(titulo.text.toString(), desc.text.toString())
            viewModel.addItem(titulo.text.toString())
            titulo.text.clear()
            viewModel.addItem(desc.text.toString())
            desc.text.clear()
        }

        buttonIntegrantes.setOnClickListener {
            setContentView(R.layout.integrantes)
        }

        val viewModelFactory = ItemsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}