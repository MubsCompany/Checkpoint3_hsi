package com.mubs.checkpoint3_hsi

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubs.checkpoint3_hsi.model.Person
import com.mubs.checkpoint3_hsi.model.PersonDataSource
import com.mubs.checkpoint3_hsi.ui.theme.Checkpoint3_hsiTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.remember


@Composable
    fun PersonListItem(
        person: Person,
        modifier: Modifier = Modifier
    ) {
        Card (
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = modifier
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 8.dp)
                    .sizeIn(minHeight = 52.dp)
            ){
                Image(
                    painter = painterResource(id = person.personProfileImageRes),
                    contentDescription = "a",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(end = 16.dp)
                )
                Column (
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Row {
                        Text(
                            text = stringResource(id = person.personNameRes),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Text(
                            text = stringResource(id = person.personCodeRes),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row {
                        Text(text = stringResource(id = person.personAgeRes))
                        Text(
                            text = stringResource(R.string.strip),
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        Text(text = stringResource(id = person.personHometownRes))
                    }
                }
                Icon(Icons.Rounded.KeyboardArrowRight, contentDescription = null)
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun PersonList(
        persons: List<Person>,
        modifier: Modifier = Modifier,
        contentPadding: PaddingValues = PaddingValues(0.dp)
    ) {

        val visibleState = remember {
            MutableTransitionState(false).apply {
                targetState = true
            }
        }

        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
            ),
            exit = fadeOut(),
            modifier = modifier
        ) {
            LazyColumn(
                contentPadding = contentPadding
            ) {
                itemsIndexed(persons) {index, item ->
                    PersonListItem(
                        person = item,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessVeryLow,
                                        dampingRatio = Spring.DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = { it * (index + 1) } // staggered entrance
                                )
                            )
                    )
                }
            }
        }
        
    }

    @Composable
    fun HeaderPersonScreen() {
        Column (
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.pencarian_calon_pasangan),
                    fontWeight = FontWeight.Bold
                )
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = stringResource(R.string.menu))
            }
            Text(text = stringResource(R.string.profil_profil_berikut_adalah_para_peserta_yang_telah_disesuaikan_dengan_preferensimu))
        }
    }

    @Preview
    @Composable
    fun HeaderPersonPreview() {
        HeaderPersonScreen()
    }


    @Preview
    @Composable
    fun PersonListPreview() {
        Surface {
            PersonList(persons = PersonDataSource.personList)
        }
    }

@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun PersonListItemPreview() {
        val person = Person (
        personNameRes = R.string.person1_name,
        personCodeRes = R.string.person1_code,
        personAgeRes = R.string.person1_age,
        personHometownRes = R.string.person1_hometown,
        personProfileImageRes = R.drawable.letter_a
        )
    Checkpoint3_hsiTheme {
        PersonListItem(
            person
        )
    }
    }