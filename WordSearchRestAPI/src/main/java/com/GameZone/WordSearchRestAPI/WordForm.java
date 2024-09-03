package com.GameZone.WordSearchRestAPI;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WordForm {
    private int grid_size;
    private List<String> words;
}
