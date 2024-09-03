package com.GameZone.WordSearchRestAPI;

import java.util.List;

public interface WordGridInter {

    public void display_grid(int grid_size,char wordgrid[][]);
    public void set_word(int grid_size, char wordgrid[][], List<String> words);

}
