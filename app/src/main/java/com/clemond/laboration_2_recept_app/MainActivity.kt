package com.clemond.laboration_2_recept_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clemond.laboration_2_recept_app.ui.theme.Laboration_2_Recept_AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboration_2_Recept_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        MyApp()
                }
            }
        }
    }
}


// --------NAVIGATION----------//

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homePage"){
        composable("homePage") {
            HomePage(navController)
        }
        composable("aboutPage") {
            AboutPage(navController)
        }
        composable("signInPage") {
            SignInPage(navController)
        }
        composable("aboutButton") {
            AboutButton(navController)
        }
        composable("buttonRow") {
            ButtonRow(navController)
        }
        composable("backButton") {
            BackButton(navController)
        }
        composable("signInButton") {
            SignInButton(navController)
        }
        composable("signInForm") {
            SignInForm(navController)
        }
        composable("loggedInPage/{username}"){ navBackStackEntry ->
            val username = navBackStackEntry.arguments?.getString("username")
            if (username != null) {
                LoggedInPage(navController,username)
            } else {
                println("ERROR: USERNAME ARG NOT FOUND")
            }
        }
        }

    }

// ------------PREVIEWS-------------//

//@Preview(showBackground = true)
@Composable
fun LoggedInPage(navController: NavHostController, username: String) {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            LoggedInHeader()
            Text(text = "Logged in as: $username")
            Column (modifier = Modifier
                .fillMaxHeight()
                .padding(30.dp),
                verticalArrangement = Arrangement.Bottom){
                BackButton(navController)
            }
        }
    }
}

// @Preview(showBackground = true)
@Composable
fun HomePage(navController: NavHostController) {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
    Column {
        HomePageHeader()
        ButtonRow(navController)
    }
    }
}

// @Preview(showBackground = true)
@Composable
fun AboutPage(navController: NavHostController) {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
    Column {
        AboutPageHeader()
        AboutText()

        Column (modifier = Modifier
            .fillMaxHeight()
            .padding(30.dp),
            verticalArrangement = Arrangement.Bottom
        ){
            BackButton(navController)
        }
    }
    }
}

//@Preview(showBackground = true)
@Composable
fun SignInPage(navController: NavHostController) {
    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            SignInPageHeader()
            SignInForm(navController)
            BackButton(navController)
        }
    }
}

// -------------Composable--------------//

//@Preview(showBackground = true)
@Composable
fun SignInForm(navController: NavHostController) {

    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    val accountUsernames = mutableListOf<String>("Clemond", "Rabbit")
    val accountPasswords = mutableListOf<String>("123", "Rabbit99")

    Box(modifier = Modifier
        .padding(0.dp, 20.dp)
        //.background(color = Color.Gray)
        .fillMaxWidth()
    ){
        Box(modifier = Modifier
            .background(color = Color.LightGray)
            .border(border = BorderStroke(width = 1.dp, Color.DarkGray))
            .padding(25.dp)
            .align(Alignment.Center)
        ){
            Column (modifier = Modifier
            ){
                OutlinedTextField(
                    value = username,
                    onValueChange = {username = it},
                    label =  { Text(text = "Username")}
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it}, 
                    label = { Text(text = "Password")},
                    visualTransformation =  PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),)

                // TextFieldUsername()
                // TextFieldPassword()

                Button(onClick = {
                    if (username.text == accountUsernames[0] && password.text == accountPasswords[0]) {
                        navController.navigate("loggedInPage/${username.text}")
                    }
                }) {
                    Text(text = "Sign in")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun AboutText() {

    Box(modifier = Modifier
        //.background(color = Color.LightGray)
        .fillMaxWidth()
        .padding(15.dp)
        .height(210.dp)

    ){
        Box(modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize()
            .border(border = BorderStroke(width = 1.dp, Color.DarkGray))
            .padding(8.dp)
        ){
            Text(text = "This is an app dedicated to everyone that loves to cook food. On this app you can explore, find, save and share new recipes, keep up with your favorite chefs, follow new food trends and much more." +
                    "You will also be able to write down and save your own recipes, like having your own cook book or food journal.",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LoggedInHeader() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(color = Color.Cyan)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(
            id = R.drawable.bread),
            contentDescription = "Header_Img",
            modifier = Modifier
                .size(80.dp)
        )
        Text(text = "Welcome", fontSize = 20.sp)
    }
}

@Composable
fun SignInPageHeader() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(color = Color.Cyan)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(
            id = R.drawable.pizza),
            contentDescription = "Header_Img",
            modifier = Modifier
                .size(80.dp)
        )
        Text(text = "Welcome to the sign in page", fontSize = 20.sp)
    }
}

//@Preview(showBackground = true)
@Composable
fun AboutPageHeader() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(color = Color.Cyan)
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(
            id = R.drawable.soda),
            contentDescription = "Header_Img",
            modifier = Modifier
                .size(80.dp)
        )
        Text(text = "Welcome to the about page", fontSize = 20.sp)
    }
}

//@Preview(showBackground = true)
@Composable
fun HomePageHeader() {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(color = Color.Cyan)
                .padding(30.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(
                    id = R.drawable.taco),
                    contentDescription = "Header_Img",
                    modifier = Modifier
                        .size(80.dp)
                )
            Text(text = "Welcome to Recipes 4 the people", fontSize = 20.sp)
        }
}

//@Preview(showBackground = true)
@Composable
fun BackButton(navController: NavHostController) {
    Row (modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ){
        FilledTonalButton(onClick = {
            navController.navigate("homePage")
        }) {
            Text(text = "Back To Home Page")
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun SignInButton(navController: NavHostController) {

    FilledTonalButton(
        onClick = {
            navController.navigate("signInPage")
        },
        modifier = Modifier
            .width(140.dp)
            .height(50.dp)
    ) {
        Text(text = "Sign In")
    }
}

//@Preview(showBackground = true)
@Composable
fun AboutButton(navController: NavHostController) {
    FilledTonalButton(
        onClick = {
                  navController.navigate("aboutPage")
        },
        modifier = Modifier
            .width(140.dp)
            .height(50.dp)
    ) {
        Text(text = "About")
    }
}

//@Preview(showBackground = true)
@Composable
fun ButtonRow(navController: NavHostController) {
    Row (
        modifier = Modifier
            .border(border = BorderStroke(width = 2.dp, Color.DarkGray))
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
    ){
        AboutButton(navController)
        SignInButton(navController)
    }
}