package com.print.color.printcolor.ui.login

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.print.color.printcolor.R
import com.print.color.printcolor.ui.components.ButtonTheme.PcsButton
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonData.ButtonType
import com.print.color.printcolor.ui.components.ButtonTheme.model.ButtonThemeDefaultVariants
import com.print.color.printcolor.ui.components.NavigationRail.model.Routes
import com.print.color.printcolor.ui.components.SnackBar.PcSSnackBar
import com.print.color.printcolor.ui.components.SnackBar.SnackBarController
import com.print.color.printcolor.ui.components.SnackBar.model.SnackBarDefaultVariants
import com.print.color.printcolor.ui.components.SnackBar.rememberSnackBarController
import com.print.color.printcolor.ui.components.TextFieldTheme.PcsTextField
import com.print.color.printcolor.ui.components.TextFieldTheme.model.TextFieldDefaultVariants
import com.print.color.printcolor.ui.theme.LightBlue
import com.print.color.printcolor.ui.theme.LightLightBlue
import com.print.color.printcolor.ui.theme.LightPink
import com.print.color.printcolor.ui.theme.LightYellow
import com.print.color.printcolor.ui.theme.PrintColorTheme
import com.print.color.printcolor.ui.theme.White
import com.print.color.printcolor.utils.PcsStepDivider

const val MAX_PASSWORD_LENGTH = 6

//@Preview(showBackground = true)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginScreenViewModel: LoginScreenViewModel,
    onSignUp: () -> Unit,
    navController: NavHostController
) {
    val isLoading by loginScreenViewModel.isLoading.collectAsState()
    val snackBarController = rememberSnackBarController()

    Box(modifier = Modifier.then(modifier).fillMaxSize()) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(.40f)
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                CarouselLoginContent()
            }

            Box(
                modifier = Modifier
                    .weight(.60f)
                    .fillMaxSize()
            ) {
                FieldsLoginContent(
                    modifier = Modifier,
                    loginScreenViewModel = loginScreenViewModel,
                    onSignUp = { onSignUp() },
                    navController = navController,
                    snackBarController = snackBarController
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp), // Optional space from edge
            contentAlignment = Alignment.BottomCenter
        ) {
            snackBarController.snackBarData.value?.let { data ->
                PcSSnackBar(
                    snackBarData = data,
                    onDismiss = { snackBarController.dismiss() }
                )
            }
        }

        if (isLoading) {
            Log.d("circularProgress", "circularProgress")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselLoginContent() {
    PrintColorTheme {
        val carouselState = rememberCarouselState { 3 }

        Box(modifier = Modifier.fillMaxSize()) {
            // Carousel
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                preferredItemWidth = 3000.dp,
                itemSpacing = 0.dp,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Image(
                    painter = painterResource(
                        id = when (page) {
                            0 -> R.drawable.ic_launcher_background
                            1 -> R.drawable.ic_pcs_confirm
                            2 -> R.drawable.ic_pcs_delivery
                            else -> R.drawable.ic_pc_logo
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /*Image(
                    painter = painterResource(id = R.drawable.ic_pc_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(300.dp)
                )*/
                Text(
                    modifier = Modifier.padding(all = 24.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(R.string.login_screen_slogan_text)
                )
            }
        }
    }
}


/*@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
    locale = "es"
)*/
@Composable
private fun FieldsLoginContent(
    loginScreenViewModel: LoginScreenViewModel,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    snackBarController: SnackBarController,
    navController: NavHostController
) {

    val uiState by loginScreenViewModel.uiState.collectAsState()
    val isButtonEnabled = uiState.isValidLogin()

    val userNameValue = uiState.userName
    val passwordValue = uiState.password


    PrintColorTheme {
        LaunchedEffect(uiState.error) {
            uiState.error?.let {
                loginScreenViewModel.clearError()
                Log.d("Login", it)
                snackBarController.show(
                    SnackBarDefaultVariants.snackBarError("error credentials")

                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            ExtraSmoothWavyDiagonalBackground()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_pc_logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(R.string.login_screen_welcome_text)
                )
                Card(
                    modifier = Modifier
                        .padding(horizontal = 130.dp)
                        .border(
                            BorderStroke(1.5.dp, MaterialTheme.colorScheme.onBackground),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .then(modifier)
                            .padding(all = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        PcsTextField(
                            data = TextFieldDefaultVariants.textFieldOutlined(
                                label = "",
                                placeHolder = stringResource(R.string.login_screen_user_name_text_field),
                                keyboardType = KeyboardType.Text,
                                leadingIcon = painterResource(R.drawable.ic_pcs_client)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                loginScreenViewModel.onUserNameChanged(it)
                            },
                            value = userNameValue,
                            imeAction = ImeAction.Next
                        )

                        PcsTextField(
                            maxLength = MAX_PASSWORD_LENGTH,
                            data = TextFieldDefaultVariants.textFieldOutlinedPassword(
                                label = "",
                                placeHolder = stringResource(R.string.login_screen_pin_text_field),
                                keyboardType = KeyboardType.NumberPassword,
                                leadingIcon = painterResource(R.drawable.ic_pcs_pin)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                loginScreenViewModel.onPasswordChanged(it)
                            },
                            value = passwordValue,
                            imeAction = ImeAction.Next
                        )
                        Text(
                            modifier = Modifier.align(Alignment.End),
                            text = stringResource(R.string.login_screen_forgot_pin_text)
                        )
                        PcsButton(
                            onClick = {
                                loginScreenViewModel.onLoginClicked(
                                    userName = userNameValue,
                                    password = passwordValue
                                ) {
                                    navController.navigate(Routes.Home.route)
                                }
                            },
                            data = ButtonThemeDefaultVariants.buttonDataWithIcon(
                                label = stringResource(R.string.login_screen_login_button_text),
                                type = ButtonType.OUTLINED,
                                contentDescription = "Content Description",
                                icon = painterResource(R.drawable.ic_pcs_login),
                                isEnabled = isButtonEnabled
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        PcsStepDivider(modifier = Modifier.fillMaxWidth())
                        PcsButton(
                            onClick = { onSignUp() },
                            data = ButtonThemeDefaultVariants.buttonDefaultData(
                                label = stringResource(R.string.login_screen_create_account_button_text),
                                type = ButtonType.FILLED,
                                contentDescription = "Content Description",
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ExtraSmoothWavyDiagonalBackground(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        drawRect(color = LightBlue)

        val path = Path().apply {
            moveTo(0f, height)

            val waveCount = 5
            val segmentWidth = width / waveCount
            val segmentHeight = height / waveCount

            for (i in 0 until waveCount) {
                val nextX = segmentWidth * (i + 1)
                val nextY = height - segmentHeight * (i + 1)

                val controlX1 = segmentWidth * i + segmentWidth * 0.25f
                val controlY1 = height - segmentHeight * i + (if (i % 2 == 0) -60f else 60f)

                val controlX2 = segmentWidth * i + segmentWidth * 0.75f
                val controlY2 = height - segmentHeight * i + (if (i % 2 == 0) -60f else 60f)

                cubicTo(
                    controlX1, controlY1,
                    controlX2, controlY2,
                    nextX, nextY
                )
            }

            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(
            path = path,
            color = White
        )

        // Círculos decorativos con imperfecciones
        drawCircle(color = LightPink, radius = 60f, center = Offset(width * 0.2f, height * 0.85f))
        drawCircle(
            color = LightYellow,
            radius = 45f,
            center = Offset(width * 0.65f, height * 0.52f)
        )
        drawCircle(
            color = LightLightBlue,
            radius = 35f,
            center = Offset(width * 0.52f, height * 0.35f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TestBackground(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        ExtraSmoothWavyDiagonalBackground()

        // Aquí tu contenido encima
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Tu Login, TextFields, etc.
        }
    }

}