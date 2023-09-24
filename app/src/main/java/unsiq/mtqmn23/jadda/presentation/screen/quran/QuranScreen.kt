import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import unsiq.mtqmn23.jadda.R
import unsiq.mtqmn23.jadda.presentation.screen.quran.QuranViewModel
import unsiq.mtqmn23.jadda.presentation.ui.theme.Gray
import unsiq.mtqmn23.jadda.presentation.ui.theme.JaddaTheme

@Composable
fun QuranScreen(
    snackbarHostState: SnackbarHostState,
    navigateToDetail: (Int?) -> Unit,
    viewModel: QuranViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.apply {
            setStatusBarColor(color = Color.White)
            setNavigationBarColor(color = Color.White)
        }
    }

    state.statusMessage?.let {
        LaunchedEffect(it) {
            snackbarHostState.showSnackbar(it)
        }
    }

    Column(Modifier.fillMaxSize()) {
        ExpandableSearchView(
            searchDisplay = "",
            onSearchDisplayChanged = {},
            onSearchDisplayClosed = {}
        )
        if (state.isLoading) {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyColumn {
                itemsIndexed(items = state.surah, key = { _, item -> item.id ?: 0 }) { index, item ->
                    Surah(
                        title = item.name.toString(),
                        number = index + 1,
                        shortName = item.shortName.toString(),
                        description = "${item.revelation} \u2022 ${item.translation}",
                        modifier = Modifier.clickable {
                            navigateToDetail(item.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = Color.Black
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }


    Crossfade(targetState = expanded, label = "") { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )

            false -> CollapsedSearchView(
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint
            )
        }
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "search icon",
        tint = iconTint
    )
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Al-Qur'an",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            IconButton(onClick = { onExpandedChanged(true) }) {
                SearchIcon(iconTint = tint)
            }
        }
        Divider(color = Gray.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onExpandedChanged(false)
                onSearchDisplayClosed()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back icon",
                    tint = tint
                )
            }
            TextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    onSearchDisplayChanged(it.text)
                },
                trailingIcon = {
                    SearchIcon(iconTint = tint)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(textFieldFocusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
        Divider(color = Gray.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@Composable
fun Surah(
    title: String,
    number: Int,
    shortName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Box(
       modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ic_surah_number),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .align(Alignment.Center)
                    )
                    Text(
                        text = "$number",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Column {
                Text(
                    text = shortName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    JaddaTheme {
        Surface(
            color = Color.White,
        ) {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {}
            )
        }
    }
}

@Preview
@Composable
fun ExpandedSearchViewPreview() {
    JaddaTheme {
        Surface(
            color = Color.White,
        ) {
            ExpandableSearchView(
                searchDisplay = "",
                onSearchDisplayChanged = {},
                expandedInitially = true,
                onSearchDisplayClosed = {}
            )
        }
    }
}

@Preview
@Composable
fun SurahPreview() {
    JaddaTheme {
        Surface(
            color = Color.White
        ) {

        }
    }
}
