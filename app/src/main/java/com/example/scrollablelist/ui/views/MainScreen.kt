package com.example.scrollablelist.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scrollablelist.viewmodel.ContactListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel: ContactListViewModel = viewModel()
    val navController = rememberNavController()
    val uiState by viewModel.mainScreenUiState.collectAsState()
    ContactList(viewModel = viewModel, navController = navController)
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = uiState.screenName) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.fabAction(navController) }) {
                Image(painter = painterResource(id = uiState.icon), contentDescription = uiState.iconContentDescription)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.ContactList.name,
            modifier = Modifier.padding(it)
        ){
            composable(route = AppScreens.ContactList.name){
                ContactList(viewModel = viewModel, navController = navController)
            }
            composable(route = AppScreens.InsertContact.name){
                InsertContact(viewModel = viewModel, navController = navController)
            }
        }
    }
}

enum class AppScreens{
    ContactList,
    InsertContact,
}