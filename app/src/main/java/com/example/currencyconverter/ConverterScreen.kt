package com.example.currencyconverter

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.ui.CryptoListPage
import com.example.currencyconverter.ui.CurrencyListPage
import com.example.currencyconverter.ui.MainScreen
import com.example.currencyconverter.ui.MainScreenCrypto
import com.example.currencyconverter.ui.model.CryptosRepository
import com.example.currencyconverter.ui.model.CurrencyRepository
import javax.sql.DataSource

enum class MainScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Currency(title = R.string.app_nameCurrencies),
    MainCrypto(title = R.string.app_nameCrypto),
    Crypto(title = R.string.app_nameCryptos)
}
/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterAppBar(
    currentScreen: MainScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.USD)
                    )
                }
            }
        }
    )
}

@Composable
fun ConverterApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MainScreen.valueOf(
        backStackEntry?.destination?.route ?: MainScreen.Start.name)

    Scaffold(
        topBar = {
            ConverterAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MainScreen.Start.name) {
                MainScreen(
                    onNextButtonClicked = {
                        navController.navigate(MainScreen.Currency.name)
                    },
                    onCryptoButtonClicked = {navController.navigate(MainScreen.MainCrypto.name)}
                )
            }
            composable(route = MainScreen.Currency.name) {
                val context = LocalContext.current
                CurrencyListPage(
                    onNextButtonClicked = { navController.navigate(MainScreen.Start.name) },
                    currencies = CurrencyRepository.currencies
                )
            }
            composable(route = MainScreen.MainCrypto.name) {
                MainScreenCrypto(
                    onNextButtonClicked = { navController.navigate(MainScreen.Crypto.name) },
                    onCryptoButtonClicked = { navController.navigate(MainScreen.Start.name) }
                    )
            }
            composable(route = MainScreen.Crypto.name) {
                val context = LocalContext.current
                CryptoListPage(
                    onNextButtonClicked = { navController.navigate(MainScreen.Start.name) },
                    cryptos = CryptosRepository.cryptos
                )
            }
        }
    }
}
private fun cancelOrderAndNavigateToStart(
    navController: NavHostController
) {
    navController.popBackStack(MainScreen.Start.name, inclusive = false)
}

private fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.USD)
        )
    )
}