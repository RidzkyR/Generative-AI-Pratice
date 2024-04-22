package com.dicoding.bertqa

import android.content.Context
import android.os.Build
import android.util.Log
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.text.qa.BertQuestionAnswerer

//inisialisasi model ML
class BertQAHelper0(val context: Context){
    private var bertQuestionAnswerer: BertQuestionAnswerer? = null

    private fun setupBertQuestionAnswerer() {

        val baseOptionsBuilder = BaseOptions.builder()

        if (CompatibilityList().isDelegateSupportedOnThisDevice){
            baseOptionsBuilder.useGpu()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){
            baseOptionsBuilder.useNnapi()
        } else {
            // Menggunakan CPU
            baseOptionsBuilder.setNumThreads(4)
        }

        val options = BertQuestionAnswerer.BertQuestionAnswererOptions.builder()
            .setBaseOptions(baseOptionsBuilder.build())
            .build()

        try {
            bertQuestionAnswerer =
                BertQuestionAnswerer.createFromFileAndOptions(context, BERT_QA_MODEL, options)
        } catch (e: IllegalStateException) {
            Log.e(TAG, "TFLite gagal untuk load model dengan error: " + e.message)
        }

    }

    companion object {
        private const val BERT_QA_MODEL = "mobilebert.tflite"
        private const val TAG = "BertQaHelper"
    }
}