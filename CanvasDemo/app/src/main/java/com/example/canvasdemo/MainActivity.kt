package com.example.canvasdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvasdemo.ui.theme.CanvasDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
//    DrawLine()
//    DrawRect()
    DrawImage()
}

@Composable
fun DrawLine() {
    Canvas(modifier = Modifier.size(300.dp)) {
        val height = size.height
        val width = size.width
        drawLine(
            start = Offset(x= 0f, y = 0f),
            end = Offset(x = width, y = height),
            color = Color.Blue,
            strokeWidth = 16.0f,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(30f, 10f, 10f, 10f), phase = 0f)
        )
    }
}

@Composable
fun DrawRect() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawRect(
            color = Color.Blue,
            topLeft = Offset(x=350f, y = 300f),
            size = size / 2f
        )
    }
}

@Composable
fun DrawImage() {
    val image = ImageBitmap.imageResource(id = R.drawable.vacation)
    Canvas(
            modifier = Modifier
                .size(360.dp, 270.dp)
            ) {
        drawImage(
            image = image,
            topLeft = Offset(x = 0f, y = 0f),
            colorFilter = ColorFilter.tint(
                color = Color(0xADFFAA2E),
                blendMode = BlendMode.ColorBurn
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasDemoTheme {
        MainScreen()
    }
}