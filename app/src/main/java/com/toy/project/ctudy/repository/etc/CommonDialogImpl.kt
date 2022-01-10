package com.toy.project.ctudy.repository.etc

import android.content.Context
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.view.CommonDialog

interface CommonDialogManager {
    fun showDialog(type: AlertDialogBtnType, content: String): CommonDialog
}

class CommonDialogImpl(val context: Context) : CommonDialogManager {
    override fun showDialog(
        type: AlertDialogBtnType,
        content: String
    ): CommonDialog {
        return CommonDialog(context = context).apply {
            if (type == AlertDialogBtnType.ONE) {
                setOneButtonType()
            }

            setContentMsg(content)
            show()
        }
    }
}