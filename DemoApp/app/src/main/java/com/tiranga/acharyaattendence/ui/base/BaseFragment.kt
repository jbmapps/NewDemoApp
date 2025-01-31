package com.tiranga.acharyaattendence.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tiranga.acharyaattendence.R
import com.tiranga.acharyaattendence.databinding.DialogLoadingBinding

abstract class BaseFragment : Fragment() {

    private lateinit var loaderDialog: Dialog
    lateinit var appContext: Context

    private fun initDialog() {
        loaderDialog = Dialog(appContext, R.style.NormalDialog)
        val loadingBinding: DialogLoadingBinding = DialogLoadingBinding.inflate(layoutInflater)
        loaderDialog.setContentView(loadingBinding.getRoot())
        loaderDialog.setCancelable(false)
        loaderDialog.setCanceledOnTouchOutside(false)
        loaderDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loaderDialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    fun showLoadingDialog() {
        loaderDialog.show()
    }

    fun hideLoadingDialog() {
        if (loaderDialog != null && loaderDialog.isShowing) {
            loaderDialog.hide()
        }
    }

    fun Context.setMainContext() {
        appContext = this
        initDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}