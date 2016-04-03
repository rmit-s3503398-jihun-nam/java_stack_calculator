package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  @author Jihun Nam
 *  Basic calculator using stack data structure
 *  
 */

public class Calc {

	private static int stackSize = 30;
	private static Stack stack;
	private static char[] operator = {'+','-','/','*'};
	private static HashMap<Character, Integer> operatorPriorty = new HashMap<Character, Integer>();
	private static String st;
	private static String st_after_postfix = "";
	private static ArrayList stringforParsing = new ArrayList(); 
	
	public static void main(String[] args) {
			
		InitOperatorProiority();
 
		toPostFix();
		
	}
	/*
	 *   eg) A+B*C => A B C * + 
	 */
	
	public static void toPostFix()
	{
		Scanner sc = new Scanner(System.in);
		stack = new Stack(stackSize);
		char[] chs;
		int j = 0;
		
		while(sc.hasNext())
		{
		    st = sc.nextLine();
		    chs = st.toCharArray();
		    char temp;
		    
		    for(int i=0;i<chs.length;i++)
		    {
		    	temp = chs[i];

		    	if(temp >= '0' && temp <= '9')
		    	{
 
		    		do{
		    			
		    			st_after_postfix += temp;
 
		    			j = i;
		    			
		    			if(++j < chs.length)
		    			{
		    			   temp = chs[++i];	
		    			}
		    			else
		    			{
		    				break;
		    			}
		    			
		    			
 
		    		}
		    	   while(temp >= '0' && temp <= '9'); 
		    	
		    		st_after_postfix += ' ';
		    		
		    	}
		    	
		    	if(temp=='(')
		    	{
		    		stack.push(temp);
		    	}
		    	
		    	if(temp==')')
		    	{
		    		do{
		    			
		    		st_after_postfix += (char) stack.pop();
 
		    		}while(!stack.isEmpty() && stack.viewPop()!='(');

		    		stack.pop();
		    		
		    	}
 
		    	if(array_in(operator,temp))
		    	{
		    		int priorty = operatorPriorty.get(temp);

		    		
		    		do{

		    			if(!stack.isEmpty() && operatorPriorty.get((char) stack.viewPop()) >= priorty)
		    			{
 
			    			char _operator = (char) stack.pop();
 
			    			st_after_postfix += _operator;

			    			st_after_postfix += ' ';
		    			}
 
		    			
		    		}while(!stack.isEmpty() && operatorPriorty.get((char) stack.viewPop()) >= priorty);
		 
		    		stack.push(temp);
		    	}		    	
		    	
		    	
		    }
		    
		    while(!stack.isEmpty())
		    {
		    	st_after_postfix += (char) stack.pop();
		    	st_after_postfix += ' ';
		    }
 

			
		    for(String tmp : st_after_postfix.split(" "))
		    {

		        stringforParsing.add(tmp);
		    }
		    
 
			stack.removeStack();
			calculate();

		}



	}
	
	public static void calculate()
	{
	    
        Iterator it = stringforParsing.iterator();
 
        
        while(it.hasNext())
        {
        	String tmp = (String) it.next();
        	
        	if(isNumber(tmp))
        	{
        		stack.push(Integer.parseInt(tmp)); 
        	}
        	
        	if(array_in(operator,tmp.toCharArray()[0]))
        	{
        		int num1 = stack.pop();
        		int num2 = stack.pop();
        		int result = 0;
        		char _operator = tmp.toCharArray()[0];
        		
        		if(_operator == '+')
        		{
        			result = num2 + num1;
        		}
        		else if(_operator == '-')
        		{
        			result = num2 - num1;
        		}
        		else if(_operator == '*')
        		{
        			result = num2 * num1;
        		}
        		else if(_operator == '/')
        		{
        			result = num2 / num1;
        		}
        		
        		stack.push(result);
        		
        	}
        	
           
        }
        
        System.out.println("Finish calculate: " + stack.pop());
	}
	
	public static void InitOperatorProiority()
	{
		operatorPriorty.put('+',1);
		operatorPriorty.put('-',1);
		operatorPriorty.put('*',2);
		operatorPriorty.put('/',2);
		operatorPriorty.put('(',0);
		operatorPriorty.put(')',0);
	}
	
	
	public static boolean array_in(char[] chas,char target)
	{
		boolean found = false;
		
		for(int i=0;i<chas.length;i++)
		{
			if(chas[i]==target)
			{
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public static boolean isNumber(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}

}
