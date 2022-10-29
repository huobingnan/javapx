package ano.nunu.jvmabi;

public class Unsigned {

    // covert byte to int (unsigned)
    public static int cbi(byte src) {
        if (src < 0) return (((int)src) & 0x00_00_00_ff);
        else return (int)src;
    }

    // covert byte to unsigned long
    public static long cbl(byte src) {
        if (src < 0) return (((long) src) & 0x00_00_00_00_00_00_00_ff);
        else return (long) src;
    }
}
