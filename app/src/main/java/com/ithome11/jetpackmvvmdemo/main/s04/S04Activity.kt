package com.ithome11.jetpackmvvmdemo.main.s04

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ithome11.jetpackmvvmdemo.R
import com.ithome11.jetpackmvvmdemo.databinding.ActivityS04Binding


class S04Activity : AppCompatActivity() {
    val permissRequestCode = 100
    private val viewModel: S04ViewModel by lazy {
        ViewModelProviders.of(this).get(S04ViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityS04Binding>(this, R.layout.activity_s04).let {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
        }

        viewModel.pickContactAction.observe(this, Observer {
            permissionOf(Manifest.permission.READ_CONTACTS).checkOrRequest({
                val intent =
                    Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                startActivityForResult(intent, permissRequestCode)
            }, {
                Toast.makeText(this, "notGranted", Toast.LENGTH_SHORT).show()
            })
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        requestPermissionResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == permissRequestCode && resultCode == Activity.RESULT_OK && data?.data != null) {

            val cursor = contentResolver.query(data.data!!, null, null, null, null)
            val nameAndPhone = cursor?.use {
                it.moveToFirst()

                val name =
                    it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA))

                name to phone
            } ?: "" to ""

            viewModel.nameAndPhone.value = nameAndPhone
        }
    }
}
