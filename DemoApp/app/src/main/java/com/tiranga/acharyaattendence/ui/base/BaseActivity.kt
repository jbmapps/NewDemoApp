package com.tiranga.acharyaattendence.ui.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.tiranga.acharyaattendence.R
import com.tiranga.acharyaattendence.databinding.DialogLoadingBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var loaderDialog: Dialog

    private fun initDialog() {
        loaderDialog = Dialog(this, R.style.NormalDialog)
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
        try {
            loaderDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideLoadingDialog() {
        try {
            if (loaderDialog != null && loaderDialog.isShowing) {
                loaderDialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
    }
}