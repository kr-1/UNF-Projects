//////////////////////////
// Riley Kollman        //
// N01094067            //
// Assignment 2         //
// Last updated: 9/8/18 //
//////////////////////////

#include "my.h"

int sort(int* arr, int s)
{
    int j;
    int temp;
    int checking = 1;

    while(checking == 1)       //Sort loop
    {
        checking = 0;
        for(j=0; j<s; j=j+2)  //odd indexes
        {

            if(arr[j+1]<arr[j])
            {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                checking = 1;
            }
        }

        for(j=1; j<s; j=j+2)  //even indexes
        {
            if(arr[j+1]<arr[j])
            {
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                checking = 1;
            }
        }

    }
    printf("Array is sorted.\n\n");  //sort complete
}
