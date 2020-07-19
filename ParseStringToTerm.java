package linkedListPolynomials;

import java.util.*;
public class ParseStringToTerm {
	   
	static void parseStringToTerm(String s, DList<Term> data ) {	
		Scanner sc1 = new Scanner( s );
        sc1.useDelimiter( "[+]" );      
        while( sc1.hasNext( ) ) {
            boolean first = true;            
            Scanner sc2 = new Scanner( sc1.next( ) );
            sc2.useDelimiter( "-" );
            while( sc2.hasNext( ) ) {
                String token = sc2.next( );
                if( !first )
                    token = "-" + token;
                else
                    first = false;
                System.out.println(token);	                
                Double coe=(double) parseCoe(token);
                int deg=parseDegree(token);
                Term t=new Term(coe, deg);
                data.addLast(t);
            }
            sc2.close();
        }	        	
        sc1.close();
	}
  
	static double parseCoe(String token) {
	  int mod=1;
	  token = token.toLowerCase();
	  if(token.contains("-")) {
		  mod=-1;
		  int index=token.indexOf("-");
		  token =token.substring(index+1).trim();
	  }		  
	  String sub="";
	  if(token.indexOf("x")>=0) {
		  int index=token.indexOf("x");
		  sub=token.substring(0,index).trim();
	  } else {
		sub=token.trim();  
	  }
	  double res=0;
	  if(sub.length()==0)
		  res=1;  
	  else
		  res=Double.parseDouble(sub);
       return res*mod;       
   }
  
	static int parseDegree(String token1) {
		String token=token1.trim().toLowerCase();
		if(token.indexOf("^")>=0)
			return Integer.parseInt(token.substring(token.indexOf("^")+1));
		else if(token.indexOf("x")>=0)
			return 1;
		else
			return 0;
	} 
  

	public static void main(String args[]) throws Exception {
		String s="x^4 + 2.0 x^2 + 3.0";
		DList<Term> data = new DList<>();
		parseStringToTerm(s, data);
		System.out.println(data);
	   
		//loop the link
		DNode<Term> head = data.getFirst();
		int maxDeg= head.getData().getDegree();
		double newCoe[]=new double[maxDeg+1];
	  
		while(head.getNext()!=null) {
			Term node = head.getData();
			double coe=node.getCoefficient();
			int deg = node.getDegree();			   
			newCoe[deg] = coe; 
			head=head.getNext();  
		}
		for(int i=maxDeg;i>=0;i--)
			System.out.print(newCoe[i]+ " ");
		System.out.println();		
	}
}
