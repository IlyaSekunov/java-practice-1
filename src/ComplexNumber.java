public record ComplexNumber(double real, double image) implements Number {
    @Override
    public Number plus(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            return new ComplexNumber(real + ((ComplexNumber) otherNumber).real, image + ((ComplexNumber) otherNumber).image);
        } else if (otherNumber instanceof RealNumber) {
            return new ComplexNumber(real + ((RealNumber) otherNumber).value(), image);
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public Number minus(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            return new ComplexNumber(real - ((ComplexNumber) otherNumber).real, image - ((ComplexNumber) otherNumber).image);
        } else if (otherNumber instanceof RealNumber) {
            return new ComplexNumber(real - ((RealNumber) otherNumber).value(), image);
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public Number multiply(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            double real1 = real * ((ComplexNumber) otherNumber).real;
            double image1 = real * ((ComplexNumber) otherNumber).image;
            double real2 = -(image * ((ComplexNumber) otherNumber).image);
            double image2 = image * ((ComplexNumber) otherNumber).real;
            if (Double.compare(image1 + image2, 0) == 0) {
                return new RealNumber(real1 + real2);
            }
            return new ComplexNumber(real1 + real2, image1 + image2);
        } else if (otherNumber instanceof RealNumber) {
            if (Double.compare(((RealNumber) otherNumber).value(), 0) == 0) {
                return new RealNumber(0);
            }
            return new ComplexNumber(real * ((RealNumber) otherNumber).value(), image * ((RealNumber) otherNumber).value());
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public Number divide(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            if (this.equals(otherNumber)) {
                return new RealNumber(1);
            }
            ComplexNumber conjugate = new ComplexNumber(((ComplexNumber) otherNumber).real, -((ComplexNumber) otherNumber).image);
            ComplexNumber nominator = (ComplexNumber) multiply(conjugate);
            RealNumber denominator = (RealNumber) otherNumber.multiply(conjugate);
            return new ComplexNumber(nominator.real / denominator.value(), nominator.image / denominator.value());
        } else if (otherNumber instanceof RealNumber) {
            return new ComplexNumber(real / ((RealNumber) otherNumber).value(), image / ((RealNumber) otherNumber).value());
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(real, that.real) == 0 && Double.compare(image, that.image) == 0;
    }

    @Override
    public String toString() {
        if (image > 0) {
            return real + " + " + image + "i";
        }
        return real + " - " + (-image) + "i";
    }
}
