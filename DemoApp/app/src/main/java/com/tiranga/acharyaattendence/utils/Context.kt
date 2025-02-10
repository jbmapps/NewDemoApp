package com.tiranga.acharyaattendence.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.tiranga.acharyaattendence.R
import com.tiranga.acharyaattendence.databinding.DialogCommonBinding
import com.tiranga.acharyaattendence.model.NetworkResult
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun showLog(text: String = "log") {
    try {
        Log.e("logError", text)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.getProgressImage(): CircularProgressDrawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 10f
        centerRadius = 50f
        setColorSchemeColors(
            getColor(R.color.colorMain)
        )
        start()
    }
}

fun Context.handleResponse(
    response: Response<ResponseBody>,
    data: MutableLiveData<NetworkResult<ResponseBody>>
) {
    if (response.isSuccessful && response.body() != null) {
        data.postValue(NetworkResult.Success(response.body()!!))
    } else if (response.errorBody() != null) {
        try {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            data.postValue(NetworkResult.Error(errorObj.getString("message")))
        } catch (e: Exception) {
            e.printStackTrace()
            data.postValue(NetworkResult.Error(resources.getString(R.string.kk_error_unknown)))
        }
    } else {
        data.postValue(NetworkResult.Error(resources.getString(R.string.kk_error_unknown)))
    }
}

fun View.onSingleClick(debounceTime: Long = 1500, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun Context.showCommonDialog(
    message: String = resources.getString(R.string.kk_error_unknown),
    buttonText: String = resources.getString(R.string.kk_ok), action: () -> Unit
) {
    val dialogN = Dialog(this)
    val commonBinding: DialogCommonBinding =
        DialogCommonBinding.inflate(LayoutInflater.from(this))
    dialogN.setContentView(commonBinding.getRoot())
    if (dialogN.window != null) {
        dialogN.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#802E2E2E")))
        dialogN.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
    dialogN.setCancelable(false)
    dialogN.setCanceledOnTouchOutside(false)
    commonBinding.btnCancel.visibility = View.GONE
    commonBinding.btnSubmit.text = buttonText
    commonBinding.btnSubmit.onSingleClick {
        dialogN.setOnDismissListener { action.invoke() }
        dialogN.dismiss()
    }
    commonBinding.tvMessage.text = message
    dialogN.show()
}

fun Context.showConfirmDialog(
    message: String = resources.getString(R.string.kk_error_unknown),
    btnYesText: String = resources.getString(R.string.kk_ok),
    btnNoText: String = resources.getString(R.string.kk_cancel),
    action: () -> Unit
) {
    try {
        val dialog = Dialog(this)
        val binding: DialogCommonBinding =
            DialogCommonBinding.inflate(LayoutInflater.from(this))
        dialog.setContentView(binding.getRoot())
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#802E2E2E")))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        binding.tvMessage.text = message
        binding.btnCancel.text = btnNoText
        binding.btnSubmit.text = btnYesText
        binding.btnSubmit.onSingleClick {
            dialog.setOnDismissListener { action.invoke() }
            dialog.dismiss()
        }
        binding.btnCancel.onSingleClick { dialog.dismiss() }
        dialog.show()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}