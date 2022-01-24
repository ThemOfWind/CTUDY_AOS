package com.toy.project.ctudy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Binding : ViewDataBinding>(
    parent: ViewGroup,
    layoutId: Int,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
) {
    val binding: Binding = DataBindingUtil.bind(itemView)!!
}