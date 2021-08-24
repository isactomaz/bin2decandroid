package com.github.isactomaz.bin2decandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.isactomaz.bin2decandroid.ui.theme.Bin2DecAndroidTheme
import com.github.isactomaz.bin2decandroid.utils.convertNumber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bin2DecAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var number by remember { mutableStateOf("") }
    var binary by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var placeholder by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (binary.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Result = $binary")
            Spacer(modifier = Modifier.padding(8.dp))
        }
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text(text = stringResource(R.string.input_number)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isError,
            placeholder = { Text(placeholder) },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                val result = convertNumber(number)
                if (result.second) {
                    number = ""
                    binary = ""
                    placeholder = result.first
                } else {
                    binary = result.first
                    placeholder = ""
                }
                isError = result.second
            }
        ) {
            Text(text = stringResource(R.string.convert))
        }
    }
}