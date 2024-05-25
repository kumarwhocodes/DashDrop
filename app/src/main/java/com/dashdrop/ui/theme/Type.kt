package com.dashdrop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dashdrop.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val rubikBlackStyle = FontFamily(
    Font(R.font.rubik_black,FontWeight.Black)
)
val rubikBoldStyle = FontFamily(
    Font(R.font.rubik_bold,FontWeight.Bold)
)
val rubikExtraBoldStyle = FontFamily(
    Font(R.font.rubik_extrabold,FontWeight.ExtraBold)
)
val rubikItalicStyle = FontFamily(
    Font(R.font.rubik_italic,FontWeight.Normal,FontStyle.Italic)
)
val rubikLightStyle = FontFamily(
    Font(R.font.rubik_light,FontWeight.Light)
)
val rubikMediumStyle = FontFamily(
    Font(R.font.rubik_medium,FontWeight.Medium)
)
val rubikRegularStyle = FontFamily(
    Font(R.font.rubik_regular,FontWeight.Normal)
)
val rubikSemiBoldStyle = FontFamily(
    Font(R.font.rubik_semibold,FontWeight.SemiBold)
)