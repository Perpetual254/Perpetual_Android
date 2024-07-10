package net.ezra.ui.logout





import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_ADD_PRODUCT
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_LOGOUT
import net.ezra.navigation.ROUTE_REGISTER
import net.ezra.navigation.ROUTE_SEARCH
import net.ezra.navigation.ROUTE_SIGNUP
import net.ezra.navigation.ROUTE_VIEW_PROD
import net.ezra.navigation.ROUTE_VIEW_STUDENTS


data class Screen(val title: String, val icon: Int)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun logout(navController: NavController,){

//    var new by remember {
//        mutableStateOf("")
//    }
//
//    var me by remember {
//        mutableStateOf("")
//    }
//
//    var cone = LocalContext.current
//
//    LazyColumn (
//        modifier = Modifier
//          //  .padding(start = 30.dp)
//            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    // Applies the vertical gradient brush
//                    colors = listOf(Color(0xffF9A825), Color(0XFFFF4C7C)), // List of colors
//
//                )
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//
//        item {
//
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//
//              Image(
//                  modifier = Modifier
//                      .size(200.dp)
//                      .align(Alignment.CenterHorizontally)
//                  ,
//                  painter = painterResource(id = R.drawable.cross),
//                  contentDescription = ""
//              )
//
//              Spacer(modifier = Modifier.height(10.dp))
//
//              OutlinedTextField(
//                  shape = RoundedCornerShape(30.dp),
//                  leadingIcon = {
//                      androidx.compose.material.Icon(
//                          imageVector = Icons.Default.Email,
//                          contentDescription = "mail icon"
//                      )
//                  },
//                  value = new,
//                  onValueChange = {new = it},
//                  label = {Text("Email") },
//                  modifier = Modifier
//                      .fillMaxWidth()
//
//              )
//
//              Spacer(modifier = Modifier.height(20.dp))
//
//              OutlinedTextField(
//                    shape = RoundedCornerShape(30.dp),
//                    leadingIcon = {
//                        androidx.compose.material.Icon(
//                            imageVector = Icons.Default.Lock,
//                            contentDescription = "mail icon"
//                        )
//                    },
//                    value = me,
//                    onValueChange = {me = it},
//                    label = { Text("Password") },
//                    modifier = Modifier
//                      .fillMaxWidth()
//
//              )
//
//              Spacer(modifier = Modifier.height(20.dp))
//
//
//              Button(onClick =
//              { navController.navigate(ROUTE_HOME) },
//              shape = RoundedCornerShape(15.dp),
//              colors = ButtonDefaults.buttonColors(Color.White),
//              modifier = Modifier
//                  .width(150.dp)
//                  .align(Alignment.CenterHorizontally)
//              ) {
//                  Text(
//                      text = "LOG OUT",
//                      color = Color.Red,
//                  )
//              }
//
//
//
//
//            }
//
//        }
//
//    }
//
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xffF9A825), Color(0XFFFF4C7C))
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.cross),
                    contentDescription = "Cross Icon",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon"
                        )
                    },
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon"
                        )
                    },
                  //  visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        // Perform logout logic here
                        navController.navigate(ROUTE_HOME)
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                        .width(150.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "LOG OUT",
                        color = Color.Red,
                    )
                }
            }
        }
    }

}




