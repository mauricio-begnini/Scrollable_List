package com.example.scrollablelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scrollablelist.ui.theme.ScrollableListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollableListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

fun createContactsList(): List<Contact> {
    val pictures = listOf(
        R.drawable.baseline_face_24,
        R.drawable.baseline_face_2_24,
        R.drawable.baseline_face_3_24,
        R.drawable.baseline_face_4_24,
        R.drawable.baseline_face_5_24,
        R.drawable.baseline_face_6_24
    )

    val characters = listOf(
        "Yoda" to "May the Force be with you.",
        "Luke Skywalker" to "I am a Jedi, like my father before me.",
        "Darth Vader" to "I am your father.",
        "Princess Leia" to "Help me, Obi-Wan Kenobi. You're my only hope.",
        "Han Solo" to "I've got a bad feeling about this.",
        "Harry Potter" to "I solemnly swear that I am up to no good.",
        "Hermione Granger" to "It's leviOsa, not levioSA!",
        "Ron Weasley" to "Bloody hell!",
        "Albus Dumbledore" to "Happiness can be found, even in the darkest of times.",
        "Severus Snape" to "Always.",
        "Tony Stark" to "I am Iron Man.",
        "Thor" to "I'm not a queen or a monster. I'm the Goddess of Death.",
        "Captain America" to "I can do this all day.",
        "Black Widow" to "I'm always picking up after you boys.",
        "Spider-Man" to "With great power comes great responsibility.",
        "Batman" to "I'm Batman.",
        "Superman" to "Truth, justice, and the American way.",
        "Wonder Woman" to "I believe in love.",
        "The Joker" to "Why so serious?",
        "Sherlock Holmes" to "Elementary, my dear Watson.",
        "Gandalf" to "A wizard is never late, nor is he early.",
        "Frodo Baggins" to "I wish the ring had never come to me.",
        "Aragorn" to "For Frodo.",
        "Legolas" to "They're taking the hobbits to Isengard!",
        "Gimli" to "Nobody tosses a dwarf!",
        "Saruman" to "Against the power of Mordor, there can be no victory.",
        "Samwise Gamgee" to "I can't carry it for you, but I can carry you!",
        "Gollum" to "My precious.",
        "Bilbo Baggins" to "I'm going on an adventure!",
        "Elrond" to "I was there the day the strength of Men failed.",
        "Galadriel" to "Even the smallest person can change the course of the future.",
        "Sauron" to "You cannot hide. I see you.",
        "Thranduil" to "Do not speak to me of dragon fire. I know its wrath and ruin.",
        "Boromir" to "One does not simply walk into Mordor.",
        "Faramir" to "I do not seek to command the loyalty that my brother won.",
        "Eowyn" to "I am no man.",
        "King Theoden" to "So it begins.",
        "Aragorn (as King)" to "You bow to no one.",
        "Tauriel" to "If this is love, I do not want it.",
        "Kili" to "I will see you again.",
        "Gandalf (to Bilbo)" to "I am looking for someone to share in an adventure.",
        "Lurtz" to "Find the Halflings!",
        "Gríma Wormtongue" to "He must be dead by now.",
        "Denethor" to "You think you are wise, Mithrandir.",
        "Haldir" to "The world is changing.",
        "Éomer" to "Riders of Rohan! Oaths you have taken, now fulfill them all, to lord and land!"
    )

    val contacts = characters.mapIndexed { index, (name, status) ->
        Contact(picture = pictures[index % pictures.size], name = name, status = status)
    }

    return contacts
}

@Composable
fun App() {
    val contacts by remember {
        mutableStateOf(createContactsList())
    }
    ContactList(contactList = contacts)
}

@Composable
fun ContactList(contactList: List<Contact>, modifier: Modifier = Modifier) {
    LazyColumn{
        items(contactList){contact ->
            ContactCard(contact = contact)
        }
    }
}

@Composable
fun ContactCard(
    contact: Contact, 
    modifier: Modifier = Modifier
) {
    Card (modifier = modifier
        .padding(2.dp)
        .fillMaxWidth())
    {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .size(50.dp),
                painter = painterResource(id = contact.picture),
                contentDescription = null,
            )
            Column (modifier = modifier.padding(top = 6.dp, bottom = 6.dp)){
                Text(text = contact.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = contact.status, fontStyle = FontStyle.Italic, fontSize = 12.sp, color = Color.Black)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    ScrollableListTheme {
        ContactList(contactList = createContactsList())
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview() {
    ScrollableListTheme {
        ContactCard(contact = Contact(R.drawable.baseline_face_24, "Albus Dumbledore", "Happiness can be found, even in the darkest of times."))
    }
}