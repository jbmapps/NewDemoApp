package com.kraaft.driver.manager.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.kraaft.driver.manager.R
import com.kraaft.driver.manager.databinding.DialogLoadingBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.graphics.drawable.toDrawable

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    private var loaderDialog: Dialog? = null

    private fun initDialog() {
        loaderDialog = Dialog(this, R.style.NormalDialog)
        val loadingBinding: DialogLoadingBinding = DialogLoadingBinding.inflate(layoutInflater)
        loaderDialog?.let {
            it.setContentView(loadingBinding.getRoot())
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    fun showLoadingDialog() {
        loaderDialog?.show()
    }

    fun hideLoadingDialog() {
        loaderDialog?.let {
            if (it.isShowing)
                it.dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }
}