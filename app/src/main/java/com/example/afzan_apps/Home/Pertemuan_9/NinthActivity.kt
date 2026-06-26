package com.example.afzan_apps.Home.Pertemuan_9

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.afzan_apps.Home.Pertemuan_10.TenthActivity
import com.example.afzan_apps.Home.Pertemuan_3.ThirdResultActivity
import com.example.afzan_apps.R
import com.example.afzan_apps.databinding.ActivityBaseBinding
import com.example.afzan_apps.databinding.ActivityNinthBinding
import com.example.afzan_apps.utils.NotificationHelper
import com.example.afzan_apps.utils.PermissionHelper
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNinthBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pertemuan9"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text
            val intent = Intent(this, TenthActivity::class.java)

            //startActivity(intent)

            NotificationHelper.showNotification(
                this, //Jika panggil di fragment maka requireContext()
                "Pesanan Anda",
                "Halo $email, Pesanan Anda Sedang Diproses",
                intent
            )
        }

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull() // Ambil ID chip yang dipilih
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
                // Lakukan logika filter di sini
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}