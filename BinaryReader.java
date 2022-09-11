import java.util.ArrayList;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;

public final class BinaryReader {
    private final byte[] bytes;
    private int idx;

    public BinaryReader(byte[] bytes) {
        this.bytes = bytes;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final int getIdx() {
        return this.idx;
    }

    public final void setIdx(int i) {
        this.idx = i;
    }

    public final boolean getHasNext() {
        return this.idx < this.bytes.length;
    }

    public final String getString(int i) {
        BinaryReader binaryReader = this;
        byte[] slice = Arrays.copyOfRange(binaryReader.getBytes(), binaryReader.getIdx(), binaryReader.getIdx() + i);
        ArrayList<Byte> arrayList = new ArrayList<Byte>();
        for (Byte obj : slice) {
            if (((Number) obj).byteValue() == 0) {
                break;
            }
            arrayList.add(obj);
        }
        byte[] result = new byte[arrayList.size()];
        for(int r = 0; r < arrayList.size(); r++) {
            result[r] = arrayList.get(r).byteValue();
        }
        String str = new String(result, StandardCharsets.UTF_8);
        this.idx += i;
        return str;
    }

    public final String getVariableLengthString() {
        return getString(getByte());
    }

    public final short getShort() {
        return (short) (getByte() | (getByte() << 8));
    }

    public final int getUShort() {
        return getByte() | (getByte() << 8);
    }

    public final short getShortLE() {
        return (short) ((getByte() << 8) | getByte());
    }

    public final int getInt() {
        return getByte() | (getByte() << 8) | (getByte() << 16) | (getByte() << 24);
    }

    public final int getByte() {
        byte[] bArr = this.bytes;
        int i = this.idx;
        this.idx = i + 1;
        return bArr[i] & 255;
    }

    public final int getByte(int i) {
        return this.bytes[i] & 255;
    }
}