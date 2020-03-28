#include <iostream>
#include <string>
#include <stdlib.h>

#define grid_size 3
#define win_events 8

using namespace std;

class TicTacToe{
  private:
    //int numslot = 1;
    char blank = '-';
    char grid[grid_size][grid_size];

  public:
    void draw_grid(){
      for(int i = 0; i < grid_size; i++)
      {
        for(int j = 0; j < grid_size; j++)
        {
          //grid[i][j] = to_string(numslot).c_str()[0];
          //numslot++;
          grid[i][j] = blank;
        }
      }
    }

    void show_grid(){
      for(int i = 0; i < grid_size; i++)
      {
        for(int j = 0; j < grid_size; j++)
        {
          printf(" %c |", grid[i][j]);
        }
        printf("\n - - - - - - \n");
      }
    }

    void play(){
      string input;
      while(true){
        puts("Choose a position (1-9): ");
        getline(cin, input);

        if(input != ""){
          char placement = input.c_str()[0];
          if(placement >= '1' && placement <= '9'){
            int placementnum = placement - '0';
            int index = placementnum - 1;

            int row = index / grid_size;
            int col = index % grid_size;

            char move = grid[row][col];
            if(move == 'X' || move == 'O'){
              puts("That position is taken");
            }
            else{
              grid[row][col] = 'X';
              break;
            }
          }
          else{
            puts("You have to choose a position between 1 and 9");
          }
        }
        else{
          puts("You must choose: ");
          getline(cin, input);
        }
      }
    }

    void cpuplay(){
      while(true){
        int cpu_placement = (rand() %9) + 1;
        int row = (cpu_placement - 1) / grid_size;
        int col = (cpu_placement - 1) % grid_size;

        char cpu_pos = grid[row][col];
        if(cpu_pos == 'X' || cpu_pos == 'O'){
          continue;
        }
        else{
          printf("Computer has played at tile %d.\n", cpu_placement);
          grid[row][col] = 'O';
          break;
        }
      }
    }

    void game_check(){
      const char* win_conditions[win_events] =
      {
        "123", "456", "789", "147", "258", "369", "159", "753"
      };
      for(int i = 0; i < win_events; i++){
        bool winner = true;
        char observation = '0';
        const char* won = win_conditions[i];

        for(int j = 0; j < grid_size; j++){
          char win_pos = won[j];
          int entered_pos = win_pos - '0';
          int grid_pos = entered_pos - 1;

          int row = grid_pos / grid_size;
          int col = grid_pos % grid_size;

          char win_grid = grid[row][col];
          if(observation == '0' && win_grid != blank){
            observation = win_grid;
          }
          else if(observation == win_grid){
            continue;
          }
          else{
            winner = false;
            break;
          }
        }
        if(winner){
          puts("We have a winner");
          if(observation == 'X'){
            printf("Hey, you won! Good game.\n");
          }
          else if(observation == 'O'){
            printf("You lost. Better luck next time.\n");
          }
          exit(0);
          break;
        }
      }
    }

    TicTacToe(){
      draw_grid();
      show_grid();
      while(true){
        play();
        show_grid();
        game_check();
        cpuplay();
        show_grid();
        game_check();
      }
    }
};

int main(int argc, char *argv[]){
    TicTacToe game;

}
