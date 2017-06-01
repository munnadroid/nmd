package com.awecode.nmd.view.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awecode.nmd.R
import com.awecode.nmd.util.extensions.inflate

/**
 * Created by munnadroid on 5/23/17.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(layoutId)
    }

    abstract val layoutId: Int

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        if (visible && isResumed) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!userVisibleHint) {
            return
        }

        fragmentVisible()
        //INSERT CUSTOM CODE HERE
    }

    open fun fragmentVisible() {

    }

    fun changeFragment(fragment: Fragment, cleanStack: Boolean = false, addToBackStack: Boolean = true) {
        val ft = activity.supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.replace(R.id.container, fragment)
        if (addToBackStack)
            ft.addToBackStack(null)
        ft.commit()
    }

    fun changeChildFragment(fragment: Fragment) {
        val ft = childFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = activity.supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
