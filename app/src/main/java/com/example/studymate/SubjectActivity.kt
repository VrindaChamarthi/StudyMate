package com.example.studymate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.studymate.ui.theme.StudyMateTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState

class SubjectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    subjectGrid()
                }
            }
        }
    }
}
@Composable
fun Subjectitem(
    sub:String,
    imageUrl:String
){

    Card(
        modifier = Modifier.padding(8.dp)
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
fun generateSubjects():List<Subjects>{
    return listOf(
        Subjects("DAA","https://i.imgur.com/If1JaaE.jpeg"),
        Subjects(
            "CD",
            "https://i.imgur.com/ydKtsHF.png"
        ),
        Subjects("FIOT",  "https://i.imgur.com/P3aoXTc.png"),
        Subjects(
            "ML",
            "https://i.imgur.com/upam8g1.png"
        ),
        Subjects(
            "SL",
            "https://i.imgur.com/OhEHsyU.png"
        )
    )
}


@Composable
fun subjectGrid(){
    val subs = generateSubjects()

    var subjectname by remember{
        mutableStateOf(true)
    }

    val viewModel = SubjectspageViewModel()
    val subjectState = viewModel.Resource.collectAsState()
    val subjects = subjectState.value

    LazyColumn() {
        items(subs){ subs ->
            Subjectitem(subs.sub,subs.imageUrl)
        }
    }
}