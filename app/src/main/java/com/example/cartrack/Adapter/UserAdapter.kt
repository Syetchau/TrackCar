package com.example.cartrack.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cartrack.Adapter.UserAdapter.MyViewHolder
import com.example.cartrack.Common.Common.userSelected
import com.example.cartrack.DisplayMapActivity
import com.example.cartrack.Model.User
import com.example.cartrack.R
import com.example.cartrack.Service.ItemClickListener
import java.util.*

class UserAdapter(private val context: Context, private val usersList: List<User?>?) : RecyclerView.Adapter<MyViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val address = Objects.requireNonNull(usersList?.get(position)?.address)?.street!!.trim { it <= ' ' } + ", " +
                Objects.requireNonNull(usersList?.get(position)?.address)?.suite!!.trim { it <= ' ' } + ", " +
                Objects.requireNonNull(usersList?.get(position)?.address)?.city!!.trim { it <= ' ' } + "," +
                Objects.requireNonNull(usersList?.get(position)?.address)?.zipcode!!.trim { it <= ' ' }

        holder.tvName.text = usersList?.get(position)!!.name
        holder.tvEmail.text = usersList[position]!!.email
        holder.tvPhone.text = usersList[position]!!.phone
        holder.tvCompanyName.text = Objects.requireNonNull(usersList[position]!!.company)?.name
        holder.tvAddress.text = address
        holder.tvWebsite.text = usersList[position]!!.website
        holder.tvCatchPhrase.text = usersList[position]!!.company!!.catchPhrase
        holder.tvBs.text = usersList[position]!!.company!!.bs
        holder.setItemClickListener(object : ItemClickListener {
            override fun onItemClickListener(view: View?, position: Int) {
                userSelected = usersList[position]
                val i = Intent(context, DisplayMapActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(i)
            }
        })
    }

    override fun getItemCount(): Int {
        return usersList!!.size
    }

    class MyViewHolder(itemView: View) : ViewHolder(itemView), View.OnClickListener {
        var tvName: TextView = itemView.findViewById(R.id.tv_username)
        var tvEmail: TextView = itemView.findViewById(R.id.tv_email)
        var tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        var tvCompanyName: TextView = itemView.findViewById(R.id.tv_company_name)
        var tvAddress: TextView = itemView.findViewById(R.id.tv_address)
        var tvWebsite: TextView = itemView.findViewById(R.id.tv_website)
        var tvCatchPhrase: TextView = itemView.findViewById(R.id.tv_catch_phrase)
        var tvBs: TextView = itemView.findViewById(R.id.tv_bs)

        private var itemClickListener: ItemClickListener? = null

        fun setItemClickListener(itemClickListener: ItemClickListener?) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(view: View) {
            itemClickListener!!.onItemClickListener(view, adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

}