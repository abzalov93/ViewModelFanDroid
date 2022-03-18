package com.afbullet.jetpackviewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afbullet.jetpackviewmodel.databinding.UserItemBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var users: List<User> = ArrayList()


    //создает ViewHolder и инициализирует views для списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    //связывает views с содержимым
    override fun onBindViewHolder(viewHolder: UserHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    override fun getItemCount() = users.size

    //передаем данные и оповещаем адаптер о необходимости обновления списка
    fun refreshUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }


    //внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
    class UserHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(itemView) {
            binding.userName.text = user.name
            binding.userDescription.text = user.description
        }
    }
}