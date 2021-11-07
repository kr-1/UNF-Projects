//////////////////////////
//Riley Kollman         //
//n01094067             //
//Assignment 3          //
//Last updated: 9/23/18 //
//////////////////////////
import java.util.*;
import java.lang.*;

public class n01094067
{
   public static void main (String args[])
   {
      int people;
      int start;
      int count;
      int running = 1;
      
      Scanner scan = new Scanner(System.in);
      
      while (running == 1)
      {
      System.out.println("Enter the number of people in the circle, the number of the person who will start off, and the countoff number, in the format \"# # #\"");
      String input = scan.nextLine();
      
      if(input.equals("stop"))
      {
         break;
      }
      
      String[] parts = input.split(" ");
      
      people = Integer.parseInt(parts[0]);
            
      start = Integer.parseInt(parts[1]);

      count = Integer.parseInt(parts[2]);
      
      LinkList list = new LinkList();
      
      for(int i = 1; i<=people; i++)
      {
         list.insert(i);
      }
      
      list.shift(start - 1);
      
      while(people>1)
      {
         list.shift(count);
         list.delete();
         
         people--;
      }
      list.displayList();
   }
   }
}

class Link
{
   public int data;
   public Link next;
   
   public Link(int id)
   {
      data = id;
   }
   
   public void displayLink()
   {
      System.out.println("{" + data +"} ");
   }
}

class LinkList
{
   private Link first;
   private Link current;
   private int size = 0;
   
   public LinkList()
   {
      first = null;
   }
   
   public boolean isEmpty()
   {
      return (first==null);
   }
   
   public void insert(int data)
   {      
      Link newLink = new Link(data);
      if(first == null)
      {
         first = newLink;
      }
      else
      {
         current.next = newLink;
      }
      current = newLink;
      newLink.next = first;
      size++;
   }
   
   public void delete()
   {
      if(current != first)
      {
         current.next = first.next;
         first = first.next;
         size--;
      }
   }
   
   public void shift(int count)
   {
      for(int i=0; i<count; i++)
      {
         first = first.next;
         current = current.next;
      }
   }
   
   public void displayList()
   {
      Link current = first;
      for(int i=1; i<=size; i++)
      {
         current.displayLink();
         current = current.next;
      }
      System.out.println("");
   }
}