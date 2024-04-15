package com.example.scrollablelist.ui.views

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelist.R
import com.example.scrollablelist.viewmodel.ContactListViewModel

@Composable
fun InsertContact(
    viewModel: ContactListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.insertContactUiState.collectAsState()
    BackHandler {
        viewModel.navigateBack(navController = navController)
    }
    InsertForm(
        picture = uiState.picture,
        name = uiState.name,
        status = uiState.status,
        onUpdatePicture = viewModel::updatePicture,
        onUpdateName = viewModel::updateName,
        onUpdateStatus = viewModel::updateStatus,
        modifier = Modifier,
    )
}

@Composable
fun InsertForm(
    @DrawableRes picture: Int,
    name: String,
    status: String,
    onUpdatePicture: (Int) -> Unit,
    onUpdateStatus: (String) -> Unit,
    onUpdateName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        R.drawable.baseline_face_24,
        R.drawable.baseline_face_2_24,
        R.drawable.baseline_face_3_24,
        R.drawable.baseline_face_4_24,
        R.drawable.baseline_face_5_24,
        R.drawable.baseline_face_6_24
    )
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth()
        ) {
            items(imageList) { image ->
                Box(modifier = modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .background(if (image == picture) Color.LightGray else Color.Transparent)
                ){
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clickable {
                                onUpdatePicture(image)
                            }
                    )
                }
            }
        }

        Spacer(modifier = modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = onUpdateName,
            label = { Text("Nome") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = status,
            onValueChange = onUpdateStatus,
            label = { Text("Status") },
            singleLine = false,
            minLines = 1,
            maxLines = 3,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InsertFormPreview() {
    InsertForm(
        picture = R.drawable.baseline_face_24,
        name = "",
        status = "",
        onUpdatePicture = {},
        onUpdateStatus = {},
        onUpdateName ={},
    )
}