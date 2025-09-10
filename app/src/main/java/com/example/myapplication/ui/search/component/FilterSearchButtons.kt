package com.example.myapplication.ui.search.component

import android.graphics.Color
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.R
import com.example.myapplication.ui.search.SearchViewModel


@Composable
fun SegmentedButtonMultiSelectSample(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val checkedList = remember { mutableStateListOf<Int>() }
    val checkedList2 = remember { mutableStateListOf<Int>() }
    val options = listOf("Action", "Adventure", "Comedy", "Drama")
    val options2 = listOf("Romance", "Music", "Family")

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MultiChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    icon = {
                        Icon(active = index in checkedList) {
                            Icon(
                                imageVector = Icons.Default.CheckBoxOutlineBlank,
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                            )
                        }
                    },

                    onCheckedChange = {
                        if (index in checkedList) {
                            checkedList.remove(index)
                            viewModel.filteredMoviesList.remove(label)
                            viewModel.resetSearch()
                            if (
                                viewModel.filteredMoviesList.isEmpty()
                            ) {
                                viewModel.resetSearch()
                            }
                            viewModel.filterSearch(
                                viewModel.filteredMoviesList
                            )
                        } else {
                            checkedList.add(index)
                            viewModel.filteredMoviesList.add(label)
                            viewModel.filterSearch()
                        }
                    },
                    checked = index in checkedList,

                    ) {
                    Text(label)

                }
            }
        }
        MultiChoiceSegmentedButtonRow {
            options2.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options2.size),
                    icon = {
                        Icon(active = index in checkedList2) {
                            Icon(
                                imageVector = Icons.Default.CheckBoxOutlineBlank,
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                            )
                        }
                    },

                    onCheckedChange = {
                        if (index in checkedList2) {
                            checkedList2.remove(index)
                            viewModel.filteredMoviesList.remove(label)
                            viewModel.resetSearch()
                            if (
                                viewModel.filteredMoviesList.isEmpty()
                            ) {
                                viewModel.resetSearch()
                            }
                            viewModel.filterSearch(
                                viewModel.filteredMoviesList
                            )
                        } else {
                            checkedList2.add(index)
                            viewModel.filteredMoviesList.add(label)
                            viewModel.filterSearch()
                        }
                    },
                    checked = index in checkedList2,
                ) {
                    Text(label)
                }
            }
        }
    }
}


@Composable
@Preview()
fun SortSearchResult(
    viewModel: SearchViewModel = hiltViewModel()

) {


    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Icon(
            imageVector = Icons.Filled.ArrowUpward,
            contentDescription = "Sort Descending",
            Modifier
                .clickable(
                    onClick = {
                        viewModel.sortDescending()

                    }
                )
                .size(30.dp),
            tint = colorResource(R.color.Red)
        )

        Spacer(modifier = Modifier.padding(end = 2.dp))


        Icon(
            imageVector = Icons.Default.ArrowDownward,
            contentDescription = "Sort Ascending",
            Modifier
                .clickable(
                    onClick = {
                        viewModel.sortAscending()



                    }
                )
                .size(30.dp),
            tint = colorResource(R.color.blue)

        )
        Spacer(modifier = Modifier.padding(end = 4.dp))

    }


}


