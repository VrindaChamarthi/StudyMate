package com.example.studymate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.studymate.ui.theme.StudyMateTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider

class SubjectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(SubjectspageViewModel::class.java)
        setContent {
            StudyMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(200, 216, 230)
                ) {
                    val subjectState = viewModel.subjects.collectAsState()
                    val subjects = subjectState.value
                    subjectGrid(
                        subjects,
                        onSubjectClicked = { subjectName ->
                            val resourcesIntent = Intent(this, ResourceActivity::class.java)
                            resourcesIntent.putExtra("subjectName", subjectName)
                            startActivity(resourcesIntent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LogosImage() {
    Image(painter = painterResource(id = R.drawable.quillff_p), contentDescription = null,Modifier.size(40.dp), alignment = Alignment.TopEnd)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Subjectitem(
    sub:String,
    imageUrl:String,
    onSubjectClicked: (String) -> Unit
){

    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {
            onSubjectClicked(sub)
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
                AsyncImage(
                    model = imageUrl,
                    contentDescription = imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = sub,
                    modifier = Modifier.padding(4.dp)
                )
            }
    }
}

@Composable
fun subjectGrid(subjects: List<Subjects>, onSubjectClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LogosImage()
        Text(
            text = "StudyMate.",
            style = TextStyle(fontFamily = FontFamily.Cursive),
            textAlign = TextAlign.End,
            fontSize = 30.sp
        )
        Text(
            text = "\nSubjects",
            textAlign = TextAlign.Left,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        LazyColumn {
            items(subjects) { subs ->
                Subjectitem(subs.sub, subs.imageUrl, onSubjectClicked)
            }
        }
    }
}