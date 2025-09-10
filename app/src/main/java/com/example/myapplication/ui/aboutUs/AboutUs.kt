package com.example.myapplication.ui.aboutUs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ui.home.itemSpacing

@Preview(showBackground = true)
@Composable
fun AboutUs() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.padding(itemSpacing))

            AppInfoCard()
            Spacer(modifier = Modifier.padding(itemSpacing))

            DescriptionCard()
            Spacer(modifier = Modifier.padding(itemSpacing))

            MissionCard()
            Spacer(modifier = Modifier.padding(itemSpacing))

            FeaturesCard()
            Spacer(modifier = Modifier.padding(itemSpacing))

            PrivacyCard()
            Spacer(modifier = Modifier.padding(itemSpacing))

            ContactCard()
            Spacer(modifier = Modifier.padding(itemSpacing))

            FooterText()
            Spacer(modifier = Modifier.padding(itemSpacing))
        }
    }
}

@Composable
private fun AppInfoCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Bat-Rate",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Version 1.0",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "From MAS Studios",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
private fun DescriptionCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Bat-Rate is a free community-powered app for rating and reviewing movies and TV shows",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun MissionCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Our Mission:",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "To help fans discover and share their passion for entertainment.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun FeaturesCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "What We Offer:",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "• Rate titles on a 10-star scale\n" +
                        "• Create personal watchlists\n" +
                        "• Read and write reviews\n" +
                        "• Explore detailed information and trivia\n" +
                        "• Get personalized recommendations",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun PrivacyCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Data Privacy:",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "We take your privacy seriously. Your data is used solely to enhance your app experience. You can manage your preferences in your account settings.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ContactCard() {
    val context = LocalContext.current

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Contact Us:",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Have feedback or need help? We'd love to hear from you!",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Row(modifier = Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically ,
               // horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = MaterialTheme.colorScheme.primary ,
                    modifier = Modifier.padding(end = 2.dp)
                )


                // Clickable email
                ClickableText(
                    text = "support@mas-batrate.com",
                    url = "mailto:support@mas-batrate.com",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(modifier = Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically ,
                // horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.icons8_github_24),
                    contentDescription = "Email Icon",
                    tint = MaterialTheme.colorScheme.primary ,
                    modifier = Modifier.padding(end = 2.dp).size(24.dp)
                )
            // Clickable website
            ClickableText(
                text = "github.com/MAS",
                url = "https://github.com/MoSrouji",
                style = MaterialTheme.typography.bodyMedium
            )
            }
        }
    }
}

@Composable
private fun ClickableText(
    text: String,
    url: String,
    style: androidx.compose.ui.text.TextStyle
) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(text)
        }
    }

    Text(
        text = annotatedString,
        style = style,
        modifier = Modifier
            .clickable {
                try {
                    val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
                    ContextCompat.startActivity(context, intent, null)
                } catch (e: Exception) {
                    // Handle exception if no app can handle the intent
                    android.widget.Toast.makeText(context, "Cannot open link", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
    )
}

@Composable
private fun FooterText() {
    Text(
        text = "© 2024 MAS. All rights reserved.",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.bodySmall
    )
}