package com.dashdrop.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dashdrop.R
import com.dashdrop.ui.components.CustomInputField
import com.dashdrop.ui.components.DividerTextComponent
import com.dashdrop.ui.components.PasswordTextField
import com.dashdrop.ui.components.PrimaryButton
import com.dashdrop.ui.components.SmallCircularImageButton
import com.dashdrop.ui.theme.bg


//TODO: Color Scheme ka kal dekhenge aur bss cards wagera buttons wagera ko round krna hai side se
@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bg)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "To get more advantages sign up your accounts by filling in some informations",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        //TODO: Card ko bottom align krna hai
        Card(
            modifier = Modifier
                .padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 10.dp, bottom = 10.dp,
                            start = 5.dp, end = 5.dp
                        ),
                    verticalArrangement = Arrangement.Bottom,
                    
                ) {
                    CustomInputField(labelValue = "Your Name") {
                    }
                    CustomInputField(labelValue = "Email address") {
                    }
                    PasswordTextField(labelValue = "Enter your password",
                        onTextSelected = {})
                    //TODO: red kyu horha password field??

                    Spacer(modifier = Modifier.height(20.dp))

                    PrimaryButton(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Sign Up")
                    }

                    DividerTextComponent(value = "or sign up with")

                    //TODO: google button ko center krna hai
                    SmallCircularImageButton(
                        onClick = { },
                        image = painterResource(id = R.drawable.google),
                        desc = "google_icon",

                    )


                }
            }


        }


    }
}


@Composable
@Preview(showSystemUi = true)
fun Preview() {
    SignUpScreen()
}