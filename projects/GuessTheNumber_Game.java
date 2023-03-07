/**
 * 
 */
package projects;

import java.util.Random;
import java.util.Scanner;

/**
 * @author logan
 *
 */
public class GuessTheNumber_Game {

	/**
	 * @param args
	 */
	
	Scanner sc;
	int arr1[];
	int arr2[];
	int count;
	int range=4;
	
	public void toGetRandom_Number()
	{
		
		Random r=new Random();
		
		int num=0;
		arr1=new int[range];
		
		for(int i=0;i<range;i++)
		{
			arr1[i]=r.nextInt(9);
		}
	
		for(int i:arr1)
			System.out.print(i+" ");
		
	}
	
	public void toGetUser_Number()
	{
		sc=new Scanner(System.in);
		
		System.out.println("\nEnter number to guess: ");
		try{
			
			String str=sc.nextLine();
			
			//int num=sc.nextInt();
			//System.out.println(num);
			//arr2=toReturnNumber_asArray(num);
			
			for(int i=0;i<str.length();i++)
			{
				if(!(str.charAt(i)>='0' && str.charAt(i)<='9'))
					throw new GuessNumberException(Error_msg_GuessNumber.err2_input_mismatch);
			}
			
			arr2=new int[range];
			
			if(str.length()!=4)
			{
				throw new GuessNumberException(Error_msg_GuessNumber.err1_input_mismatch);
			}
	
			for(int i=0;i<str.length();i++)
			{
				int temp=str.charAt(i);
				arr2[i]=temp-48;
			}
			
			System.out.println();
			for(int i:arr2)
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
		
		for(int i=0;i<arr2.length;i++)
		{
				int j=0,index=0;
												
					while(index<i)				//to check if the number is presents before, if it presents then the outer loop(j) will start from (i)index.
					{								//the loop will run until i index. 
						if(arr2[i]==arr2[index])
							{
								j=i;				
							}
						index++;
					}
			
			for(;j<arr1.length;j++)
			{
				if(arr2[i]==arr1[j] && i==j)			//if user number and random number are same and their index also same, then it will indicate as c.
				{
					ch[i]='c';
					break;
				}
				else if(arr2[i]==arr1[j])				//if user number and random number are same but their index is different, then it will indicate as p.
				{
					ch[i]='p';
					break;
				}
			}
			
			if(j==arr1.length)							//if user number not same as random number, then it will indicate as x.
				ch[i]='x';
			
		}
		
		boolean flag=true;
		
		for(int i=0;i<ch.length;i++)
		{
			if(ch[i]!='c')
			{
				flag=false;
			}
		}
		
		if(flag==false)
		{
			System.out.println();
			
			for(char i:ch)
				System.out.print(i+" ");
			
			System.out.println();
			toGetUser_Number();
		}
		
		else
		{
			System.out.println();
			
			for(char i:ch)
				System.out.print(i+" ");
		}
		
	}
  
  public int[] toReturnNumber_asArray(int num)
	{
		int num1=num;
		int mul=1;
		count=0;
		while(num1>0)
		{
			num1/=10;
			mul*=10;
			count++;
		}
		mul/=10;
		
		int a[]=new int[count];
		int index=0;
		
//		while(num>0)
//		{
//			a[index]=num/mul;
//			num%=mul;
//			mul/=10;
//			index++;
//		}
		
		int i=a.length-1;
		while(num>0)
		{
			a[i]=num%10;
			num/=10;
			i--;
		}
		
		return a;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GuessTheNumber_Game obj=new GuessTheNumber_Game();
		obj.toGetRandom_Number();
		obj.toGetUser_Number();
		
	}

}
