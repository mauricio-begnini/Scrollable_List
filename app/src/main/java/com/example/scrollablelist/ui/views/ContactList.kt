package com.example.scrollablelist.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scrollablelist.R
import com.example.scrollablelist.models.Contact
import com.example.scrollablelist.ui.theme.ScrollableListTheme
import com.example.scrollablelist.viewmodel.ContactListViewModel

@Composable
fun ContactList(viewModel: ContactListViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn {
        items(uiState.contactList) { contact ->
            ContactCard(contact = contact, onDelete = viewModel::deleteContact)
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact,
    onDelete: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
    )
    {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(50.dp),
                painter = painterResource(id = contact.picture),
                contentDescription = null,
            )
            Column(modifier = modifier.padding(top = 6.dp, bottom = 6.dp)) {
                Text(text = contact.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(
                    modifier = modifier.width(260.dp),
                    text = contact.status,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black,
                    maxLines = 2
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "delete",
                modifier = modifier.clickable {
                    onDelete(contact)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ScrollableListTheme {
        ContactList(viewModel())
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ScrollableListTheme {
        ContactCard(
            contact = Contact(
                R.drawable.baseline_face_24,
                "Albus Dumbledore",
                "Happiness can be found, even in the darkest of times."
            ),
            {}
        )
    }
}