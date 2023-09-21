package com.example.labtad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.Card

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.material3.Text as Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var imageUrl by remember { mutableStateOf("") }
    var imageList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text(text = "Ingrese un enlace de imagen") }
        )

        Button(
            onClick = {
                if (imageUrl.isNotEmpty()) {
                    imageList = imageList + imageUrl
                    imageUrl = ""
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Agregar Imagen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(imageList) { imageUrl ->
                ImageListItem(imageUrl = imageUrl)
            }
        }
    }
}

@Composable
fun ImageListItem(imageUrl: String) {
    val painter: Painter = rememberImagePainter(data = imageUrl)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}



@Preview
@Composable
fun AppPreview() {
    MaterialTheme {
        App()
    }
}
