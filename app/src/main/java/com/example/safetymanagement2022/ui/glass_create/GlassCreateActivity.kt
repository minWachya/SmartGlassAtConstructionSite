package com.example.safetymanagement2022.ui.glass_create

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.safetymanagement2022.databinding.ActivityGlassCreateBinding
import com.example.safetymanagement2022.ui.basic_dialog.BasicDialog
import com.example.safetymanagement2022.ui.common.BasicDialogReturnValueInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GlassCreateActivity : AppCompatActivity(), BasicDialogReturnValueInterface {
    private lateinit var binding: ActivityGlassCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
            val glassName = binding.editGlassName.text
            BasicDialog("스마트 글래스 추가 완료", "‘$glassName’가 정상적으로  추가되었습니다.",
            "", "확인").show(supportFragmentManager, "CustomDialog")
        }
    }

    override fun onClickBtn1() { }
    override fun onClickBtn2() { finish() }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}