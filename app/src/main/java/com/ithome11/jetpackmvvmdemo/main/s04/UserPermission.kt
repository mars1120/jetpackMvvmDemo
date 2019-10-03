package com.ithome11.jetpackmvvmdemo.main.s04

import android.app.Activity
import android.content.pm.PackageManager
import android.util.SparseArray
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class UserPermission(private val activity: Activity, vararg permissions: String) {
    val perms: Array<out String> by lazy { permissions }

    lateinit var isGrantedCallback: () -> Unit
    lateinit var notGrantedCallback: () -> Unit


    /**
     *  1. 確認權限是否註冊
     *  2. 如果註冊  呼叫`isGranted`, 或註冊權限
     *  3. 根據回傳結果呼叫 `isGranted` 或 `notGranted`
     */
    fun checkOrRequest(isGranted: () -> Unit, notGranted: () -> Unit) {
        val notGrantedPermissions = perms.filter {
            ContextCompat.checkSelfPermission(
                activity,
                it
            ) != PackageManager.PERMISSION_GRANTED
        }
            .toTypedArray()

        val permissionIsAllGranted = notGrantedPermissions.isEmpty()
        if (permissionIsAllGranted) {
            isGranted()
        } else {
            isGrantedCallback = isGranted
            notGrantedCallback = notGranted
            val requestCode =
                Math.abs((activity.hashCode() + isGranted.hashCode()).toShort().toInt())
            activity.permissions.put(requestCode, this)
            ActivityCompat.requestPermissions(activity, notGrantedPermissions, requestCode)
        }
    }

    override fun toString(): String = "${javaClass.simpleName} ${Arrays.toString(perms)}"
}

private val Activity.permissions by lazy { SparseArray<UserPermission>() }

fun Activity.permissionOf(vararg permissions: String): UserPermission =
    UserPermission(this, *permissions)


/**
 *  this has to be called in Activity.onRequestPermissionsResult
 */
fun Activity.requestPermissionResult(requestCode: Int, grantResults: IntArray) =
    permissions[requestCode]?.let {
        permissions.remove(requestCode)
        val isGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        if (isGranted) it.isGrantedCallback() else it.notGrantedCallback()
    }
