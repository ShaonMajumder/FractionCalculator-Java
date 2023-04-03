public class FractionTest {
    public static void main(String[] args){
        Fraction f1, f2, f3;
        f1 = new Fraction(24, 36);
        f1.print_simply = false;
        System.out.println(f1.toString() + " can be reduced to " + f1.simplify());
        f1.print_simply = true;
        f1 = new Fraction(1, 2);
        f2 = new Fraction(1, 4);
        f3 = f1.add(f2);
        System.out.println("Sum of " + f1.toString() + " and " +  f2.toString() + " is " + f3.toString());
        Fraction add_result = f1.add(f2);
        System.out.println("Sum of " + f1.toString() + " and " + f2.toString() + " = " + add_result.toString());
        System.out.println("Difference between " + f1.toString() + " and " + f2.toString() + " = " + f1.subtract(f2));
        System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
        Fraction divide_result = f1.divide(f2);
        System.out.println(f1.toString() + " / " + f2.toString() + " = " + divide_result.toString());
        Fraction minFraction = Fraction.min(f1, f2);
        System.out.println("The minimum fraction between " +f1.toString() + " and " + f2.toString() + ": " + minFraction);
        System.out.println("f1 in decimal format " + f1.decimal());
        System.out.println("f2 in decimal format " + f2.decimal());
        System.out.println( f1.toString() + " == " +  f2.toString() + " is " + f1.equals(f2));
        System.out.println( f1.toString() + " == " +  f3.toString() + " is " + f1.equals(f3));
        Fraction f4 = new Fraction(1, 2);
        System.out.println( f1.toString() + " == " +  f4.toString() + " is " + f1.equals(f4));
    }
}
