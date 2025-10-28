package com.example.app_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_02.ui.theme.JWTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JWTheme {
                KakaoPasswordScreen()
            }
        }
    }
}

@Composable
fun KakaoPasswordScreen() {
    // 배경 흰색
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            // 안내 문구
            Text(
                text = "회원님의 소중한 정보 보호를 위해,\n카카오계정의 현재 비밀번호를 확인해 주세요.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 이메일 표시
            Text(
                text = "luckymoon4157@gmail.com",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 비밀번호 입력칸
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("비밀번호") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 안내 문구 (비밀번호 찾기)
            TextButton(
                onClick = { /* TODO: 비밀번호 찾기 로직 추가 */ },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 0.dp)
            ) {
                Text(
                    text = "비밀번호가 기억나지 않으세요?",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF3F51B5)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 확인 버튼
            Button(
                onClick = { /* TODO: 비밀번호 검증 로직 추가 */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3F51B5),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("확인")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KakaoPasswordScreenPreview() {
    JWTheme {
        KakaoPasswordScreen()
    }
}