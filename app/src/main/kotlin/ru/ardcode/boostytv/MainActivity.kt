package ru.ardcode.boostytv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.tv.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import ru.ardcode.boostytv.core.ui.theme.BoostyTvTheme
import ru.ardcode.boostytv.navigation.BoostyNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoostyTvTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BoostyNavHost()
                }
            }
        }
    }
}
