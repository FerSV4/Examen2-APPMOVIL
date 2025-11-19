package com.example.examenej2.usercard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.examenej2.R

@Composable
fun UserCardView(
    viewModel: UserCardViewModel,
    onFollowClick: () -> Unit
) {
    val state = viewModel.state.collectAsState().value

    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = android.R.drawable.sym_def_app_icon),
                contentDescription = "Foto de usuario",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = state.nombre)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (state.siguiendo) Icons.Filled.Done else Icons.Filled.Check,
                        contentDescription = null,
                        tint = if (state.siguiendo) MaterialTheme.colorScheme.primary else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = if (state.siguiendo) "Siguiendo" else "No siguiendo")
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.handleIntent(UserCardIntent.ToggleFollow)
            onFollowClick()
        }) {
            Text(text = if (state.siguiendo) "Siguiendo" else "Seguir")
        }
    }
}
