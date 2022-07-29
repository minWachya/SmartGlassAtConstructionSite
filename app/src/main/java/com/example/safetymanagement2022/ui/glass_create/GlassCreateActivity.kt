package com.example.safetymanagement2022.ui.glass_create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.safetymanagement2022.databinding.ActivityGlassCreateBinding

class GlassCreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlassCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setBtnEnableListener()
        setFinishBtnListener()
    }

    private fun setBtnEnableListener() {
        binding.editGlassName.addTextChangedListener { text ->
            binding.btnFinish.isEnabled = text?.trim().toString().isNotEmpty()
        }
    }

    private fun setFinishBtnListener() {
        binding.btnFinish.setOnClickListener {

        }
    }

}