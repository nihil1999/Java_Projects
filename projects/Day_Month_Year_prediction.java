/**
 * 
 */
package projects;

import java.util.Scanner;

/**
 * @author logan
 *
 */
public class Day_Month_Year_prediction {

	/**
	 * @param args
	 */
	
	public void toPredict()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter hours: ");
		int number=sc.nextInt();
		int num=number;
		int sec=0,min=0,hour=0,day=0,month=0,year=0;
		int sec_count=0,min_count=0,hour_count=0,day_count=0,month_count=0;
		
		while(num>0)
		{
			if(num<24)
				{
					hour=num;
					num-=24;
				}
			hour_count=hour;
			if(num>=24)
			{
				day=day+1;
				num-=24;
			}
			day_count=day;
			while(day>=30)
			{
				month=month+1;
				day-=30;
			}
			month_count=month;
			while(month>=12)
			{
				year+=1;
				month-=12;
			}
		}
		if(number<24)
			System.out.println("Hour is: "+hour);
		else
		System.out.println("Year : "+year+"\nMonth : "+month_count+"\nDay : "+day_count+"\nHours : "+hour_count);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Day_Month_Year_prediction obj=new Day_Month_Year_prediction();
		obj.toPredict();
	}

}
