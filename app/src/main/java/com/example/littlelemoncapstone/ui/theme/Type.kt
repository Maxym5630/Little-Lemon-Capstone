package com.example.littlelemoncapstone.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.font.Font
import com.example.littlelemoncapstone.R

// Подключаем шрифты
val MarkaziText = FontFamily(Font(R.font.markazi_text_regular))
val Karla = FontFamily(Font(R.font.karla_regular))

// Определяем стили
val Typography = Typography(
    displayLarge = TextStyle( // Display Title
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Medium,
        fontSize = 64.sp
    ),
    displayMedium = TextStyle( // Sub title
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    titleLarge = TextStyle( // Lead text (CTA)
        fontFamily = Karla,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle( // Section title (UPPERCASE)
        fontFamily = Karla,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    titleSmall = TextStyle( // Categories
        fontFamily = Karla,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle( // Card Title
        fontFamily = Karla,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle( // Paragraph text
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    labelLarge = TextStyle( // Highlight text (e.g. price)
        fontFamily = Karla,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)
