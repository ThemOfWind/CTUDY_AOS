package com.toy.project.ctudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.MainRoomItemBinding
import com.toy.project.ctudy.extension.CommonFunction
import com.toy.project.ctudy.model.response.RoomAllResponseList

class MainRoomAdapter(roomList: ArrayList<RoomAllResponseList>) :
    RecyclerView.Adapter<MainRoomViewHolder>() {

    lateinit var mContext: Context
    private var mRoomList = roomList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRoomViewHolder {
        mContext = parent.context
        val mainRoomBinding: MainRoomItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.main_room_item,
                parent,
                false)
        return MainRoomViewHolder(mainRoomBinding)
    }

    override fun onBindViewHolder(holder: MainRoomViewHolder, position: Int) {
        // TODO 현재 에러남ㅠㅠ 고쳐어어어어어엇!
        onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        return mRoomList.size
    }

    fun setData(roomList: ArrayList<RoomAllResponseList>) {
        mRoomList = roomList
        notifyDataSetChanged()
    }

}

/**
 * 함수 바인딩으로 이미지 세팅
 * app:imageUrl = "@{데이터.이미지컬럼}"
 */
@BindingAdapter("imageUrl")
fun setImage(imageView: AppCompatImageView, imageUrl: String) {
    CommonFunction.setImageView(imageView.context, imageUrl, imageView)
}

class MainRoomViewHolder(itemView: MainRoomItemBinding) : RecyclerView.ViewHolder(itemView.root) {
}