package com.GameZone.WordSearchRestAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class WordSearchController {

    private int grid_size;
    private List<String> words;
    private Test tst;
    private WordGrid wordgrid;

    @Autowired
    public WordSearchController(WordGrid thewordgrid)
    {
        this.wordgrid=thewordgrid;
    }







    @PostMapping("/game")
    public ResponseEntity<String> createUser(@RequestBody WordForm wordform) {

        int grid_size= wordform.getGrid_size();
        List<String> words=wordform.getWords();
        char[][] word_grid=new char[grid_size][grid_size];
        wordgrid.set_word(grid_size,word_grid,words);
        wordgrid.display_grid(grid_size,word_grid);
        StringBuilder gridBuilder = new StringBuilder();

        for(int row = 0 ; row < grid_size; row++){
            for(int col = 0 ; col < grid_size; col++) {
                gridBuilder.append(word_grid[row][col]);
                gridBuilder.append(" ");
            }
            gridBuilder.append("\n");
            }

        return ResponseEntity.ok(gridBuilder.toString());
    }
}
