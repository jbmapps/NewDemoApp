package com.kraaft.driver.manager.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.kraaft.driver.manager.R
import com.kraaft.driver.manager.databinding.LayoutErrorBinding
import org.json.JSONObject

fun EditText.isNotEmpty(context: Context): Boolean {
    if (text.isEmpty()) {
        error = context.getString(R.string.kk_error_empty_edit)
    }
    return text.isNotEmpty()
}

fun Context.getErrorMessage(message: String): String {
    return message.ifEmpty {
        getString(R.string.kk_error_unknown)
    }
}

fun Context.showErrorToast(data: String) {
    try {
        val response = JSONObject(data)
        showToast(getErrorMessage(response.optString("message")))
    } catch (e: Exception) {
        showToast(data.ifEmpty { resources.getString(R.string.kk_error_unknown) })
    }
}


fun LayoutErrorBinding.showLoading(cvMain: ConstraintLayout) {
    cvLoading.visibility = View.VISIBLE
    cvMain.visibility = View.GONE
    cvError.visibility = View.GONE
}

fun LayoutErrorBinding.showError(
    message: String,
    cvMain: ConstraintLayout,
    buttonText: String = "Retry",
    action: (() -> Unit)? = null
) {
    cvRootError.visibility = View.VISIBLE
    cvMain.visibility = View.GONE
    cvLoading.visibility = View.GONE
    cvError.visibility = View.VISIBLE
    tvMessage.text = message
    action?.let {
        btnRetry.text = buttonText
        btnRetry.isVisible = true
        btnRetry.onSingleClick {
            action.invoke()
        }
    }
}


fun LayoutErrorBinding.showPage(cvMain: ConstraintLayout) {
    cvError.visibility = View.GONE
    cvMain.visibility = View.VISIBLE
}
