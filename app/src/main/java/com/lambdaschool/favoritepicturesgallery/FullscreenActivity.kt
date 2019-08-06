package com.lambdaschool.favoritepicturesgallery

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {
    private val mHideHandler = Handler()
    private var mContentView: View? = null
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mContentView!!.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
    //    private View mControlsView;
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        val actionBar = actionBar
        actionBar?.show()
        //            mControlsView.setVisibility(View.VISIBLE);
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { view, motionEvent ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stringArray = arrayOfNulls<String>(5)

            //what's this about? superfluous? jss
        setContentView(R.layout.activity_fullscreen)

        mVisible = true
        //        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content)


        // Set up the user interaction to manually show or hide the system UI.
        mContentView!!.setOnClickListener { toggle() }

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        val intent = intent
        (mContentView as ImageView).setImageURI(Uri.parse(intent.getStringExtra("image")))
        Toast.makeText(this, "Lifecycle - onCreate", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onCreate")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        val actionBar = actionBar
        actionBar?.hide()
        //        mControlsView.setVisibility(View.GONE);
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @SuppressLint("InlinedApi")
    private fun show() {
        // Show the system bar
        mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [.AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [.AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Lifecycle - onCreate", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Lifecycle - onStart", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "Lifecycle - onResume", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "Lifecycle - onPause", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onPause")
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Lifecycle - onStop", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onStop")
    }
    override fun onDestroy() {
        Toast.makeText(this, "Lifecycle - onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Toast.makeText(this, "Lifecycle - onRestart", Toast.LENGTH_SHORT).show()
        Log.i("LifecycleFA", "onRestart")
        super.onRestart()
    }


}
