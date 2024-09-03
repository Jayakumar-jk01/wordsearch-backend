package com.GameZone.WordSearchRestAPI;

import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class WordGrid implements WordGridInter {



    private class Coordinates{
        int x;
        int y;

        public Coordinates(int x,int y)
        {
            this.x=x;
            this.y=y;
        }
    }




    @Override
    public void display_grid(int grid_size,char wordgrid[][])
    {
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++)
            {
                System.out.print(wordgrid[i][j]+" ");
            }

            System.out.println("");
        }
    }

    @Override
    public void set_word(int grid_size,char wordgrid[][],List<String> words)
    {

        List<Coordinates> coordinates;
        List<Integer> directions;
        coordinates=new ArrayList<>();
        directions=Arrays.asList(0,1,2);
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++)
            {
                wordgrid[i][j]='_';
                coordinates.add(new Coordinates(i,j));

            }
        }
        Collections.shuffle(coordinates);

        for(String word:words)
        {
            Collections.shuffle(directions);//horiz(0) verticla(1) diagonal(2)
            for(Coordinates coordinate:coordinates)
            {

                int direction=get_possible_direction(grid_size,wordgrid,word,directions,coordinate);

                int row=coordinate.x;
                int col=coordinate.y;

                if(direction!=-1)
                {
                    switch(direction)
                    {
                        case 0:
                            for(char ch:word.toCharArray())
                            {
                                wordgrid[row][col++]=ch;
                            }
                            break;
                        case 1:
                            for(char ch:word.toCharArray())
                            {
                                wordgrid[row++][col]=ch;
                            }
                            break;
                        case 2:
                            for(char ch:word.toCharArray())
                            {
                                wordgrid[row++][col++]=ch;
                            }
                            break;


                    }
                    break;
                }





            }

        }

        fill_Empty_spaces(grid_size, wordgrid);
    }

    private void fill_Empty_spaces(int grid_size,char[][] wordgrid) {
        List<Character> alphabets;
        alphabets=new ArrayList<>();
        for(int i=0;i<26;i++)
        {
            char ch=(char)('a'+i);
            alphabets.add(ch);
        }
        for(int i=0;i<grid_size;i++)
        {
            for(int j=0;j<grid_size;j++)
            {
                if(wordgrid[i][j]=='_')
                {
                    Collections.shuffle(alphabets);
                    wordgrid[i][j]=alphabets.get(i);

                }
            }
        }
    }

    private int get_possible_direction(int grid_size,char[][] wordgrid,String word, List<Integer> directions,Coordinates coordinate) {
        int row=coordinate.x;
        int col=coordinate.y;
        for(Integer x:directions)
        {
            switch(x)
            {
                case 0:
                    if(col+word.length()<grid_size)
                    {
                        int flag=1;
                        for(int i=0;i<word.length();i++)
                        {
                            if(wordgrid[row][col+i]!='_')
                            {
                                flag=0;
                                break;
                            }
                        }
                        if(flag==1)return 0;
                    }
                    break;

                case 1:
                    if(row+word.length()<grid_size)
                    {
                        int flag=1;
                        for(int i=0;i<word.length();i++)
                        {
                            if(wordgrid[row+i][col]!='_')
                            {
                                flag=0;
                                break;
                            }
                        }
                        if(flag==1)return 1;
                    }
                    break;


                case 2:
                    if(row+word.length()<grid_size && col+word.length()<grid_size)
                    {
                        int flag=1;
                        for(int i=0;i<word.length();i++)
                        {
                            if(wordgrid[row+i][col+i]!='_')
                            {
                                flag=0;
                                break;
                            }
                        }
                        if(flag==1)return 2;
                    }
                    break;



            }
        }

        return -1;
    }


}

