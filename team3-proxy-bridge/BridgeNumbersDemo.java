public class BridgeNumbersDemo {
    interface NumberImpl {
        double getR();
        double getIm();
    }
    static class IntegerImpl implements NumberImpl {
        private int x;
        IntegerImpl(int x) {
            this.x = x;
        }
        public double getR() {
            return x;
        }
        public double getIm() {
            return 0.0;
        }
    }

    static class RealImpl implements NumberImpl {
        protected double r;
        RealImpl(double r) {
            this.r = r;
        }
        public double getR() {
            return r;
        }
        public double getIm() {
            return 0.0;
        }
    }

    static class ComplexImpl extends RealImpl {
        private double im;
        ComplexImpl(double r, double im) {
            super(r);
            this.im = im;
        }
        public double getIm() {
            return im;
        }
    }

    interface INumber {
        INumber add(INumber other);
        double getR();
        double getIm();
    }
    static abstract class AbstractNumber implements INumber {
        protected NumberImpl impl;
        protected AbstractNumber(NumberImpl impl) {
            this.impl = impl;
        }
        public double getR() {
            return impl.getR();
        }
        public double getIm() {
            return impl.getIm();
        }
    }


    static class ComplexNumber extends AbstractNumber {
        ComplexNumber(NumberImpl impl) {
            super(impl);
        }
        public INumber add(INumber other) {
            double r = getR() + other.getR();
            double im = getIm() + other.getIm();
            return new ComplexNumber(new ComplexImpl(r, im));
        }
    }
    static class RealNumber extends AbstractNumber {
        RealNumber(NumberImpl impl) {
            super(impl);
        }
        public INumber add(INumber other) {
            if (other.getIm() != 0) {
                throw new IllegalArgumentException(
                        "Cannot add complex number to real number"
                );
            }
            double r = getR() + other.getR();
            return new RealNumber(new RealImpl(r));
        }
    }

    static class IntegerNumber extends AbstractNumber {
        IntegerNumber(NumberImpl impl) {
            super(impl);
        }
        public INumber add(INumber other) {
            if (other.getIm() != 0) {
                throw new IllegalArgumentException(
                        "Cannot add complex number to integer"
                );
            }
            int sum = (int) (getR() + other.getR());
            return new IntegerNumber(new IntegerImpl(sum));
        }
    }
    static class NumberFactory {
        static INumber create(String type, double... args) {
            switch (type.toLowerCase()) {
                case "complex":
                    return new ComplexNumber(
                            new ComplexImpl(args[0], args[1])
                    );
                case "real":
                    return new RealNumber(
                            new RealImpl(args[0])
                    );
                case "int":
                    return new IntegerNumber(
                            new IntegerImpl((int) args[0])
                    );
                default:
                    throw new IllegalArgumentException("Unknown number type");
            }
        }
    }


    public static void main(String[] args) {

        INumber c = NumberFactory.create("complex", 1.3, 8.4);
        INumber r = NumberFactory.create("real", 5.6);
        INumber i = NumberFactory.create("int", 2);

        INumber c2 = c.add(r);
        System.out.println("c + r = " + c2.getR() + " + " + c2.getIm() + "i");

        INumber c3 = c.add(i);
        System.out.println("c + i = " + c3.getR() + " + " + c3.getIm() + "i");

        try {
            r.add(c);
        } catch (Exception e) {
            System.out.println("r + c error: " + e.getMessage());
        }
    }
}
