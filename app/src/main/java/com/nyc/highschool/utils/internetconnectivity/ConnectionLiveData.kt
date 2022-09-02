package com.nyc.highschool.utils.internetconnectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.nyc.highschool.utils.AppConstants

class ConnectionLiveData(private val context: Context?) : LiveData<ConnectionModel?>() {
  override fun onActive() {
    super.onActive()
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    context?.registerReceiver(networkReceiver, filter)
  }

  override fun onInactive() {
    super.onInactive()
    context?.unregisterReceiver(networkReceiver)
  }

  private val networkReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    var connectivityManager =
      context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun onReceive(context: Context, intent: Intent) {
      if (intent.extras != null) {
        val activeNetwork =
          connectivityManager.activeNetworkInfo
        val isConnected = activeNetwork != null &&
          activeNetwork.isConnectedOrConnecting
        if (isConnected) {
          when (activeNetwork!!.type) {
            ConnectivityManager.TYPE_WIFI -> postValue(
              ConnectionModel(
                AppConstants.WIFI_DATA,
                true
              )
            )
            ConnectivityManager.TYPE_MOBILE -> postValue(
              ConnectionModel(
                AppConstants.CELL_DATA,
                true
              )
            )
          }
        } else {
          postValue(ConnectionModel(0, false))
        }
      }
    }
  }
}