package com.mhmdbh.qurani.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhmdbh.qurani.presentation.theme.QuraniTheme
import com.mhmdbh.qurani.presentation.theme.mainTextColor
import kotlinx.coroutines.flow.collect

private const val TAG = "HomeScreen"
private const val QURAN_PAGE_COUNT = 604

@Preview
@Composable
fun HomeScreenPreview(){

    QuraniTheme{

        HomeScreen(
            Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier
){

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {

        val currentPage by remember { mutableIntStateOf(0) }

        var pageCount by remember {
            mutableIntStateOf(QURAN_PAGE_COUNT)
        }

        val pageErrNum by remember {
            mutableIntStateOf(-1)
        }

        val pagerState = rememberPagerState(
            initialPage = currentPage,
            initialPageOffsetFraction = 0f,
            pageCount = {
                pageCount
            }
        )

        LaunchedEffect(key1 = pagerState){

            snapshotFlow { pagerState.currentPage }.collect{ page ->

                //currentPage = page
                Log.d(TAG, "HomeScreen: pagerState - currentPage= $page")
            }
        }

        val pageErrorStatus = remember {
            mutableMapOf(0 to "")
        }

        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    //color = mainTextColor
                    color = MaterialTheme.colorScheme.background
                ),
            verticalAlignment = Alignment.CenterVertically,
            state = pagerState
        ) { page ->

            QuranPageComponent(
                page + 1,//pagerState.currentPage + 1,
                //pageErrorStatus[pagerState.currentPage+1] ?: ""
                pageErrorStatus[page + 1] ?: ""
            ){ errMsg ->

                pageCount = if(errMsg.isNotEmpty())
                    page//pagerState.currentPage
                else
                    QURAN_PAGE_COUNT

                pageErrorStatus[page] = errMsg
            }

        }

    }
}