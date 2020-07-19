package linkedListPolynomials;
import java.util.*;

public class Polynomial extends PolynomialInterface {	
	public Polynomial(String s) {
		parseStringToDList(s);
	}

	public Polynomial(double coe, int deg) {
		Term t=new Term(coe, deg);		   
		data.addLast(t);	   		   
	}
   
	public Polynomial(PolynomialInterface p) {
		data = p.data;
	}
   
	//default constructor
	public Polynomial() {
		super();
	}
  
	//utility method to create polynomials from string
	private void parseStringToDList(String s) {	
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
                Double coe=(double) parseCoefficient(token);
                int deg=parseDegree(token);
                Term t=new Term(coe, deg);
                data.addLast(t);
            }
            sc2.close();
        }	        	
        sc1.close();
	}
  
	//parse token to get Coefficient	
	private double parseCoefficient(String token) {
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
 
	//parse token to get degree
	private  int parseDegree(String token) {
		 token=token.trim().toLowerCase();
		  if(token.indexOf("^")>=0)
			return Integer.parseInt(token.substring(token.indexOf("^")+1));
		  else if(token.indexOf("x")>=0)
			  return 1;
		  else
			  return 0;
   } 
 
	//utility method to convert Dlist to array
	private  double[] convertDListToArray(DList<Term> data){
	   try{
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
		   return newCoe;
	   } catch(Exception e) {
		   System.out.println("error in convert");
			   return new double[0];
	   }
	}
   
	public PolynomialInterface add(PolynomialInterface p) {
		PolynomialInterface ans = new Polynomial();
		double[] coe1=convertDListToArray(data);
		DList<Term> pdata = p.data;
		double[] coe2=convertDListToArray(pdata);
		Term t=null;
		int m=coe1.length, n=coe2.length; 
		int size = Math.max(m, n); 
		double sum[] = new double[size]; 
        for (int i = 0; i < m; i++) { 
            sum[i] =coe1[i]; 
        } 
        for (int i = 0; i < n; i++) { 
            sum[i] += coe2[i]; 
        } 
        for (int i=sum.length-1;i>=0;i--) { 
        	 if(sum[i]!=0){
		    	  t=new Term(sum[i], i);
		    	  ans.data.addLast(t);
	    	  }
        }       
        return ans;
	}
   

	public PolynomialInterface subtract(PolynomialInterface p) {
		PolynomialInterface ans = new Polynomial();
		double[] coe1=convertDListToArray(data);
		DList<Term> pdata = p.data;
		double[] coe2=convertDListToArray(pdata);
		Term t=null;
		int m=coe1.length, n=coe2.length; 
		int size = Math.max(m, n); 
		double sub[] = new double[size]; 
        for (int i = 0; i < m; i++) { 
            sub[i] =coe1[i]; 
        } 
        for (int i = 0; i < n; i++) { 
            sub[i] -= coe2[i]; 
        } 
        for (int i=sub.length-1;i>=0;i--) { 
        	 if(sub[i]!=0){
		    	  t=new Term(sub[i], i);
		    	  ans.data.addLast(t);
	    	  }
        }     
        return ans;
	}
   
	public PolynomialInterface multiply(PolynomialInterface p) {
		PolynomialInterface ans = new Polynomial();
		double[] coe1=convertDListToArray(data);
		DList<Term> pdata = p.data;
		double[] coe2=convertDListToArray(pdata);
		Term t=null;
		int m=coe1.length, n=coe2.length;      
		double[] prod = new double[m+n- 1]; 
		for (int i = 0; i < m + n - 1; i++)  { 
			prod[i] = 0; 
		} 
		for (int i = 0; i < m; i++)   { 
            for (int j = 0; j < n; j++)   { 
                prod[i + j] += coe1[i] * coe2[j]; 
            } 
		} 
		for(int i=prod.length-1;i>=0;i--) {	    	  
			if(prod[i]!=0){
				t=new Term(prod[i], i);
				ans.data.addLast(t);
			}
		}
		return ans;
   }
	   
	//utility method to get max degree from polynomial
	private int maxDegree(PolynomialInterface p) {
	   try{
		   DNode<Term> head = p.data.getFirst();
		   int maxDeg= head.getData().getDegree();
		   return maxDeg;
	   } catch(Exception e) {
		   System.out.println("error in get maxDegree");
		   return 0;
	   }   
   }
   
   public PolynomialInterface divide(PolynomialInterface p) throws Exception {
	   PolynomialInterface ans = new Polynomial();
	      // complete this code
	   PolynomialInterface q = new Polynomial(0,0);
	   PolynomialInterface r = new Polynomial( this );
	    while( !r.data.isEmpty() && maxDegree(r) >= maxDegree(p) ){
	    	Term r1=r.data.getFirst().getData();
	    	Term p1=p.data.getFirst().getData();
            double coef = r1.getCoefficient() / p1.getCoefficient();
            int deg = r1.getDegree() - p1.getDegree();
            Polynomial t = new Polynomial( coef, deg );
            q = q.add( t );
            r = r.subtract( t.multiply( p ) );
        }//end while
	   ans = q;
	   return ans;		 
	}
  	   
   public PolynomialInterface remainder(PolynomialInterface p) throws Exception {
	   PolynomialInterface ans = new Polynomial();
	   PolynomialInterface q = new Polynomial(0,0);
	   PolynomialInterface r = new Polynomial( this );
	    while( !r.data.isEmpty() && maxDegree(r) >= maxDegree(p) ){
	    	Term r1=r.data.getFirst().getData();
	    	Term p1=p.data.getFirst().getData();
            double coef = r1.getCoefficient() / p1.getCoefficient();
            int deg = r1.getDegree() - p1.getDegree();
            Polynomial t = new Polynomial( coef, deg );
            q = q.add( t );
            r = r.subtract( t.multiply( p ) );
        }//end while
	   ans = r;
      return ans;
  
   }
   
   public static void main(String args[]) throws Exception {
	   PolynomialInterface p, q;
	   //test case	    	     
	   p = new Polynomial("x^4 + 2.0 x^2 + 3.0");
	   q = new Polynomial("x^2 + x + 1.0");
	   System.out.println("Default Input\n***************************");
	   Utility.run(p, q);
   }	   
}