package com.mhmdbh.qurani.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mhmdbh.qurani.presentation.theme.QuraniTheme
import com.mhmdbh.qurani.presentation.theme.mainColor

@Preview
@Composable
fun QuranPageComponentPreview(){

    QuraniTheme {
        QuranPageComponent(
            pageNum = 1
        )
    }
}

@Composable
fun QuranPageComponent(
    pageNum: Int
){

    val tempPage: String = when {
        pageNum >= 100 -> {
            "$pageNum"
        }
        pageNum >= 10 -> {
            "0$pageNum"
        }
        else -> {
            "00$pageNum"
        }
    }

    //model = "https://android.quran.com/data/width_1260/page001.png",

     AsyncImage(
         modifier = Modifier.fillMaxSize().background(mainColor),
         model = "https://android.quran.com/data/width_1260/page${tempPage}.png",
         contentDescription = "Page",
         contentScale = ContentScale.FillBounds
     )

}