package com.example.safetymanagement2022.ui.list.glasscreate

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.USER_ID
import com.example.safetymanagement2022.data.remote.model.request.GlassCreateRequest
import com.example.safetymanagement2022.databinding.ActivityGlassCreateBinding
import com.example.safetymanagement2022.ui.base.BaseActivity
import com.example.safetymanagement2022.ui.basic_dialog.BasicDialog
import com.example.safetymanagement2022.ui.common.BasicDialogReturnValueInterface
import com.example.safetymanagement2022.ui.list.building.ListBuildingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GlassCreateActivity : BaseActivity<ActivityGlassCreateBinding>(R.layout.activity_glass_create),
    BasicDialogReturnValueInterface {
    private val viewModel: GlassCreateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setBtnEnableListener()
        setFinishBtnListener()

        viewModel.glassCreateResponse.observe(this@GlassCreateActivity) { data ->
            val glassName = binding.editGlassName.text.toString()
            BasicDialog("스마트 글래스 추가 완료", "‘$glassName’가 정상적으로  추가되었습니다.",
                "", "확인").show(supportFragmentManager, "CustomDialog")
        }
    }

    private fun setBtnEnableListener() {
        binding.editGlassName.addTextChangedListener { text ->
            binding.btnFinish.isEnabled = text?.trim().toString().isNotEmpty()
        }
    }

    private fun setFinishBtnListener() {
        binding.btnFinish.setOnClickListener {
            val glassName = binding.editGlassName.text.toString()
            postGlassCreate(USER_ID, GlassCreateRequest(glassName))
        }
    }

    private fun postGlassCreate(userId: String, body: GlassCreateRequest) {
        viewModel.postGlassCreate(userId, body)
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