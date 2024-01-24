public record RealNumber(double value) implements Number {
    @Override
    public Number plus(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            return new ComplexNumber(value + ((ComplexNumber) otherNumber).real(), ((ComplexNumber) otherNumber).image());
        } else if (otherNumber instanceof RealNumber) {
            return new RealNumber(value + ((RealNumber) otherNumber).value);
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public Number minus(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            return new ComplexNumber(value - ((ComplexNumber) otherNumber).real(), -((ComplexNumber) otherNumber).image());
        } else if (otherNumber instanceof RealNumber) {
            return new RealNumber(value - ((RealNumber) otherNumber).value);
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public Number multiply(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            return new ComplexNumber(value * ((ComplexNumber) otherNumber).real(), value * ((ComplexNumber) otherNumber).image());
        } else if (otherNumber instanceof RealNumber) {
            return new RealNumber(value * ((RealNumber) otherNumber).value);
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public Number divide(Number otherNumber) {
        if (otherNumber instanceof ComplexNumber) {
            ComplexNumber conjugate = new ComplexNumber(((ComplexNumber) otherNumber).real(), -((ComplexNumber) otherNumber).image());
            RealNumber denominator = (RealNumber) otherNumber.multiply(conjugate);
            ComplexNumber nominator = (ComplexNumber) multiply(conjugate);
            return new ComplexNumber(nominator.real() / denominator.value(), nominator.image() / denominator.value());
        } else if (otherNumber instanceof RealNumber) {
            return new RealNumber(value / ((RealNumber) otherNumber).value);
        } else {
            throw new UnknownNumberException("Unknown number" + otherNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealNumber that = (RealNumber) o;
        return Double.compare(value, that.value) == 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
