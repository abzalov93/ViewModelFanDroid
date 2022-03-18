package com.afbullet.jetpackviewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afbullet.jetpackviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    //инициализируем ViewModel ленивым способом
    private val userViewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //инициализируем адаптер и присваиваем его списку
        val adapter = UserAdapter()
        binding.userList.layoutManager = LinearLayoutManager(this)
        binding.userList.adapter = adapter

        //подписываем адаптер на изменения списка
        userViewModel.getListUsers().observe(this, Observer {
            it?.let {
                adapter.refreshUsers(it)
            }
        })
    }

    //создаем меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //при нажатии пункта меню Refresh обновляем список
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.refresh -> {
                userViewModel.updateListUsers()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
