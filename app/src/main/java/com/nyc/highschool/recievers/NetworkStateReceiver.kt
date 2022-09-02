package com.nyc.highschool.recievers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import java.util.*

class NetworkStateReceiver : BroadcastReceiver() {
  //region Properties
  private var listeners: MutableList<NetworkStateReceiverListener> = ArrayList()
  private var connected = false

  //endregion

  //region Lifecycle

  override fun onReceive(context: Context?, intent: Intent?) {
    if (intent?.extras == null) {
      return
    }
    if (context != null) {
      val manager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val ni = manager.activeNetworkInfo
      connected = ni != null && ni.isConnected
      notifyStateToAll()
    }
  }

  //endregion

  //region private Method

  private fun notifyStateToAll() {
    for (listener in listeners)
      notifyState(listener)
  }

  private fun notifyState(listener: NetworkStateReceiverListener) {
    when {
      connected -> listener.networkAvailable()
      else -> listener.networkUnavailable()
    }
  }

  open fun addListener(l: NetworkStateReceiverListener) {
    listeners.add(l)
    notifyState(l)
  }

  open fun removeListener(l: NetworkStateReceiverListener) {
    listeners.remove(l)
  }

  //endregion

  //region Interface

  interface NetworkStateReceiverListener {
    fun networkAvailable()
    fun networkUnavailable()
  }
  //endregion
}