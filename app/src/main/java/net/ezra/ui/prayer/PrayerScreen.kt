package net.ezra.ui.prayer


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

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.ezra.R
import net.ezra.navigation.ROUTE_ABOUT
import net.ezra.navigation.ROUTE_ADD_PRODUCT
import net.ezra.navigation.ROUTE_ADD_STUDENTS
import net.ezra.navigation.ROUTE_DASHBOARD
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_SEARCH
import net.ezra.navigation.ROUTE_VIEW_PROD
import net.ezra.navigation.ROUTE_VIEW_STUDENTS

@Composable
fun Prayer(navController: NavHostController,){


    Box(
        modifier = Modifier
            .fillMaxSize()
          //  .verticalScroll(rememberScrollState())
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.cross),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable {
                        navController.navigate(ROUTE_VIEW_PROD)
                    }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Morning Prayers",
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                           // modifier = Modifier.padding(bottom 8.dp)
                        )
                        Text(
                            text = "An early-morning prayer is a blessing for the whole day.",
                            fontStyle = FontStyle.Italic,
                            fontSize = 10.sp,
                           // modifier = Modifier.padding(bottom: 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.sun),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp) // Adjust size as needed
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable {
                        navController.navigate(ROUTE_VIEW_PROD)

                    }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Meal Prayers",
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            // modifier = Modifier.padding(bottom 8.dp)
                        )
                        Text(
                            text = "One cannot think well, love well, sleep well, if one has not dined well.",
                            fontStyle = FontStyle.Italic,
                            fontSize = 10.sp,
                            // modifier = Modifier.padding(bottom: 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.meals),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp) // Adjust size as needed
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable {
                        navController.navigate(ROUTE_VIEW_PROD)

                    }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Night Prayers",
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            // modifier = Modifier.padding(bottom 8.dp)
                        )
                        Text(
                            text = "As the night gets dark, let your worries fade. ...\n" +
                                    "Life always offers you a second chance..",
                            fontStyle = FontStyle.Italic,
                            fontSize = 10.sp,
                            // modifier = Modifier.padding(bottom: 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.night),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp) // Adjust size as needed
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable {
                        navController.navigate(ROUTE_VIEW_PROD)

                    }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Rozary",
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            // modifier = Modifier.padding(bottom 8.dp)
                        )
                        Text(
                            text = "In these dark days, we must not be afraid. Like our forebears in faith,we must again .\n" +
                                    " turn again to the rozary calling on the Immaculate Virgin to come to our assistance and put our enemies to flight.",
                            fontStyle = FontStyle.Italic,
                            fontSize = 10.sp,
                            // modifier = Modifier.padding(bottom: 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.rozary),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp) // Adjust size as needed
                    )
                }
            }


        }
    }



}
