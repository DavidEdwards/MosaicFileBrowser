package dae.mosaicfilebrowser.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dae.mosaicfilebrowser.state.ScreenState

class StateViewModel {

    var screenState by mutableStateOf<ScreenState>(ScreenState.SplashState)

}