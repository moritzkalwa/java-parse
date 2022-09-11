
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Parser {
    List<Info> parseBytesFormat5(byte[] bArr) {
        BinaryReader binaryReader = new BinaryReader(bArr);
        ArrayList<Info> arrayList = new ArrayList<Info>();
        while (binaryReader.getHasNext()) {
            int i = 1;
            int count = binaryReader.getInt();
            int i2 = 0;
            while (i2 < count) {
                i2++;
                String variableLengthString = binaryReader.getVariableLengthString();
                System.out.println(variableLengthString);
                String variableLengthString2 = binaryReader.getVariableLengthString();
                int i3 = binaryReader.getInt();
                Coordinate2d coordinate2d = new Coordinate2d(binaryReader.getInt() / 100.0d, binaryReader.getInt() / 100.0d);
                IntRange intRange = new IntRange(i, binaryReader.getByte());
                ArrayList<Net> arrayList2 = new ArrayList<Net>(intRange.size());
                Iterator<Integer> it = intRange.iterator();
                while (it.hasNext()) {
                    it.next();
                    String lowerCase = binaryReader.getString(3).toLowerCase(Locale.ROOT);
                    IntRange intRange2 = new IntRange(i, binaryReader.getByte());
                    ArrayList<Integer> arrayList3 = new ArrayList<Integer>(intRange2.size());
                    Iterator<Integer> it2 = intRange2.iterator();
                    while (it2.hasNext()) {
                        it2.next();
                        arrayList3.add(Integer.valueOf(binaryReader.getInt()));
                    }
                    arrayList2.add(new Net(lowerCase, arrayList3));
                }
                ArrayList<Net> arrayList4 = arrayList2;
                int count2 = (new IntRange(i, binaryReader.getShort())).size();
                int i5 = 0;
                while (i5 < count2) {
                    int i6 = i5 + 1;
                    String variableLengthString3 = binaryReader.getVariableLengthString();
                    String variableLengthString4 = binaryReader.getVariableLengthString();
                    short s = binaryReader.getShort();
                    short s2 = binaryReader.getShort();
                    boolean z = binaryReader.getByte() == i;
                    IntRange intRange3 = new IntRange(i, binaryReader.getByte());
                    ArrayList<Coordinate2d> arrayList5 = new ArrayList<Coordinate2d>(intRange3.size());
                    Iterator<Integer> it3 = intRange3.iterator();
                    while (it3.hasNext()) {
                        it3.next();
                        arrayList5.add(new Coordinate2d(binaryReader.getInt() / 100.0d, binaryReader.getInt() / 100.0d));
                    }
                    arrayList.add(new Info(variableLengthString3, variableLengthString4, variableLengthString, variableLengthString2, i3, s, s2, z, coordinate2d, 0, arrayList4));
                    i5 = i6;
                }
            }
        }
        return arrayList;
    }
}