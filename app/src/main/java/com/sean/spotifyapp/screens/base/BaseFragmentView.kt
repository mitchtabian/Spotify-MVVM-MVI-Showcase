package com.sean.myapplication.screens.BaseClasses

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.sean.spotifyapp.R
import com.sean.spotifyapp.network.AreYouSureCallback
import com.sean.spotifyapp.network.MessageModel
import com.sean.spotifyapp.network.MessageType
import com.sean.spotifyapp.network.UIComponentType
import com.sean.spotifyapp.screens.base.ReturnAction
import com.sean.spotifyapp.screens.base.views.LoadingView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.sean.spotifyapp.activity.MainActivity
import com.sean.spotifyapp.screens.menu.MenuViewModel


abstract class BaseFragmentView<vs : ViewState>(
    private val viewModel: BaseViewModel<vs>,
    context: Context
) : FrameLayout(context) {

    private lateinit var loadingView: LoadingView

    private var dialogInView: MaterialDialog? = null

    init {
        inflate(context, getLayoutRes(), this)
        initLoadingView()
    }

    private fun initObservers() {
        viewModel.dataChannel.numActiveJobs.observe(
            context as AppCompatActivity,
            Observer { resolveLoadingView(it) })
        viewModel.stateMessage.observe(context as AppCompatActivity, Observer { resolveMessageModel(it) })
        viewModel.returnAction.observe(context as AppCompatActivity, Observer { resolveReturnAction(it) })
        viewModel.viewState.observe(context as AppCompatActivity, Observer { onViewStateChanged(it as vs) })
    }

    private fun resolveLoadingView(numberOfJobs: Int) {
        if (numberOfJobs > 0) {
            loadingView.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.GONE
        }
    }

    private fun resolveMessageModel(messageModel: MessageModel?) {
        //MessageModel will be null when it's cleared after resolving
        messageModel?.let {
            onResponseReceived(messageModel, object : ErrorModelCallback {
                override fun removeMessageFromStack() {
                    viewModel.clearErrorMessage()
                    if (messageModel.returnAction != null) {
                        resolveReturnAction(messageModel.returnAction)
                    }
                }
            })
        }
    }

    private fun resolveReturnAction(returnAction: ReturnAction) {
        if (!returnAction.hasBeenUsed()) {
            resolveAction(returnAction)
            returnAction.markActionUsed()
        }
    }

    private fun onResponseReceived(
        messageModel: MessageModel,
        errorModelCallback: ErrorModelCallback
    ) {
        when (messageModel.uiComponentType) {
            is UIComponentType.AreYouSureDialog -> messageModel.message?.let {
                areYouSureDialog(
                    it,
                    messageModel.uiComponentType.callback,
                    errorModelCallback
                )
            }
            is UIComponentType.Toast -> messageModel.message?.let { displayToast(it, errorModelCallback) }
            is UIComponentType.Dialog -> displayDialog(messageModel, errorModelCallback)
            is UIComponentType.None -> errorModelCallback.removeMessageFromStack()
        }
    }


    private fun displayToast(message: String, errorModelCallback: ErrorModelCallback) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        errorModelCallback.removeMessageFromStack()
    }

    private fun displayDialog(messageModel: MessageModel, errorModelCallback: ErrorModelCallback) {
        val title = when (messageModel.messageType) {
            is MessageType.Error -> R.string.dialog_title_error
            is MessageType.Success -> R.string.dialog_title_success
            is MessageType.Info -> R.string.dialog_title_info
            is MessageType.None -> R.string.dialog_title_error
        }
        val dialog = MaterialDialog(context)
            .show {
                title(title)
                message(text = messageModel.message)
                positiveButton(R.string.ok) {
                    errorModelCallback.removeMessageFromStack()
                    dismiss()
                }
                onDismiss {
                    errorModelCallback.removeMessageFromStack()
                    it.dismiss()
                }
                cancelable(false)
            }
        dialog.show()
    }

    private fun areYouSureDialog(
        message: String,
        callback: AreYouSureCallback,
        errorModelCallback: ErrorModelCallback
    ): MaterialDialog {
        return MaterialDialog(context)
            .show {
                title(R.string.dialog_title_are_you_sure)
                message(text = message)
                negativeButton(R.string.dialog_option_cancel) {
                    callback.cancel()
                    errorModelCallback.removeMessageFromStack()
                    dismiss()
                }
                positiveButton(R.string.dialog_option_yes) {
                    callback.proceed()
                    errorModelCallback.removeMessageFromStack()
                    dismiss()
                }
                onDismiss {
                    dialogInView = null
                }
                cancelable(false)
            }
    }


    private fun initLoadingView() {
        loadingView = LoadingView(context)
        addView(loadingView)
    }

    //Latest view-initializing callback
    open fun onCreateOptionsMenu(menu: Menu?) {
        initViews(menu)
        initObservers()
        if (!viewModel.hasInitiated) {
            initialAction()
            viewModel.hasInitiated = true
        }
    }

    protected fun setStateEvent(stateEvent: StateEvent) {
        viewModel.setStateEvent(stateEvent)
    }

    open fun onOptionsItemSelected(item: MenuItem){}

    abstract fun onViewStateChanged(viewState: vs)

    abstract fun getLayoutRes(): Int

    abstract fun initViews(menu: Menu?)

    protected open fun resolveAction(action: ReturnAction){}

    abstract fun initTitle(): String

    protected open fun initialAction() {}

    protected fun getCurrentViewState() = viewModel.viewState.value as vs

    protected fun navigate(navId: Int) {
        (context as MainActivity).findNavController(R.id.nav_host_fragment)
            .navigate(navId)
    }

    interface ErrorModelCallback {
        fun removeMessageFromStack()
    }

}