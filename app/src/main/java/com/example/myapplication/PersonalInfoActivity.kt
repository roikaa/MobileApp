package com.example.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.*
import android.content.res.Configuration

class PersonalInfoActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startBackgroundMusic()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonalInfoScreen()
                }
            }
        }
    }

    private fun startBackgroundMusic() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
            mediaPlayer?.apply {
                isLooping = true
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun changeLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.start()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoScreen() {
    var showLanguageMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // Language Menu
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            }
        }

        // University Logo
        Image(
            painter = painterResource(id = R.drawable.tamanghasset_logo),
            contentDescription = stringResource(R.string.university_logo),

        )

        Spacer(modifier = Modifier.height(32.dp))

        // Title
        Text(
            text = stringResource(R.string.personal_info_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Personal Information Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Name
                PersonalInfoItem(
                    label = stringResource(R.string.name_label),
                    value = stringResource(R.string.name_value)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Date of Birth
                PersonalInfoItem(
                    label = stringResource(R.string.dob_label),
                    value = stringResource(R.string.dob_value)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // University
                PersonalInfoItem(
                    label = stringResource(R.string.university_label),
                    value = stringResource(R.string.university_value)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Specialty
                PersonalInfoItem(
                    label = stringResource(R.string.specialty_label),
                    value = stringResource(R.string.specialty_value)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Year
                PersonalInfoItem(
                    label = stringResource(R.string.year_label),
                    value = stringResource(R.string.year_value)
                )
            }
        }
    }


@Composable
fun PersonalInfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}