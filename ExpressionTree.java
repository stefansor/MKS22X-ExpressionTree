public class ExpressionTree{
  private char op;
  private double value;
  private ExpressionTree left,right;

  /*TreeNodes are immutable, so no issues with linking them across multiple
  *  expressions. The can be constructed with a value, or operator and 2
  * sub-ExpressionTrees*/
  public ExpressionTree(double value){
    this.value = value;
    op = '~';
  }
  public ExpressionTree(char op,ExpressionTree l, ExpressionTree r){
    this.op = op;
    left = l;
    right = r;
  }

  public char getOp(){
    return op;
  }

  /* accessor method for Value, precondition is that isValue() is true.*/
  private double getValue(){
    return value;
  }
  /* accessor method for left, precondition is that isOp() is true.*/
  private ExpressionTree getLeft(){
    return left;
  }
  /* accessor method for right, precondition is that isOp() is true.*/
  private ExpressionTree getRight(){
    return right;
  }

  private boolean isOp(){
    return hasChildren();
  }
  private boolean isValue(){
    return !hasChildren();
  }

  private boolean hasChildren(){
    return left != null && right != null;
  }


  /*return the expression as an infix notation string with parenthesis*/
  /* The sample tree would be: "(3 + (2 * 10))"     */
  public String toString(){
    if(isValue()){
      return  "" + getValue();
    }
    else{
      return "(" + getLeft().toString() + " "
      + getOp() + " "
      + getRight().toString() + ")";
    }
  }    /*you are to write this method*/


  /*return the expression as a postfix notation string without parenthesis*/
  /* The sample tree would be: "3 2 10 * +"     */
  public String toStringPostfix(){
    if(isValue()){
      return  "" + getValue();
    }
    else{
      return  getLeft().toStringPostfix() + " "
      + getRight().toStringPostfix() + " "
      + getOp();
    }
  }

  /*return the expression as a prefix notation string without parenthesis*/
  /* The sample tree would be: "+ 3 * 2 10"     */

  public String toStringPrefix(){
    if(isValue()){
      return  "" + getValue();
    }
    else{
      return  getOp() + " "
      + getLeft().toStringPrefix() + " "
      + getRight().toStringPrefix();
    }
  }



  /*return the value of the specified expression tree*/

  public double evaluate(){
    if(isValue()){
      return getValue();
    }
    else{
      return apply(getOp(), getLeft().evaluate(), getRight().evaluate());
    }
  }


  /*use the correct operator on both a and b, and return that value*/
  private double apply(char op, double a, double b){
    if(op == '+'){
      return a + b;
    }
    else if(op == '-'){
      return a - b;
    }
    else if(op == '*'){
      return a * b;
    }
    else{
      return a / b;
    }
  }

  public static void main(String[] args){
    ExpressionTree one = new ExpressionTree(4.0);
    ExpressionTree two = new ExpressionTree(4.0);
    ExpressionTree three = new ExpressionTree('+', one, two);
    ExpressionTree four = new ExpressionTree(3.0);
    ExpressionTree five = new ExpressionTree('*', four, three);


    System.out.println(five.toStringPrefix());
    System.out.println(five.toStringPostfix());
    System.out.println(five);
    System.out.println(five.evaluate());
  }

}
