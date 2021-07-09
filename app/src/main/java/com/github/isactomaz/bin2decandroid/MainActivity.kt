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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val number = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val placeholder = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = number.value,
            onValueChange = { number.value = it },
            label = { Text(text = stringResource(R.string.input_number)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = isError.value,
            placeholder = { Text(placeholder.value) },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                val result = convertNumber(number.value)
                if (result.second) {
                    isError.value = result.second
                    number.value = ""
                    placeholder.value = result.first
                } else {
                    isError.value = false
                    number.value = result.first
                    placeholder.value = ""
                }
            }
        ) {
            Text(text = stringResource(R.string.convert))
        }
    }
}