package com.example.screentimeout

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var originalTimeout: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!Settings.System.canWrite(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_WRITE_SETTINGS,
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        }

        originalTimeout = Settings.System.getInt(
            contentResolver,
            Settings.System.SCREEN_OFF_TIMEOUT
        )

        findViewById<Button>(R.id.btn15).setOnClickListener {
            setTimeout(15 * 60 * 1000)
        }
        findViewById<Button>(R.id.btn20).setOnClickListener {
            setTimeout(20 * 60 * 1000)
        }
        findViewById<Button>(R.id.btn30).setOnClickListener {
            setTimeout(30 * 60 * 1000)
        }
        findViewById<Button>(R.id.btnAlways).setOnClickListener {
            setTimeout(24 * 60 * 60 * 1000)
        }
    }

    private fun setTimeout(ms: Int) {
        Settings.System.putInt(
            contentResolver,
            Settings.System.SCREEN_OFF_TIMEOUT,
            ms
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Settings.System.putInt(
            contentResolver,
            Settings.System.SCREEN_OFF_TIMEOUT,
            originalTimeout
        )
    }
}
