package com.example.safetymanagement2022.ui.list.buildingdetail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.example.safetymanagement2022.GlideApp
import com.example.safetymanagement2022.R
import com.example.safetymanagement2022.common.*
import com.example.safetymanagement2022.data.remote.model.response.SafetyIssue
import com.example.safetymanagement2022.databinding.FragmentBuildingDetailBinding
import com.example.safetymanagement2022.ui.base.BaseFragment
import com.example.safetymanagement2022.ui.common.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.poi.util.Units
import org.apache.poi.xwpf.usermodel.Document
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.net.URL
import kotlin.math.abs


@AndroidEntryPoint
class BuildingDetailFragment: BaseFragment<FragmentBuildingDetailBinding>(R.layout.fragment_building_detail) {
    private val viewModel: BuildingDetailViewModel by viewModels()
    lateinit var issueList: List<SafetyIssue>

    private lateinit var loadingDialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setBackBtnClickListener()
        setLayout()

        viewModel.buildingDetail.observe(viewLifecycleOwner) { data ->
            binding.detail = data
            issueList = data.issueList
            binding.rvIssueDetail.adapter = BuildingDetailAdapter(data.issueList)
            binding.rvIssueDetail.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            binding.tvFloor.text = if(data.minFloor == 0) "지상 1층" else if(data.maxFloor == 0) "지하 1층" else "지상 1층"
            setShowSelectFloorDialog(abs(data.minFloor), data.maxFloor)
            setDrawing(if(data.minFloor == 0) 1 else if(data.maxFloor == 0) 0 else 1, 1)
            val arrFloor = setFloorList(abs(data.minFloor), data.maxFloor)
            setSpinnerBtn(arrFloor)
        }

        setDownload()
    }

    private fun setDownload() {
        binding.tvDownload.setOnClickListener {
            lifecycleScope.launch(IO) {
                withContext(Main) {
                    loadingDialog = LoadingDialog(requireContext())
                    loadingDialog.show()
                }
                // 폴더 생성(해당 경로의 폴더가 존재하지 않으면 해당 경로에 폴더 생성)
                val folderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/DetectUs/"
                val folder = File(folderPath)
                if (!folder.isDirectory) folder.mkdir()


                val xwpfDocument = XWPFDocument()
                val xwpfParagraphTitle: XWPFParagraph = xwpfDocument.createParagraph()
                val xwpfRunTitle: XWPFRun = xwpfParagraphTitle.createRun()

                // title 추가
                xwpfRunTitle.fontSize = 14
                xwpfRunTitle.setText(viewModel.buildingDetail.value?.buildingName)

                // issue 추가
                var index = 0
                issueList.forEach {
                    val xwpfParagraphContext: XWPFParagraph = xwpfDocument.createParagraph()
                    val xwpfRunContext: XWPFRun = xwpfParagraphContext.createRun()

                    // text 추가
                    xwpfRunContext.fontSize = 10
                    xwpfRunContext.setText(it.room + " - " + it.floor + " - " + it.details)

                    val xwpfParagraphImage: XWPFParagraph = xwpfDocument.createParagraph()
                    val xwpfRunImage: XWPFRun = xwpfParagraphImage.createRun()
                    // image file 생성
                    val bitmap = convertUrlToBitmap(it.picture)
                    val imageFile = convertBitmapToFile(bitmap, index++)
                    val imageData = FileInputStream(imageFile)

                    // image 추가
                    xwpfRunImage.addPicture(
                        imageData,
                        Document.PICTURE_TYPE_PNG,
                        imageFile.name,
                        Units.toEMU(400.0),
                        Units.toEMU(200.0)
                    ) // 400x200 pixels
                }

                val buildingName = viewModel.buildingDetail.value?.buildingName
                val fileOutputStream = FileOutputStream(folderPath + "/${buildingName}.docx")
                xwpfDocument.write(fileOutputStream)

                fileOutputStream.flush()
                fileOutputStream.close()
                xwpfDocument.close()

                withContext(Main) {
                    loadingDialog.dismiss()
                    Toast.makeText(context, "파일을 성공적으로 저장했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun convertUrlToBitmap(str: String): Bitmap {
        val bitmap = try {
            val imageTask = URLtoBitmapTask(resources.getDrawable(R.drawable.ic_image_off)).apply {
                url = URL(str)
            }
            imageTask.execute().get()
        }
        catch (e: FileNotFoundException) {
            val drawable: Drawable = resources.getDrawable(R.drawable.ic_image_off)
            (drawable as BitmapDrawable).bitmap
        }
        return bitmap
    }

    private fun convertBitmapToFile(bitmap: Bitmap, fileName: Int): File {
        val cache = requireContext().externalCacheDir
        val shareFile = File(cache, "${fileName}.png")
        val out = FileOutputStream(shareFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.flush()
        out.close()
        return shareFile
    }

    private fun setLayout() {
        val buildingId = requireArguments().getInt(KEY_BUILDING_DETAIL_ID)
        viewModel.getBuildingDetail(buildingId)
    }

    private fun setBackBtnClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setFloorList(minFloor: Int, maxFloor: Int): ArrayList<String> {
        val arrTemp = arrayListOf<String>()
        for (i in maxFloor downTo 1) arrTemp.add("지상 ${i}층")
        for(i in 1..minFloor) arrTemp.add("지하 ${i}층")
        return arrTemp
    }

    private fun setSpinnerBtn(arrFloor: ArrayList<String>) {
        // 더 지상으로
        // arrFloor = [지상 2층, 지상 1층, 지하 1층...]
        binding.ivRight.setOnClickListener {
            val curIndex = arrFloor.indexOf(binding.tvFloor.text.toString())
            if(curIndex > 0){
                binding.tvFloor.text = arrFloor[curIndex-1]
                setDrawingFromBtn(curIndex-1)
                ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(arrFloor[curIndex-1])
            }
        }
        // 더 지하로
        binding.ivLeft.setOnClickListener {
            val curIndex = arrFloor.indexOf(binding.tvFloor.text.toString())
            if(curIndex != arrFloor.size-1){
                binding.tvFloor.text = arrFloor[curIndex+1]
                setDrawingFromBtn(curIndex+1)
                ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(arrFloor[curIndex+1])
            }
        }
    }

    private fun setDrawing(minMax: Int, floor: Int) {
        // minMax: 지하 0, 지상 1
        // floor: 1층: 0, 2층: 1, 3층: 2
        // 지상 1층 = 1 0
        // min 1 Max 3에서 [3 2 1 1]
        // 지상 1층의 drawingList 위치 = drawingList[2] = max - floor = 3 - 1 = 2
        // 지하 1층의 drawingList 위치 = drawingList[3] = min + max - floor = 1 + 3 - 1 = 3
        val data = viewModel.buildingDetail.value
        if (data != null) {
            val imgUrlIndex: Int = if(minMax == 1) data.maxFloor - (floor + 1)
                                    else data.maxFloor + abs(data.minFloor) - (floor + 1)
            try{
                GlideApp.with(requireActivity())
                    .load(data.drawingList[imgUrlIndex])
                    .into(binding.ivDrawing)
            } catch(e: IndexOutOfBoundsException){
               Log.d("mmm detail frag", "index error")
             }
        }
    }
    private fun setDrawingFromBtn(floor: Int) {
        val data = viewModel.buildingDetail.value
        if (data != null) {
            try{
                GlideApp.with(requireActivity())
                    .load(data.drawingList[floor])
                    .into(binding.ivDrawing)
            } catch(e: IndexOutOfBoundsException){
                Log.d("mmm detail frag", "index error")
            }
        }
    }

    private fun setShowSelectFloorDialog(minFloor: Int, maxFloor: Int) {
        binding.tvFloor.setOnClickListener {
            SelectFloorDialog(minFloor, maxFloor).show(parentFragmentManager, "SelectFloorDialog")
        }
        parentFragmentManager.setFragmentResultListener(KEY_DIALOG_DETAIL,
            viewLifecycleOwner) { _, bundle ->
            val floorText = bundle.get(KEY_DIALOG_DETAIL_TEXT).toString()
            val minMax = bundle.get(KEY_DIALOG_DETAIL_MIN_MAX).toString().toInt()
            val floor = bundle.get(KEY_DIALOG_DETAIL_FLOOR).toString().toInt()
            binding.tvFloor.text = floorText
            ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(floorText)
            setDrawing(minMax, floor)
        }
        ((binding.rvIssueDetail.adapter) as BuildingDetailAdapter).filter.filter(binding.tvFloor.text)
    }

}

class URLtoBitmapTask(val drawable: Drawable) : AsyncTask<Void, Void, Bitmap>() {
    //액티비티에서 설정해줌
    lateinit var url:URL
    override fun doInBackground(vararg params: Void?): Bitmap {
        val bitmap = try {
            BitmapFactory.decodeStream(url.openStream())
        } catch (e: FileNotFoundException) {
            (drawable as BitmapDrawable).bitmap
        }
        return bitmap
    }
    override fun onPostExecute(result: Bitmap) {
        super.onPostExecute(result)
    }
}