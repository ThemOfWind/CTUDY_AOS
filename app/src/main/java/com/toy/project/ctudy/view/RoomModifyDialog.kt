package com.toy.project.ctudy.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatDialog
import com.toy.project.ctudy.R

class RoomModifyDialog(context: Context) : AppCompatDialog(context) {
    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.room_modify_dialog)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (isShowing) {
            dismiss()
        }
    }
}