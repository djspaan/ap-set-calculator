import java.math.*;

public class NaturalNumber implements NaturalNumberInterface, Comparable<NaturalNumber> {

    private BigInteger bi;

    NaturalNumber(){ }

    public void add(char c){
        String biString = toBoolean() ? bi.toString() + Character.toString(c) : Character.toString(c);
        bi = new BigInteger(biString);
    }

    public int getLength(){
        return this.bi.bitCount();
    }

    public String toString()
    {
        return bi.toString();
    }

    private boolean toBoolean() {
        return bi != null;
    }

    @Override
    public int compareTo(NaturalNumber naturalNumber) {
        return bi.compareTo(naturalNumber.bi);
    }
}
