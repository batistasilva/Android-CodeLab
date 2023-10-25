package com.example.boxlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.layout
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

import com.example.boxlayout.ui.theme.BoxLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxLayoutTheme {
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
fun ColorBox(modifier: Modifier) {

    Box(Modifier.padding(1.dp).size(width = 50.dp, height = 10.dp).then(modifier))
}

@Composable
fun MainScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp, 80.dp)) {
        Column {
            ColorBox(
                Modifier
                    .exampleLayout(0f)
                    .background(Color.Blue)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Green)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.5f)
                    .background(Color.Yellow)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Red)
            )
            ColorBox(
                Modifier
                    .exampleLayout(0.0f)
                    .background(Color.Magenta)
            )
        }
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier,
             fontSize: Int = 150 ) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)
    Surface {
        Text(
            text = text, cellModifier.then(modifier),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}



fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x = x, y = 0)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxLayoutTheme{
        MainScreen()
    }
}