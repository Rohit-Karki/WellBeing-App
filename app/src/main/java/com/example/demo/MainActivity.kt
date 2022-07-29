package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MainContent()
        }

    }


}
@ExperimentalMaterial3Api
@Composable
fun MainContent(){
CardDemo()
}

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun Tabs(tabs:List<TabItem>,pagerState: PagerState){
//
//    TabRow(selectedTabIndex = pagerState.currentPage,
//    Modifier.background(color = MaterialTheme.colorScheme.primary),
//        indicator = {
//            tabPositions ->
//            Modifier.pagerTabIndicatorOffset(pagerState=pagerState, tabPositions=tabPositions)
//        }
//        ) {
//
//    }
//
//}
//
