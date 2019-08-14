package com.lambdaschool.favoritepicturesgallery

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var data: ImageData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        data = intent.getSerializableExtra("object") as ImageData
        val context = this

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        image.setOnClickListener {
            val intent = Intent(context, FullscreenActivity::class.java)
            intent.putExtra("image", data?.fileUriString)
            startActivity(intent)
        }
        Toast.makeText(this, "Lifecycle - onCreate", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onCreate")
    }

    override fun onStart() {
        super.onStart()

        image.setImageURI(data?.fileUri)
        //        image.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star));
        text_name.text = data?.name
        edit_name.setText(data?.name)
        text_uri.text = data?.fileUri.toString()
        text_description.text = data?.description
        edit_description.setText(data?.description)

//HEY remember to ask about this
        Toast.makeText(this, "Lifecycle - onStart", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onStart")
    }

    override fun onBackPressed() {
        data?.name = edit_name.text.toString()
        data?.description = edit_description.text.toString()
        val resultIntent = Intent()
        resultIntent.putExtra("object", data)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }


    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Lifecycle - onResume", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "Lifecycle - onPause", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onPause")
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Lifecycle - onStop", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onStop")
    }
    override fun onDestroy() {
        Toast.makeText(this, "Lifecycle - onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Toast.makeText(this, "Lifecycle - onRestart", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleDA", "onRestart")
        super.onRestart()
    }
}
