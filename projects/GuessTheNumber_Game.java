/**
 * 
 */
package projects;

import java.util.Random;
import java.util.Scanner;

/**
 * @author logan
 * 
 * A game to guess random number, it will show the correct number with same position as (c), number presents but position different as (p), wrong number as (x).
 */
public class GuessTheNumber_Game {

	/**
	 * @param args
	 */
	
	Scanner sc;
	int random_arr[];
	int user_arr[];
	int count_random[];
	int count_user[];
	public static int range;
	String random_number;
	int toview;
	
	public void toGetRandom_Number()
	{
		/* 
		 * For guess the number with different range that can user guess. The user can select digit range of a number to guess.
		 * Here i used Random class's nextInt(int bound) method to generate random number, this will generate random number upon the bound range.
		 * Every time the random class's method called it will generate single number, so i have called the method in for loop, it will generate number depends upon the range of digit that user gives.
		 * I have stored the random number that generates each time in an array to check with user given number on later.
		 * I have also stored the random number in String to use that anywhere without loop, bcauz random number stored in array.
		 * At last i have called toCount_Random() method to count the appearance of random numbers and stored in an count_random[] array, to check the user given number's appearance on later. 
		 * */
		
		sc=new Scanner(System.in);
		System.out.println("Enter the digit range of number that you want to guess:");
		range=sc.nextInt();
		
		Random r=new Random();
		random_arr=new int[range];
		random_number="";				
		
		for(int i=0;i<range;i++)
		{
			random_arr[i]=r.nextInt(9);		
			random_number+=random_arr[i];
		}
		
//		for(int i:arr1)
//			System.out.print(i+" ");
		
		toCount_Random();
		
	}
	
	public void toGetUser_Number()
	{
		/* The user will give the number to guess and it will be stored as string, bcauz java doesn't consider 0 if a number starts with 0 in int, and handled exception that if user gives character it displays some message.
		 * The number in a string will be stored in an array to check with random number, if user gives number less than range then it will display some message.
		 * To store number in array, i need to directly store each number in array than to store in integer variable, bcauz if number starts with 0, integer won't consider 0.
		 * For that i have taken string as char by charAt and stored in integer variable, now the ASCII value of char will be stored in integer so it'll take 0. Now i stored that ASCII number - 48 (number starts with 48 in ASCII, so for 48 (0)-48 : 0 ) and stored in array,   
		 * For e.g user given number in string ->123 => ASCII : 49,50,51; a[0]=49-48 : 1, a[1]=50-48: 2, a[2]=51-48 : 3.
		 * Here i called guessnumber() method to check random number with user number.  
		 *   */
		
		sc=new Scanner(System.in);
		
		System.out.println("\nEnter number to guess: ");
		try{
			
			String str=sc.nextLine();
			
			for(int i=0;i<str.length();i++)
			{
				if(!(str.charAt(i)>='0' && str.charAt(i)<='9'))
					throw new GuessNumberException(Error_msg_GuessNumber.err2_input_mismatch);
			}
			
			user_arr=new int[range];
			
			if(str.length()!=range)
			{
				throw new GuessNumberException(Error_msg_GuessNumber.err1_input_mismatch);
			}
	
			for(int i=0;i<str.length();i++)
			{
				int temp=str.charAt(i);
				user_arr[i]=temp-48;
			}
			
			System.out.println();
			for(int i:user_arr)
				System.out.print(i+" ");
			
			toGuessNumber();
		
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e=new ArrayIndexOutOfBoundsException(Error_msg_GuessNumber.err1_input_mismatch);
			System.out.println(e.getMessage());
			toGetUser_Number();
		} 
		catch (GuessNumberException e) 
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			toGetUser_Number();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}		
	}
	
  public void toGuessNumber()
	{
	  
		char ch[]=new char[range];
		int check=0;
		count_user=new int[range];
		
		for(int i=0;i<user_arr.length;i++)
		{
			for(int j=i;j<=i;j++)
			{
				if(user_arr[i]==random_arr[j])
				{
					ch[i]='c';
					check++;
					toUser_Count(user_arr[i],count_user);
				}
			}
		}
		
		if(check==range)
		{
			{
				System.out.println();
				for(char i:ch)
					System.out.print(i+" ");
			}
			System.out.println("\n\nCongratulations, You have guessed correctly. \n\nThe number is : "+random_number );
		}
		else
		{
			for(int i=0;i<user_arr.length;i++)
			{
					int j=0,index=0;
					boolean value=false;
	
				if(ch[i]!='c') 
				{
					
					toUser_Count(user_arr[i],count_user);
					
					while(index<i)				//to check if the number is presents before, if it presents then the outer loop(j) will start from (i)index.
					{								//the loop will run until i index. 
						if(user_arr[i]==user_arr[index] && ch[index]=='p')
							{
							if(count_user[toFindNumber(user_arr[i])]>count_random[toGetCount(user_arr[i])])
								{
									j=random_arr.length;
								}
							}
						index++;
					}
					
				for(;j<random_arr.length;j++)
				{
					
					if(user_arr[i]==random_arr[j] && ch[j]!='c')				//if user number and random number are same but their index is different, then it will indicate as p.
					{
						ch[i]='p';
						value=true;
						break;
					}
				}
				
				if(j==random_arr.length && value!=true)							//if user number not same as random number, then it will indicate as x.
					ch[i]='x';
				
				}
			}
			
				System.out.println();
				for(char i:ch)
					System.out.print(i+" ");
				
				System.out.println();
				toview++;
				if(toview%10==0)
				{
					System.out.println("Do you want to reveal the number: \n1. Yes 2. To continue guessing");
					int choice=sc.nextInt();
					switch(choice)
					{
						case 1:
							System.out.println(random_number);
							break;
						case 2:
							toGetUser_Number();
							break;
					}
				}
				else
				toGetUser_Number();
		}
		
	}
  
  public void toCount_Random()
  {
		/*
		 * This method to count the appearance of random nummber and to store in an array.
		 * If no number repeats then the count will be stored in respective index. If any number repeats then, the count will be stored in first repeated number's index then the repeated number's index will have 0.
		 * As I started j as i index, bcauz it doesn't need to start from first as first comer's will check all the next places.
		 * After first outer loop executes then it will check if the same number already came till before of that number, if it cames then that index will have 0 and it will not enter inner loop, to avoid the count collision.
		 * For e.g => {1,2,1,1} -> 1 - count[0] : 3, 2 - count[1] : 1 ; if that while not given then the third place 1 will enter and inner loop and next number also 1, so it increases it's count as count[3] : 1.
		 * As I don't want that to happen bcauz later i'll check user number's appearance with this counts.
		 */
	  count_random=new int[range];
		
		for(int i=0;i<random_arr.length;i++)
		{
			int index=0,j=i;
			while(index<i)
			{
				if(random_arr[i]==random_arr[index])
				{
					count_random[i]=0;
					j=random_arr.length;
				}
				index++;
			}
			
			for(;j<random_arr.length;j++)
			{
				if(random_arr[i]==random_arr[j])
				{
					count_random[i]+=1;
				}
			}
		}
  }
  
  private int toGetCount(int num) {
		// TODO Auto-generated method stub
		  
		  int i=0;
		  for(;i<random_arr.length;i++)
		  {
			  if(num==random_arr[i])
			  {
				  return i;
			  }
		  }
		  
		return i;
	}
  
  private void toUser_Count(int num, int[] count_user) {
		// TODO Auto-generated method stub
		  for(int i=0;i<user_arr.length;i++)
		  {
			  if(num==user_arr[i])
			  {
				  count_user[i]+=1;
				  break;
			  }
			  
		  }
	}
  
  private int toFindNumber(int num) {
		// TODO Auto-generated method stub
		  int i=0;
		  for(;i<user_arr.length;i++)
		  {
			  if(num==user_arr[i])
				  return i;
		  }
		return i;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GuessTheNumber_Game obj=new GuessTheNumber_Game();
		obj.toGetRandom_Number();
		obj.toGetUser_Number();	
	}

}
