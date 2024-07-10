package net.ezra.ui.products



import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import net.ezra.R
import net.ezra.ui.prayer.Prayer

class PrayersViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    val  productState = MutableLiveData<Product>()

    fun getPrayer(prayerId: String) {
        viewModelScope.launch {
            val document = firestore.collection("prayers").document(prayerId).get().await()
            val product= document.toObject(Product::class.java) ?: return@launch
            productState.value = product
        }
    }

    fun updatePrayer(product: Product) {
        firestore.collection("prayers").document(product.id).set(product)
    }


}

@Composable
fun updatingScreen(navController: NavController, productId: String, PrayersViewModel: PrayersViewModel){


    var updatedProductName by remember { mutableStateOf("") }
    var updatedProductDescription by remember { mutableStateOf("") }
    var updatedProductPrice by remember { mutableStateOf("") }
    var updatedProductImageUri by remember { mutableStateOf<Uri?>(null) }

    val productState by PrayersViewModel. productState.observeAsState()
    val product = productState ?: Product()

    // Initialize values only once
    LaunchedEffect(product) {
        if (product != null) {
            updatedProductName = product.name
            updatedProductDescription = product.description
            updatedProductPrice = product.price.toString()
            updatedProductImageUri = Uri.parse(product.imageUrl)
        }
    }
//    // Initialize values only once
//    LaunchedEffect(product)
//    {
//        updatedProductName = product.name
//        updatedProductDescription = product.description
//        updatedProductPrice = product.price.toString()
//        updatedProductImageUri = Uri.parse(product.imageUrl)
//    }

//
//    updatedProductName = product.name
//    updatedProductDescription = product.description
//    updatedProductPrice = product.price.toString()
//    updatedProductImageUri = Uri.parse(product.imageUrl)

    val storage = FirebaseStorage.getInstance()

    fun uploadImageToStorage(productId: String, imageUri: Uri?, onSuccess: (String) -> Unit) {
        if (imageUri != null) {
            val storageRef = storage.reference.child("product_images").child("$productId.jpg")
            val uploadTask = storageRef.putFile(imageUri)
            uploadTask.addOnSuccessListener { taskSnapshot ->
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    onSuccess(uri.toString())
                }
            }
        }
    }

    val context = LocalContext.current
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        updatedProductImageUri = uri
    }



    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .height(750.dp)
            .padding(16.dp)
            .background(
                Brush.verticalGradient(
                    // Applies the vertical gradient brush
                    colors = listOf(Color(0xffF9A825), Color(0XFFFF4C7C)), // List of colors
                    // End position of the gradient (bottom)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        
    ) {
        Text(text = "Edit Prayer", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(16.dp))

        updatedProductImageUri?.let { uri ->
            Image(
                painter = painterResource(id = R.drawable.pray), // Placeholder image
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(30.dp),
            value = updatedProductName,
            onValueChange = { updatedProductName = it },
            label = { Text("Prayer Name") },
           // label = { Text(updatedProductName.takeUnless { it.isBlank() } ?: "Prayer Name") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(30.dp),
            value = updatedProductDescription,
            onValueChange = { updatedProductDescription = it },
            label = { Text("Prayer Description") },
          //  label = { Text(updatedProductDescription.takeUnless { it.isBlank() } ?: "Prayer Description") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))

//        OutlinedTextField(
//            shape = RoundedCornerShape(30.dp),
//            value = updatedProductPrice,
//            onValueChange = { updatedProductPrice = it },
//            label = { Text("Prayer Service Price") },
//           // label = { Text(updatedProductPrice.takeUnless { it.isBlank() } ?: "Prayer Service Price") },
//            modifier = Modifier
//                .fillMaxWidth()
//        )
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            getContent.launch("image/*") },
            shape = RoundedCornerShape(15.dp),
           // colors = ButtonDefaults.buttonColors(Color.White)

        ) {
            Text("Select Image")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedProduct = Product(
                id = productId,
                name = updatedProductName,
                description = updatedProductDescription,
               // price = updatedProductPrice.toDouble(),
                imageUrl = ""
            )

            if (updatedProductImageUri != null) {
                uploadImageToStorage(productId, updatedProductImageUri) { imageUrl ->
                    updatedProduct.imageUrl = imageUrl
                    PrayersViewModel.updatePrayer(updatedProduct)
                    navController.popBackStack()
                }
            } else {
                PrayersViewModel.updatePrayer(updatedProduct)
                navController.popBackStack()
            }
        },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.align(Alignment.End)) {
            Text("Save")
        }
    }
}




