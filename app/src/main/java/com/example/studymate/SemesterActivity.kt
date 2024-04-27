package com.example.studymate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studymate.ui.theme.StudyMateTheme
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.material3.CardColors
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource

class SemesterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(200, 216, 230)
                ) {
                    SemesterGrid(onItemClick = { subject ->
                        val subjectsIntent = Intent(this, SubjectActivity::class.java)
                        subjectsIntent.putExtra("semester", subject.sem)
                        startActivity(subjectsIntent)
                    })
                }
            }
        }
    }
}

@Composable
fun LogoImage() {
    Image(painter = painterResource(id = R.drawable.quillff_p), contentDescription = null)
}

@Composable
fun SemesterItem(
    sem: String,
    sems: Semesters,
    isEnabled: Boolean = true,
    onItemClick: (Semesters) -> Unit
) {
    Card(
        colors = getCardColors(isEnabled),
        modifier = getCardModifier(onItemClick, sems)
    ) {
        SemesterText(sem)
    }
}

@Composable
fun getCardColors(isEnabled: Boolean): CardColors {
    return CardDefaults.cardColors(
        containerColor = if (isEnabled) {
            MaterialTheme.colorScheme.onTertiary
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
    )
}

@Composable
fun getCardModifier(onItemClick: (Semesters) -> Unit, sems: Semesters): Modifier {
    return Modifier
        .padding(8.dp)
        .shadow(20.dp)
        .clickable {
            onItemClick(sems)
        }
}

@Composable
fun SemesterText(sem: String) {
    Text(
        text = sem,
        modifier = Modifier.padding(20.dp),
        textAlign = TextAlign.Center
    )
}


@Composable
fun SemesterGrid(onItemClick: (Semesters) -> Unit) {
    val viewModel = SemesterViewModel()
    val semesterState = viewModel.semesters.collectAsState()
    val semesters = semesterState.value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayHead()
        SemesterItems(semesters = semesters, onItemClick = onItemClick)
    }
}

@Composable
fun SemesterItems(semesters: List<Semesters>, onItemClick: (Semesters) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(semesters) { semester ->
            val isEnabled = semester.sem == "3-2"
            SemesterItem(semester.sem, sems = semester, isEnabled, onItemClick)
        }
    }
}

@Composable
fun DisplayHead() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        LogoImage()
        Text(
            text = "StudyMate.",
            style = TextStyle(fontFamily = FontFamily.Cursive),
            textAlign = TextAlign.Center,
            fontSize = 45.sp
        )
        Text(
            text = "\nSemesters",
            textAlign = TextAlign.Left,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}

