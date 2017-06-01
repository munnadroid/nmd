package com.awecode.nmd.util.extensions

/**
 * Created by munnadroid on 5/29/17.
 */
import android.app.Fragment
import android.content.Context
import android.widget.Toast
import android.support.v4.app.Fragment as SupportFragment

/**
 * Created by Yoav.
 */

 fun Context.toast(messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
}

 fun Context.longToast(messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
}

 fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

 fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

 fun Fragment.toast(messageResId: Int) {
    Toast.makeText(activity, messageResId, Toast.LENGTH_SHORT).show()
}

 fun Fragment.longToast(messageResId: Int) {
    Toast.makeText(activity, messageResId, Toast.LENGTH_LONG).show()
}

 fun Fragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

 fun Fragment.longToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

 fun SupportFragment.toast(messageResId: Int) {
    Toast.makeText(activity, messageResId, Toast.LENGTH_SHORT).show()
}

 fun SupportFragment.longToast(messageResId: Int) {
    Toast.makeText(activity, messageResId, Toast.LENGTH_LONG).show()
}

 fun SupportFragment.toast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

 fun SupportFragment.longToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
}

