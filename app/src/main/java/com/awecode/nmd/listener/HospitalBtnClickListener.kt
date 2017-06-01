package com.awecode.nmd.listener

import com.awecode.nmd.models.Hospital

/**
 * Created by munnadroid on 6/1/17.
 */
interface HospitalBtnClickListener{
    fun onBtnClickListener(buttonType: ButtonType,hospital: Hospital)
}

enum class ButtonType{
    CALL,
    DIRECTION,
    EMAIL,
    ITEM_VIEW
}