package linkedListPolynomials;

abstract class PolynomialInterface {
   DList<Term> data = null;
   
   public PolynomialInterface() {
      data = new DList<>();
   }
   
   public final String toString() {
      String ans = "";
      boolean starting = true;
      try {
         DNode<Term> n = data.getFirst();
         while (n != null) {
            if (!starting && n.getData().isPositive()) ans += " +";
            starting = false;
            ans += " " + n.getData().toString();
            n = data.getNext(n);
         }
      } catch (Exception e) {
         if (starting) return "0";
      }
      return ans;
   }
   
   abstract public PolynomialInterface add(PolynomialInterface p);
   abstract public PolynomialInterface subtract(PolynomialInterface p);
   abstract public PolynomialInterface multiply(PolynomialInterface p);
   abstract public PolynomialInterface divide(PolynomialInterface p) throws Exception;
   abstract public PolynomialInterface remainder(PolynomialInterface p) throws Exception;
}