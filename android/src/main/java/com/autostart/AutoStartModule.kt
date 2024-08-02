package com.autostart

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class AutoStartModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  init {
    context = reactContext
  }

  companion object {
    const val NAME = "AutoStart"
    var context: ReactApplicationContext? = null
  }

  @ReactMethod
  fun addAutoStartup(promise: Promise) {
    try {
      val intent = Intent()
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      val manufacturer = Build.MANUFACTURER
      when {
        "xiaomi".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")
        }
        "oppo".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")
        }
        "vivo".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")
        }
        "Letv".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")
        }
        "Honor".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")
        }
        "samsung".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.BatteryActivity")
        }
        "oneplus".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity")
        }
        "nokia".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.evenwell.powersaving.g3", "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity")
        }
        "asus".equals(manufacturer, ignoreCase = true) -> {
          intent.component = ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.autostart.AutoStartActivy")
        }
        "realme".equals(manufacturer, ignoreCase = true) -> {
          intent.action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
        }
        else -> {
          promise.reject("UNSUPPORTED_MANUFACTURER", "Manufacturer $manufacturer is not supported")
          return
        }
      }

      val resolveInfoList: List<ResolveInfo> = context!!.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
      if (resolveInfoList.isNotEmpty()) {
        context!!.startActivity(intent)
        promise.resolve(null)
      } else {
        promise.reject("ACTIVITY_NOT_FOUND", "No matching activity found for manufacturer $manufacturer")
      }
    } catch (e: Exception) {
      Log.e("AutoStartModule", "Error adding auto startup", e)
      promise.reject("ERROR_ADDING_AUTO_STARTUP", e.message, e)
    }
  }
}
