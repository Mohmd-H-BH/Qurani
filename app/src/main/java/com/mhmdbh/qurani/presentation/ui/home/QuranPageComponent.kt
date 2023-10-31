package com.mhmdbh.qurani.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mhmdbh.qurani.R
import com.mhmdbh.qurani.presentation.theme.QuraniTheme
import com.mhmdbh.qurani.presentation.theme.mainColor

private const val TAG = ""

@Preview
@Composable
fun QuranPageComponentPreview() {

    QuraniTheme {
        QuranPageComponent(
            pageNum = 1,
            isError = ""
        ) {}
    }
}

@Composable
fun QuranPageComponent(
    pageNum: Int,
    isError: String,
    onError: (errMsg: String) -> Unit
) {

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


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        if (isError.isNotEmpty()) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.msgCheckInternetConnection),
                style = TextStyle.Default.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            )

        } else {

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(mainColor),
                model = "https://android.quran.com/data/width_1260/page${tempPage}.png",
                contentDescription = "Page",
                contentScale = ContentScale.FillBounds,
                onSuccess = {
                    onError("")
                },
                onError = {

                    val errorMsg = it.result.throwable.message ?: "error"

                    Log.d(TAG, "QuranPageComponent: ERROR= $errorMsg")


                    onError(errorMsg)
                },
                fallback = painterResource(id = R.drawable.ic_launcher_foreground),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            )
        }

    }
}