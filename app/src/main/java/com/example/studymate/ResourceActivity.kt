package com.example.studymate

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.studymate.ui.theme.StudyMateTheme

class ResourceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subjectTitle = intent.getStringExtra("subjectName") ?: ""
        val viewModel = ViewModelProvider(this).get(ResourcepageViewModel::class.java)
        setContent {
            StudyMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(220, 230, 255)
                ) {
                    viewModel.fetchResources(subjectTitle)
                    val resourceState = viewModel.resources.collectAsState()
                    val resources = resourceState.value
                    resource_vm(resources, onResourceItemClicked = { pdfLink ->
                        openPdfIntent(pdfLink)
                    }, subjectTitle)
                }
            }
        }
    }
    private fun openPdfIntent(pdfLink: String) {
            Log.i("PdfLinkUrl", pdfLink)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(pdfLink), "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            val chooser = Intent.createChooser(intent, "Open PDF with:")
            startActivity(chooser)
    }
}

@Composable
fun resource_item(
    name: String,
    pdfLink: String,
    image: String,
    onResourceItemClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onResourceItemClicked(pdfLink)
            }

    ) {
        AsyncImage(
            model = image,
            contentDescription = image
        )
        Text(
            text = name,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun resource_vm(
    resources: List<Resource>,
    onResourceItemClicked: (String) -> Unit,
    subjectTitle: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayHeader()
        SubjectTitle(title = subjectTitle)
        ResourceGrid(resources = resources, onResourceItemClicked = onResourceItemClicked)
    }
}

@Composable
fun DisplayHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        LogosImage()
        Text(
            text = "StudyMate.",
            style = TextStyle(fontFamily = FontFamily.Cursive),
            textAlign = TextAlign.End,
            fontSize = 30.sp
        )
    }
}

@Composable
fun SubjectTitle(title: String) {
    Text(
        text = "\n$title",
        textAlign = TextAlign.Left,
        style = TextStyle(fontFamily = FontFamily.Monospace),
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun ResourceGrid(
    resources: List<Resource>,
    onResourceItemClicked: (String) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(resources) { resource ->
            resource_item(
                resource.name,
                resource.pdfLink,
                resource.image,
                onResourceItemClicked
            )
        }
    }
}
