package com.example.examenej1

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenej1.model.VistaModeloPermiso
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VistaPermiso(vm: VistaModeloPermiso = viewModel()) {
    val permissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    val estado by vm.estado.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(permissionState.status) {
        val concedido = permissionState.status.isGranted
        val denegado = !concedido && !permissionState.status.shouldShowRationale
        val dontAskAgain = !concedido && !permissionState.status.shouldShowRationale && permissionState.status is PermissionStatus.Denied
        vm.procesar(concedido, denegado, dontAskAgain)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Button(onClick = { permissionState.launchPermissionRequest() }) {
            Text("Solicitar ubicación")
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = when {
                estado.concedido -> "PERMISO: concedido"
                estado.denegado && !estado.dontAskAgain -> "PERMISO: denegado"
                estado.dontAskAgain -> "PERMISO: 'No preguntar de nuevo'\nIr a configuraciones"
                else -> "PERMISO: pendiente"
            },
            color = if (estado.concedido) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
        )

        if (estado.dontAskAgain) {
            Text(
                "Abrir configuración",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.parse("package:" + context.packageName)
                        context.startActivity(intent)
                    },
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.height(16.dp))
        Text("Permisos pendientes: ${estado.pendientes.joinToString()}")
    }
}
