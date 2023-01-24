package com.mt.android.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.foxitImageLock.lockscreen.PFFLockScreenConfiguration
import com.foxitImageLock.lockscreen.fragments.PFLockScreenFragment
import com.foxitImageLock.lockscreen.fragments.PFLockScreenFragment.OnPFLockScreenCodeCreateListener
import com.foxitImageLock.lockscreen.fragments.PFLockScreenFragment.OnPFLockScreenLoginListener
import com.foxitImageLock.lockscreen.security.PFResult
import com.foxitImageLock.lockscreen.viewmodels.PFPinCodeViewModel
import com.mt.android.foxit.R
import com.mt.android.util.PreferencesSettings.getCode
import com.mt.android.util.PreferencesSettings.saveToPref

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val mCodeCreateListener = OnPFLockScreenCodeCreateListener { encodedCode: String? ->
        Toast.makeText(this@SplashActivity, "Code created", Toast.LENGTH_SHORT).show()
        saveToPref(this@SplashActivity, encodedCode)
        showMainFragment()
    }
    private val mLoginListener: OnPFLockScreenLoginListener = object : OnPFLockScreenLoginListener {
        override fun onCodeInputSuccessful() {
            Toast.makeText(this@SplashActivity, "Code successfully", Toast.LENGTH_SHORT).show()
            showMainFragment()
        }

        override fun onFingerprintSuccessful() {
            Toast.makeText(
                this@SplashActivity, "Fingerprint successfully", Toast.LENGTH_SHORT
            ).show()
            showMainFragment()
        }

        override fun onPinLoginFailed() {
            Toast.makeText(this@SplashActivity, "Wrong pin", Toast.LENGTH_SHORT).show()
        }

        override fun onFingerprintLoginFailed() {
            Toast.makeText(this@SplashActivity, "Fingerprint failed", Toast.LENGTH_SHORT).show()
        }
    }
    var text1: TextView? = null
    var image: ImageView? = null
    private var slideUp: Animation? = null
    private var csRunnable1 = Runnable {
        image!!.startAnimation(slideUp)
        text1!!.startAnimation(slideUp)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        text1 = findViewById(R.id.text1)
        image = findViewById(R.id.image)
        val handler1 = Handler()
        handler1.postDelayed(csRunnable1, 0)
        supportActionBar!!.hide()
        slideUp = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        slideUp?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                image?.visibility = View.VISIBLE
                text1?.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                Handler().postDelayed({
                    image?.visibility = View.GONE
                    text1?.visibility = View.GONE
                    showLockScreenFragment()
                }, 1000)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

    private fun showLockScreenFragment() {
        PFPinCodeViewModel().isPinCodeEncryptionKeyExist.observe(
            this
        ) { result: PFResult<Boolean>? ->
            if (result == null) {
                return@observe
            }
            if (result.error != null) {
                Toast.makeText(
                    this@SplashActivity, "Can not get pin code info", Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            showLockScreenFragment(result.result)
        }
    }

    private fun showLockScreenFragment(isPinExist: Boolean) {
        val builder = PFFLockScreenConfiguration.Builder(this)
            .setTitle(if (isPinExist) "Unlock with your pin code or fingerprint" else "Create Code")
            .setCodeLength(4).setLeftButton(" ").setErrorVibration(true).setErrorAnimation(true)
            .setClearCodeOnError(true).setAutoShowFingerprint(true).setUseFingerprint(true)
        val fragment =
            PFLockScreenFragment()
        fragment.setOnLeftButtonClickListener {
            Toast.makeText(
                this@SplashActivity, "Left " + "button pressed", Toast.LENGTH_LONG
            ).show()
        }
        builder.setMode(if (isPinExist) PFFLockScreenConfiguration.MODE_AUTH else PFFLockScreenConfiguration.MODE_CREATE)
        if (isPinExist) {
            fragment.setEncodedPinCode(getCode(this))
            fragment.setLoginListener(mLoginListener)
        }
        fragment.setConfiguration(builder.build())
        fragment.setCodeCreateListener(mCodeCreateListener)
        supportFragmentManager.beginTransaction().replace(R.id.container_view, fragment).commit()
    }

    private fun showMainFragment() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}