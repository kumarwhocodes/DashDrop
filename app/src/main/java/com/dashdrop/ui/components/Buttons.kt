package com.dashdrop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.ui.theme.DashDropTheme
import com.dashdrop.ui.theme.bg

@Preview
@Composable
private fun ButtonsPreview() {
    Column{
        DashDropTheme {

            PrimaryButton(onClick = {}) {
                Text(text = "Button")
            }

            PrimaryButton(
                onClick = {},
                enabled = false
            ) {
                Text(text = "Button")
            }

            SecondaryButton(onClick = {}) {
                Text(text = "Button")
            }

            SecondaryButton(
                onClick = {},
                enabled = false
            ) {
                Text(text = "Button")
            }

            FAB(
                onClick = {},
                icon =painterResource(id = R.drawable.add)
            )

            SmallCircularImageButton(
                onClick = {},
                image = painterResource(id = R.drawable.google),
                desc = "google icon"
            )

        }
    }
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shapes: RoundedCornerShape = RoundedCornerShape(percent = 25),
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = bg,
        contentColor = Color.White,
        disabledContainerColor = bg.copy(alpha = 0.4f),
        disabledContentColor = Color.White.copy(alpha = 0.8f)
    ),
    contentPadding: PaddingValues = PaddingValues(25.dp, 0.dp),
    content: @Composable (RowScope.() -> Unit)
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shapes,
        colors = color,
        contentPadding = contentPadding,
        content = content
    )
}


@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shapes: RoundedCornerShape = RoundedCornerShape(percent = 25),
    color: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.onBackground,
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        disabledContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    ),
    contentPadding: PaddingValues = PaddingValues(25.dp, 0.dp),
    content: @Composable (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shapes,
        colors = color,
        contentPadding = contentPadding,
        content = content
    )
}

@Composable
fun FAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(percent = 100),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    contentDescription: String ="",
    icon: Painter
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(20.dp),
        shape = shape,
        containerColor = bg,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        elevation = elevation
    ) {
        Image(painter = icon, contentDescription = contentDescription)

    }
}

@Composable
fun LoginButton(
    value: String, onClick: () -> Unit,
    isEnabled: Boolean
) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        enabled = isEnabled
    ) {
        Text(
            text = value,
            fontSize = 15.sp
        )
    }
}

@Composable
fun SmallCircularImageButton(
    onClick: () -> Unit,
    image: Painter,
    desc: String
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(10.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(shape = CircleShape)
                .clickable(onClick = onClick),
            painter = image,
            contentDescription = desc
        )

    }

}
