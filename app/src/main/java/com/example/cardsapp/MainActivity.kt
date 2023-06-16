package com.example.cardsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardsapp.ui.theme.CardsAppTheme

data class User(
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val signo: String,
    val conoceKotlin: Boolean,
    @DrawableRes val imagen: Int
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val name = remember { mutableStateOf("") }
            var surname by remember { mutableStateOf("") }

            CardsAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Destinations.ScreenA.route
                    ) {
                        composable(Destinations.ScreenA.route) {
                            ScreenA(navController = navController)
                        }

                        composable(Destinations.ScreenB.route) {
                            ScreenB()
                        }

                        composable(Destinations.ScreenC.route) {
                            ScreenC()
                        }
                    }
                }
            }
        }
    }
}

fun changeName(name: MutableState<String>) {
    name.value = "Nuevo texto"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    user: User,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        tonalElevation = 0.dp
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 16.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(
                                RoundedCornerShape(56.dp)
                            ),
                        painter = painterResource(id = user.imagen),
                        contentDescription = null
                    )

                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(
                                RoundedCornerShape(12.dp)
                            )
                            .background(Color.White)
                    )

                    Image(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(12.dp)
                            .clip(
                                RoundedCornerShape(12.dp)
                            )
                            .background(Color.Black),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null
                    )
                }

                /*Image(
                    modifier = Modifier
                        .height(56.dp)
                        .clickable {
                            onClick()
                        },
                    painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                )*/
            }

            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        bottom = 16.dp
                    )
                    .height(IntrinsicSize.Min),
            ) {
                Column(
                    modifier = Modifier.height(100.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = ("Nombre: " + user.nombre),
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = ("Apellidos: " + user.apellidos),
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = ("Fecha: " + user.fechaNacimiento),
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Divider(
                        modifier = Modifier
                            .size(3.dp, 85.dp),
                        color = Color.Black
                    )
                }

                Column(
                    modifier = Modifier.height(100.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = ("Signo: " + user.signo),
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = ("Conoce Kotlin: " + user.conoceKotlin),
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

private fun getMyConstraints() = ConstraintSet {
    val textA = createRefFor("a")
    val textB = createRefFor("b")
    val textC = createRefFor("c")

    constrain(textA) {
        linkTo(top = parent.top, bottom = parent.bottom)
        linkTo(start = parent.start, end = parent.end)
    }

    constrain(textB) {
        linkTo(top = textA.top, bottom = textA.bottom)
        linkTo(start = textA.end, end = parent.end)
    }

    constrain(textC) {
        linkTo(top = textA.bottom, bottom = parent.bottom)
        linkTo(start = textA.end, end = parent.end)
    }
}

@Preview(
    showBackground = true,
    heightDp = 800,
    widthDp = 400
)
@Composable
fun GreetingPreview() {
    val context = LocalContext.current
    val user1 = User(
        nombre = "Emilio Rafael",
        apellidos = "Estévez González",
        fechaNacimiento = "12/04/2002",
        signo = "Aries",
        conoceKotlin = true,
        imagen = R.drawable.avatar,
    )

    val user2 = User(
        nombre = "Lionel Andrés",
        apellidos = "Messi Cuccittini",
        fechaNacimiento = "24/06/1987",
        signo = "Aries",
        conoceKotlin = true,
        imagen = R.drawable.avatar2,
    )

    CardsAppTheme() {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CardComponent(user = user1) {
                Toast.makeText(context, "Primer icono", Toast.LENGTH_LONG).show()
            }

            CardComponent(user = user2) {
                Toast.makeText(context, "Primer icono", Toast.LENGTH_LONG).show()
            }
        }
    }
}
