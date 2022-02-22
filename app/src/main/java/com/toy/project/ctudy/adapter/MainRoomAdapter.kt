package com.toy.project.ctudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.MainRoomItemBinding
import com.toy.project.ctudy.interfaces.RoomClickListener
import com.toy.project.ctudy.model.response.RoomAllResponseList

class MainRoomAdapter(
    roomList: ArrayList<RoomAllResponseList>,
    roomClickListener: RoomClickListener,
) :
    RecyclerView.Adapter<MainRoomViewHolder>() {

    lateinit var mContext: Context
    private var mRoomList = roomList
    private var mRoomClickListener = roomClickListener

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
        holder.bind(mRoomList.get(position))

        holder.binding.mainStudyLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                mRoomClickListener.onRoomDetailClick(mRoomList.get(position).id!!)
            }
        })
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
//@BindingAdapter("imageUrl")
//fun setImage(imageView: AppCompatImageView, imageUrl: String) {
//    CommonFunction.setImageView(imageView.context, imageUrl, imageView)
//}

class MainRoomViewHolder(itemView: MainRoomItemBinding) : RecyclerView.ViewHolder(itemView.root) {
    val binding = itemView
    fun bind(list: RoomAllResponseList) {
        binding.item = list
    }
}