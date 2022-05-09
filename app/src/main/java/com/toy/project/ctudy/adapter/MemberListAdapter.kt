package com.toy.project.ctudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.MemberListItemBinding
import com.toy.project.ctudy.model.response.MemberList

class MemberListAdapter(
    val memberList: ArrayList<MemberList>,
) :
    RecyclerView.Adapter<MemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val memberBinding: MemberListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.member_list_item,
                parent,
                false)
        return MemberViewHolder(memberBinding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(memberList.get(position))
    }

    override fun getItemCount(): Int {
        return memberList.size
    }
}

class MemberViewHolder(itemView: MemberListItemBinding) : RecyclerView.ViewHolder(itemView.root) {
    val binding = itemView
    fun bind(memberList: MemberList) {
        binding.items = memberList
    }
}