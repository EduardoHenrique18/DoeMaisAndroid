package com.example.doe.ui.createPoint

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.doe.R
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.device.DeviceConnFactory
import com.example.doe.device.DeviceWifiController
import com.example.doe.domain.usecase.CheckIntentConnectionUseCase
import com.example.doe.domain.usecase.CreatePointUseCase
import com.example.doe.remote.CreatePointService
import com.example.doe.remote.RestWebService
import java.io.ByteArrayOutputStream
import java.io.File


class CreatePointActivity : AppCompatActivity(), CreatePointContract.CreatePointView {
    private lateinit var presenter: CreatePointContract.CreatePointPresenter
    private lateinit var extras: Bundle
    private lateinit var image: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_point)

        val iin = intent
        this.extras = iin.extras!!

        presenter = CreatePointPresenter(
            this,
            CreatePointUseCase(
                CreatePointService(RestWebService().api),
                SharedPreferenceUserDataSource(this)
            ),
            CheckIntentConnectionUseCase(
                DeviceWifiController(this),
                DeviceConnFactory.createConnectivityController(this)
            ),
            SharedPreferenceUserDataSource(this)
        )

        findViewById<Button>(R.id.button_point_select_image)?.setOnClickListener {
            selectImage()
        }

        findViewById<TextView>(R.id.button_pointe_create)?.setOnClickListener {
            presenter.onRegisterpointClicked()
        }
    }

    override fun getName(): String = findViewById<EditText>(R.id.text_point_name).text.toString()

    override fun getDescription(): String = findViewById<EditText>(R.id.text_point_description).text.toString()

    override fun getImage(): String = this.image

    override fun getLatitude(): Double = extras.get("latitude") as Double

    override fun getLongitude(): Double = extras.get("longitude") as Double

    override fun showEmptyInfoError() {
        Toast.makeText(
            this,
            getString(R.string.text_empty_user_info_error),
            Toast.LENGTH_LONG).show()
    }

    override fun showNoInternet() {
        Toast.makeText(
            this,
            getString(R.string.text_no_internet_error),
            Toast.LENGTH_LONG).show()
    }

    override fun pointCreated() {
        Toast.makeText(
            this,
            getString(R.string.point_created),
            Toast.LENGTH_LONG).show()
    }

    private fun selectImage() {
        val options =
            arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Add Photo!")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val f =
                    File(Environment.getExternalStorageDirectory(), "temp.jpg")
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))
                startActivityForResult(intent, 1)
            } else if (options[item] == "Choose from Gallery") {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(intent, 2)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (data != null) {
                    print(data.extras?.get("data") as Bitmap)
                }
            } else if (requestCode == 2) {
                if (data != null) {
                    val imageUri = data.data
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)

                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                    val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                    this.image = encoded
                }
            }
        }
    }
}