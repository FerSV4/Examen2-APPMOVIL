package com.example.examenej2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.examenej2.usercard.UserCardView
import com.example.examenej2.usercard.UserCardViewModel

class MainActivity : ComponentActivity() {
    private lateinit var userCardViewModel: UserCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userCardViewModel = UserCardViewModel("Ejercicio 2")
        setContent {
            UserCardView(viewModel = userCardViewModel, onFollowClick = {})
        }
    }
}
