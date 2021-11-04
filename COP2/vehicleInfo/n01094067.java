//////////////////////////
//Riley Kollman         //
//n01094067             //
//Assignment 4          //
//Last updated: 3/11/18 //
//////////////////////////

import java.util.*;
import java.io.*;

public class n01094067
{
   public static void main (String args[]) throws FileNotFoundException
   {   
      //create scanner for reading file
      Scanner scan = new Scanner(new File(args[0]));
      
      //create empty array list
      ArrayList<vehicle> v = new ArrayList<vehicle>();
      
      //initialize variables
      String vtype;
      String name;
      String address;
      String phone;
      String email;
      String color;
      String country;
      String date;
      boolean convertible;
      boolean detroit;
      boolean union;
      float importDuty;
      float tons;
      float cost;
      int speeds;
      
//------------------------------------------------------------------------------------------------------------------------------------------------
//READ FROM FILE AND CREATE ARRAY OF VEHICLE OBJECTS

      //read file with scanner
      while(scan.hasNext())
      {
         //scan universal variables
         vtype = scan.nextLine();
         name = scan.nextLine();
         address = scan.nextLine();
         phone = scan.nextLine();
         email = scan.nextLine();
         
         //scan variables for car types
         if(vtype.equals("car") || vtype.equals("american car") || vtype.equals("foreign car"))
         {
            convertible = scan.nextBoolean(); scan.nextLine();
            color = scan.nextLine();
            
            //scan variables for american cars           
            if(vtype.equals("american car"))
            {
               detroit = scan.nextBoolean(); scan.nextLine();
               union = scan.nextBoolean(); /*this part could be problematic*/ if((scan.hasNext()) == true){scan.nextLine();}
               
               //create american car
               vehicle data = new american_car(vtype, name, address, phone, email, convertible, color, detroit, union);
               v.add(data);
            }
            
            //scan variables for foreign cars
            else if(vtype.equals("foreign car"))
            {
               country = scan.nextLine();
               importDuty = scan.nextFloat(); if((scan.hasNext()) == true){scan.nextLine();}
               
               //create foreign car
               vehicle data = new foreign_car(vtype, name, address, phone, email, convertible, color, country, importDuty);
               v.add(data);
            }
            
            
            else if(vtype.equals("car"))
            {
               //create generic car
               vehicle data = new car(vtype, name, address, phone, email, convertible, color);
               v.add(data);
            }
         }
         
         //scan variables for bicycles
         else if(vtype.equals("bicycle"))
         {
            speeds = scan.nextInt(); if((scan.hasNext()) == true){scan.nextLine();}
            
            //create bicycle
            vehicle data = new bicycle(vtype, name, address, phone, email, speeds);
            v.add(data);
         }
         
         //scan variables for trucks
         else if(vtype.equals("truck"))
         {
            tons = scan.nextFloat(); scan.nextLine();
            cost = scan.nextFloat(); scan.nextLine();
            date = scan.nextLine();
            
            //create truck
            vehicle data = new truck(vtype, name, address, phone, email, tons, cost, date);
            v.add(data);
         }
         
         else if(vtype.equals("vehicle"))
         {
            //create generic vehicle
            vehicle data = new vehicle(vtype, name, address, phone, email);
            v.add(data);
         }
      
         //solve blank line problem between sequences 
         if((scan.hasNext()) == true)
         {
            scan.nextLine();
         }
      }//end of while loop
      
//------------------------------------------------------------------------------------------------------------------------------------------------
//RUN METHODS

      System.out.println("START OF PRINT ALL METHOD---------------------------------------------------------------");
      printAll(v);
            
      System.out.println("START OF EMAIL SORT METHOD--------------------------------------------------------------");
      ArrayList<vehicle> vSorted = new ArrayList<vehicle>();
      vSorted = emailSort(v);
      printAll(vSorted);
      
      System.out.println("START OF NUMBER OF RECORDS METHOD-------------------------------------------------------");
      numberOfRecords(v);
      
      System.out.println("START OF BIKES AND TRUCKS METHOD--------------------------------------------------------");
      bikesTrucks(v);
            
      System.out.println("START OF AREA CODE METHOD (987)---------------------------------------------------------");
      areaCode(v);
   }

//------------------------------------------------------------------------------------------------------------------------------------------------
//METHODS

   //print all
   public static void printAll(ArrayList<vehicle> v)
   {
      for(int i=0; i<v.size(); i++)
      {
         System.out.println(v.get(i));
      }
   }
   
   //sort by email
   public static ArrayList<vehicle> emailSort(ArrayList<vehicle> v)
   {
      ArrayList<vehicle> vEmail = new ArrayList<vehicle>();
      char min = 'A';
      boolean going = true;
      while(going)
      {
         for(int i=0; i<v.size(); i++)
         {
            vehicle borp = v.get(i);
            if((borp.getEmail()).charAt(0) == min)
            {
            vEmail.add(v.get(i));
            }
         }
         if(min > 'z')
         {
            going = false;
         }
         else
         {
            min++;
         }
      }
      return vEmail;
   }
   
   //display number of records found in file
   public static void numberOfRecords(ArrayList<vehicle> v)
   {
      System.out.println("There are " + v.size() + " records.");
   }
   
   //print only records for bicycle and truck vehicle types
   public static void bikesTrucks(ArrayList<vehicle> v)
   {
      ArrayList<vehicle> es = new ArrayList<vehicle>();
      es = emailSort(v);
      
      ArrayList<vehicle> bt = new ArrayList<vehicle>();
      
      for(int i=0; i<es.size(); i++)
      {
         vehicle bikeTrucker = es.get(i);
         if((bikeTrucker.getVtype()).equals("bicycle") || (bikeTrucker.getVtype()).equals("truck"))
         {
            bt.add(es.get(i));
         }
      }
      printAll(bt);
   }
   
   //print only records from area code (987)
   public static void areaCode(ArrayList<vehicle> v)
   {
      ArrayList<vehicle> ac = new ArrayList<vehicle>();
      
      for(int i=0; i<v.size(); i++)
      {
         vehicle aCodes = v.get(i);
         String phone = aCodes.getPhone();
         char num1 = phone.charAt(1);
         char num2 = phone.charAt(2);
         char num3 = phone.charAt(3);
         
         String code = new StringBuilder().append(num1).append(num2).append(num3).toString();
         
         if(code.equals("987"))
         {
            ac.add(v.get(i));
         }
      }
      printAll(ac);
   }
}

//------------------------------------------------------------------------------------------------------------------------------------------------
//VEHICLE CLASSES

//generic vehicle
class vehicle
{
   //initialize variables
   private String vtype;
   private String name;
   private String address;
   private String phone;
   private String email;
   
   //constructor
   public vehicle(String vtype, String name, String address, String phone, String email)
   {
      //use set methods for variables when called
      setVtype(vtype);
      setName(name);
      setAddress(address);
      setPhone(phone);
      setEmail(email);
   }
   
   //set and get methods
   public void setVtype(String vtype)
   {
      this.vtype = vtype;
   }  
   public String getVtype()
   {
      return vtype;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   public String getName()
   {
      return name;
   }
   
   public void setAddress(String address)
   {
      this.address = address;
   }
   public String getAddress()
   {
      return address;
   }
   
   public void setPhone(String phone)
   {
      this.phone = phone;
   }
   public String getPhone()
   {
      return phone;
   }
   
   public void setEmail(String email)
   {
      this.email = email;
   }
   public String getEmail()
   {
      return email;
   }
   
   //override toString method with custom format
   public String toString()
   {
      return String.format("%s\n%s\n%s\n%s\n%s\n", vtype, name, address, phone, email);
   }
}

//generic car
class car extends vehicle
{
   //initialize variables
   boolean convertible;
   String color;
    
   //constructor
   public car(String vtype, String name, String address, String phone, String email, boolean convertible, String color)
   {
      //send universal methods up to parent class to be dealt with
      super(vtype, name, address, phone, email);
      
      //use set methods for variables when called 
      setConvertible(convertible);
      setColor(color);
   }
   
   //get and set methods
   public void setConvertible(boolean convertible)
   {
      this.convertible = convertible;
   }  
   public boolean getConvertible()
   {
      return convertible;
   }
   
   public void setColor(String color)
   {
      this.color = color;
   }
   public String getColor()
   {
      return color;
   }
   
   //override toString method with custom format
   public String toString()
   {
      return String.format("%s\n%s\n%s\n%s\n%s\n%b\n%s\n", getVtype(), getName(), getAddress(), getPhone(), getEmail(), convertible, color);
   }
}

//american car
class american_car extends car
{
   //initialize variables
   private boolean detroit;
   private boolean union;
   
   //constructor
   public american_car(String vtype, String name, String address, String phone, String email, boolean convertible, String color, boolean detroit, boolean union)
   {
      //send universal methods up to parent class to be dealt with
      super(vtype, name, address, phone, email, convertible, color);
      
      //use set methods for variables when called
      setDetroit(detroit);
      setUnion(union);
   }
   
   //get and set methods
   public void setDetroit(boolean detroit)
   {
      this.detroit = detroit;
   }
   public boolean getDetroit()
   {
      return detroit;
   }
   
   public void setUnion(boolean union)
   {
      this.union = union;
   }
   public boolean getUnion()
   {
      return union;
   }
   
   //override toString method with custom format
   public String toString()
   {
      return String.format("%s\n%s\n%s\n%s\n%s\n%b\n%s\n%b\n%b\n", getVtype(), getName(), getAddress(), getPhone(), getEmail(), getConvertible(), getColor(), detroit, union);
   }
}

//foreign car
class foreign_car extends car
{
   //initialize variables
   private String country;
   private float importDuty;
   
   //constructor
   public foreign_car(String vtype, String name, String address, String phone, String email, boolean convertible, String color, String country, float importDuty)
   {
      //send universal methods up to parent class to be dealt with
      super(vtype, name, address, phone, email, convertible, color);
      
      //use set methods for variables when called
      setCountry(country);
      setImportDuty(importDuty);
   }
   
   //get and set methods
   public void setCountry(String country)
   {
      this.country = country;
   }
   public String getCountry()
   {
      return country;
   }
   
   public void setImportDuty(float importDuty)
   {
      this.importDuty = importDuty;
   }
   public float getImportDuty()
   {
      return importDuty;
   }
   
   //override toString method with custom format
   public String toString()
   {
      return String.format("%s\n%s\n%s\n%s\n%s\n%b\n%s\n%s\n%f\n", getVtype(), getName(), getAddress(), getPhone(), getEmail(), getConvertible(), getColor(), country, importDuty);
   }
}

//truck
class truck extends vehicle
{
   //initialize variables
   private float tons;
   private float cost;
   private String date;
   
   //constructor
   public truck(String vtype, String name, String address, String phone, String email, float tons, float cost, String date)
   {
      //send universal methods up to parent class to be dealt with
      super(vtype, name, address, phone, email);
      
      //use set methods for variables when called
      setTons(tons);
      setCost(cost);
      setDate(date);
   }
   
   //get and set methods
   public void setTons(float tons)
   {
      this.tons = tons;
   }
   public float getTons()
   {
      return tons;
   }
   
   public void setCost(float cost)
   {
      this.cost = cost;
   }
   public float getCost()
   {
      return cost;
   }
   
   public void setDate(String date)
   {
      this.date = date;
   }
   public String getDate()
   {
      return date;   
   }
   
   //override toString method with custom format
   public String toString()
   {
      return String.format("%s\n%s\n%s\n%s\n%s\n%f\n%f\n%s\n", getVtype(), getName(), getAddress(), getPhone(), getEmail(), tons, cost, date);
   }
}
   
//bicycle
class bicycle extends vehicle
{
   //initialize variables
   private int speeds;
   
   //constructor
   public bicycle(String vtype, String name, String address, String phone, String email, int speeds)
   {
      //send universal methods up to parent class to be dealt with
      super(vtype, name, address, phone, email);
      
      //use set methods for variables when called
      setSpeeds(speeds);
   }
   
   //get and set methods
   public void setSpeeds(int speeds)
   {
      this.speeds = speeds;
   }
   public int getSpeeds()
   {
      return speeds;   
   }
   
   //override toString method with custom format
   public String toString()
   {
      return String.format("%s\n%s\n%s\n%s\n%s\n%d\n", getVtype(), getName(), getAddress(), getPhone(), getEmail(), speeds);
   }
}