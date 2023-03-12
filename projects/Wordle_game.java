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
public class Wordle_game {

	/**
	 * @param args
	 */
	Scanner sc;
	int range=5;
	String s1;
	String s2;
	
	public void toGetRandom_Word()
	{
		s1="lord";	
		System.out.println(s1);
		
	}
	
	public void toGetWord_fromUser()
	{
		sc=new Scanner(System.in);
		System.out.println("Enter word to guess: ");
		s2=sc.nextLine();
		
		toGuessWord();
	}
	
	 public void toGuessWord()
		{
			char ch[]=new char[range];
			
			for(int i=0;i<s2.length();i++)
			{
					int j=0,index=0;
													
						while(index<i)				//to check if the number is presents before, if it presents then the outer loop(j) will start from (i)index.
						{								//the loop will run until i index. 
							if(s2.charAt(i)==s2.charAt(index))
								{
									j=i;				
								}
							index++;
						}
				
				for(;j<s1.length();j++)
				{
					if(s2.charAt(i)==s1.charAt(j) && i==j)			//if user number and random number are same and their index also same, then it will indicate as c.
					{
						ch[i]='c';
						break;
					}
					else if(s2.charAt(i)==s1.charAt(j))				//if user number and random number are same but their index is different, then it will indicate as p.
					{
						ch[i]='p';
						break;
					}
				}
				
				if(j==s1.length())							//if user number not same as random number, then it will indicate as x.
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
				toGetWord_fromUser();
			}
			
			else
			{
				System.out.println();
				
				for(char i:ch)
					System.out.print(i+" ");
			}
			
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Wordle_game obj=new Wordle_game();
		obj.toGetRandom_Word();
		obj.toGetWord_fromUser();
	}

}
