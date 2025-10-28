package com.example.w06_bubble

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w06_bubble.ui.theme.JWTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JWTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BubbleGameScreen()
                }
            }
        }
    }
}

data class Bubble(
    val id: Int,
    val position: Offset,
    val radius: Float,
    val color: Color
)

@Composable
fun BubbleComposable(
    bubble: Bubble,
    onClick: () -> Unit
) {
    Canvas(
        modifier = Modifier
            .size((bubble.radius * 2).dp)
            .offset(x = bubble.position.x.dp, y = bubble.position.y.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        drawCircle(
            color = bubble.color,
            radius = size.width / 2,
            center = center
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BubbleGameScreen() {
    var score by remember { mutableStateOf(0) }
    var timeLeft by remember { mutableStateOf(60) }
    var isGameOver by remember { mutableStateOf(false) }

    // 타이머
    LaunchedEffect(isGameOver, timeLeft) {
        while (!isGameOver && timeLeft > 0) {
            delay(1000L)
            timeLeft--
            if (timeLeft == 0) {
                isGameOver = true
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GameStatusRow(score = score, timeLeft = timeLeft)

        if (isGameOver) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "GAME OVER",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Text(
                    text = "점수: $score",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        } else {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                // 화면 크기에 맞춰 랜덤 버블 하나를 상태로 유지
                var bubble by remember {
                    mutableStateOf(
                        Bubble(
                            id = Random.nextInt(),
                            position = Offset(
                                x = Random.nextFloat() * maxWidth.value,
                                y = Random.nextFloat() * maxHeight.value
                            ),
                            radius = Random.nextFloat() * 50 + 50,
                            color = Color(
                                red = Random.nextInt(256),
                                green = Random.nextInt(256),
                                blue = Random.nextInt(256),
                                alpha = 200
                            )
                        )
                    )
                }

                BubbleComposable(
                    bubble = bubble,
                    onClick = {
                        score++
                        // 새 위치/색으로 다시 생성
                        bubble = Bubble(
                            id = Random.nextInt(),
                            position = Offset(
                                x = Random.nextFloat() * maxWidth.value,
                                y = Random.nextFloat() * maxHeight.value
                            ),
                            radius = Random.nextFloat() * 50 + 50,
                            color = Color(
                                red = Random.nextInt(256),
                                green = Random.nextInt(256),
                                blue = Random.nextInt(256),
                                alpha = 200
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun GameStatusRow(score: Int, timeLeft: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Score: $score",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Time: ${timeLeft}s",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BubbleGamePreview() {
    JWTheme {
        BubbleGameScreen()
    }
}
