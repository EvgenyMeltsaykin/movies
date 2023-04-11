package com.cartoonapp.animemovies.android.features.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.cartoonapp.feature.movies.MoviesComponent
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@Composable
fun MoviesScreen(
    component: MoviesComponent,
    modifier: Modifier
) {
    val state = component.stateScreen.collectAsState()

    Box(modifier = modifier) {
        if (state.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column() {
            LazyColumn(modifier = Modifier.weight(1f)) {
                if (!state.value.isVisibleAction) {
                    items(state.value.texts) { Text(text = it) }
                } else {
                    items(state.value.actions) { Text(text = it) }
                }
            }
            TextField(modifier = Modifier.fillMaxWidth(), value = state.value.inputText, onValueChange = component::onTextChange)
            Text(text = state.value.searchRequest.toString())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = component::refresh
                ) {
                    Text(text = "Обновить")
                }
                Button(
                    onClick = component::onActionShowClick
                ) {
                    val text = if (state.value.isVisibleAction) {
                        "Скрыть лог"
                    } else {
                        "Показать лог"
                    }
                    Text(text = text)
                }
                if (state.value.isVisibleAction) {
                    Button(
                        onClick = component::onClearActionClick
                    ) {
                        Text(text = "Очистить")
                    }
                }
            }
        }
    }

}

@Composable
fun MyContent() {

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        // Fetching the Local Context
        val mContext = LocalContext.current

        // Declaring a string value
        // that stores raw video url
        val mVideoUrl = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4"

        // Declaring ExoPlayer
        //val mediaItem: MediaItem = MediaItem.fromUri("https://kodik.info/serial/40646/986edaf0cc3b8e3338abe83b87f5c62e/720p")
        val mediaItem: MediaItem =
            MediaItem.fromUri("https://cloud.kodik-storage.com/useruploads/1d5d87fb-20d5-4cd8-9474-25df97ce7e16/aacfebd9848a2d1439745a701c2d11b5:2023033021/360.mp4")
        val mExoPlayer = remember(mContext) {
            ExoPlayer.Builder(mContext).build().apply {
                val dataSourceFactory = DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, mContext.packageName))
                val source = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
                prepare(source)
            }
        }

        // Implementing ExoPlayer
        AndroidView(factory = { context ->
            PlayerView(context).apply {
                player = mExoPlayer
            }
        })
    }
}
