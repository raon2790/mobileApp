package com.example.app_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_01.ui.theme.JWTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxWidth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JWTheme() {
                HomeScreen()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopBarArea()
        },
        bottomBar = {
            BottomBarArea()
        }
    ) { innerPadding ->
        // 실제 화면 본문
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)   // Scaffold에서 준 안전 여백
                .padding(16.dp),         // 전체 화면 여백
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // 제목
            Text(
                text = "Compose Coffee",
                style = MaterialTheme.typography.headlineMedium
            )

            // 로고 이미지
            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Jetpack Compose 로고",
                modifier = Modifier
                    .size(300.dp)
                    .padding(16.dp)
            )

            // 버튼 2개를 가로로 나란히
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { /* TODO: 커피 주문 동작 */ }) {
                    Text("커피 주문")
                }
                Button(onClick = { /* TODO: 주스 주문 동작 */ }) {
                    Text("주스 주문")
                }
            }

            // 위치 정보
            Text(
                text = "위치 : 우송대 정문 앞",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarArea() {
    TopAppBar(
        title = { Text("Compose Coffee", color = MaterialTheme.colorScheme.onPrimary) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarArea() {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),  // <= 이걸 쓰려면 fillMaxWidth import 필요
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { /* TODO */ }) {
                Text("홈", color = MaterialTheme.colorScheme.onPrimary)
            }
            Button(onClick = { /* TODO */ }) {
                Text("주문내역", color = MaterialTheme.colorScheme.onPrimary)
            }
            Button(onClick = { /* TODO */ }) {
                Text("내정보", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    JWTheme {
        HomeScreen()
    }
}
