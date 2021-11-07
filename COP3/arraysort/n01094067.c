//////////////////////////
// Riley Kollman        //
// N01094067            //
// Assignment 2         //
// Last updated: 9/8/18 //
//////////////////////////

#include "my.h"

int main(void)
{
    int s;
    int a;
    int j;
    int index;
    int running = 1;

    FILE* f;                    //Open file
    f = fopen("test.txt", "r");

    fscanf(f, "%d", &s);        //Determine size of array
    int arr[s];                 //Create array

    for(j=0; j<s; j++)          //Fill array from file
    {
        fscanf(f, "%d", &arr[j]);
    }

    sort(arr, s);               //Call sort function

    while(running == 1)         //Prompt user for index
    {
        printf("Enter index (-1 to quit):\n");
        scanf("%d", &index);

        if(index == -1)         //End program
        {
            running = 0;
        }
        else if(index > s)
        {
            printf("Invalid: index is outside of array range.\n\n");
        }
        else
        {
            printf("Number at index %d is %d\n\n", index, arr[index]);
        }

    }
}
