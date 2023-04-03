class Fraction {
    private int numerator;
    private int denominator;
    public boolean print_simply=true;

    public Fraction(int num, int denom) {
        setNumerator(num);
        setDenominator(denom);
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setDenominator(int denom) {
        if (denom == 0) {
            // Fatal error
            System.err.println("Fatal Error");
            System.exit(1);
        }
        denominator = denom;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public String toString() {
        int num = getNumerator();
        int denom = getDenominator();

        assert denom != 0 : "Denominator cannot be zero";

        // Handle negative numbers
        if (denom < 0) {
            num = -num;
            denom = -denom;
        }

        // Handle integer values
        if (num == 0) {
            return "0";
        } else if (denom == 1) {
            return Integer.toString(num);
        }

        // Handle fractions
        try {
            int gcd = gcd(num, denom);
            if(this.print_simply){
                num /= gcd;
                denom /= gcd;
            }

        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
            return "undefined";
        }

        return num + "/" + denom;
    }

    public Fraction simplify() {
        int num = getNumerator();
        int denom = getDenominator();
        int gcd = gcd(num, denom);
        assertDenominatorNotZero(denom);
        handleDenominatorZero(denom);
        return new Fraction(num / gcd, denom / gcd);
    }

    public static int gcd(int a, int b) {
        try {
            if (a < 0 || b < 0) {
                throw new IllegalArgumentException("Arguments to gcd() must be non-negative.");
            }
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            assert a >= 0 && b >= 0;
            return a;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public Fraction add(Fraction frac) {
        int a, b, c, d;
        Fraction sum;
        a = this.getNumerator(); //get the receiving
        b = this.getDenominator(); //object's num and denom
        c = frac.getNumerator(); //get frac's num
        d = frac.getDenominator(); //and denom
        sum = new Fraction(a * d + b * c, b * d);
        assertDenominatorNotZero(sum.getDenominator());
        handleDenominatorZero(sum.getDenominator());
        return sum;
    }

    public Fraction divide(Fraction frac) {
        int a, b, c, d;
        Fraction quotient;
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        quotient = new Fraction(a * d, b * c);
        assertDenominatorNotZero(quotient.getDenominator());
        handleDenominatorZero(quotient.getDenominator());
        return quotient;
    }

    public Fraction multiply(Fraction frac) {
        int a, b, c, d;
        Fraction product;
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        product = new Fraction(a * c, b * d);
        assertDenominatorNotZero(product.getDenominator());
        handleDenominatorZero(product.getDenominator());
        return product;
    }

    public Fraction subtract(Fraction frac) {
        int a, b, c, d;
        Fraction diff;
        a = this.getNumerator();
        b = this.getDenominator();
        c = frac.getNumerator();
        d = frac.getDenominator();
        diff = new Fraction(a * d - b * c, b * d);
        assertDenominatorNotZero(diff.getDenominator());
        handleDenominatorZero(diff.getDenominator());
        return diff;
    }

    public static Fraction min(Fraction f1, Fraction f2) throws IllegalArgumentException {
        assert f1 != null && f2 != null : "Fractions cannot be null";
        try {
            double f1_dec = f1.decimal();
            double f2_dec = f2.decimal();
            if (f1_dec <= f2_dec) {
                return f1;
            } else {
                return f2;
            }
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Cannot compare fractions with 0 denominators");
        }
    }

    public double decimal() throws ArithmeticException {
        int num = getNumerator();
        int denom = getDenominator();
        if (denom == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        assert denom != 0 : "Denominator cannot be zero";
        return (double) num / denom;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction other = (Fraction) obj;
        try {
            assertDenominatorNotZero(this.getDenominator());
            assertDenominatorNotZero(other.getDenominator());
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
        Fraction f1 = this.simplify(); // simplify itself
        Fraction f2 = other.simplify(); // simplify other fraction
        return (f1.getNumerator() == f2.getNumerator() &&
                f1.getDenominator() == f2.getDenominator());
    }

    public Fraction multiply(int number) {
        try {
            assert number >= 0 : "Number must be non-negative.";
            Fraction frac = new Fraction(number, 1);
            Fraction product = multiply(frac);
            return product;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    public void assertDenominatorNotZero(int denom) {
        assert denom != 0 : "Denominator cannot be zero";
    }

    public void handleDenominatorZero(int denom) throws IllegalArgumentException {
        if (denom == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
    }
}