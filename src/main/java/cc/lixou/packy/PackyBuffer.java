package cc.lixou.packy;

import lombok.SneakyThrows;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.Arrays;

public class PackyBuffer {

    private final byte[] bytes;

    private ByteArrayOutputStream byteOutputStream;
    private DataOutputStream dataOutputStream;

    private ByteArrayInputStream byteInputStream;
    private DataInputStream dataInputStream;

    public PackyBuffer(byte[] bytes) {
        this.bytes = bytes;
    }

    public DataOutputStream requireOutputStream() {
        if(dataOutputStream == null || byteOutputStream == null) {
            byteOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteOutputStream);
        }
        return dataOutputStream;
    }
    public DataInputStream requireInputStream() {
        if(byteInputStream == null || dataInputStream == null) {
            byteInputStream = new ByteArrayInputStream(bytes);
            dataInputStream = new DataInputStream(byteInputStream);
        }
        return dataInputStream;
    }

    /* == WRITE == */
    public PackyBuffer write(Serializable serializable) { writeBytes(SerializationUtils.serialize(serializable)); return this; }
    @SneakyThrows public PackyBuffer writeBytes(byte[] bytes) { requireOutputStream().write(bytes); return this; }
    @SneakyThrows public PackyBuffer writeByte(byte b) { requireOutputStream().writeByte(b); return this; }
    @SneakyThrows public PackyBuffer writeBoolean(boolean b) { requireOutputStream().writeBoolean(b); return this; }
    @SneakyThrows public PackyBuffer writeShort(short s) { requireOutputStream().writeShort(s); return this; }
    @SneakyThrows public PackyBuffer writeInt(int i) { requireOutputStream().writeInt(i); return this; }
    @SneakyThrows public PackyBuffer writeLong(long l) { requireOutputStream().writeLong(l); return this; }
    @SneakyThrows public PackyBuffer writeFloat(float f) { requireOutputStream().writeFloat(f); return this; }
    @SneakyThrows public PackyBuffer writeDouble(double d) { requireOutputStream().writeDouble(d); return this; }
    @SneakyThrows public PackyBuffer writeChar(char c) { requireOutputStream().writeChar(c); return this; }
    @SneakyThrows public PackyBuffer writeString(String s) { requireOutputStream().writeUTF(s); return this; }

    /* == READ == */
    @SneakyThrows public byte readByte() { return requireInputStream().readByte(); }
    @SneakyThrows public boolean readBoolean() { return requireInputStream().readBoolean(); }
    @SneakyThrows public short readShort() { return requireInputStream().readShort(); }
    @SneakyThrows public int readInt() { return requireInputStream().readInt(); }
    @SneakyThrows public long readLong() { return requireInputStream().readLong(); }
    @SneakyThrows public float readFloat() { return requireInputStream().readFloat(); }
    @SneakyThrows public double readDouble() { return requireInputStream().readDouble(); }
    @SneakyThrows public char readChar() { return requireInputStream().readChar(); }
    @SneakyThrows public String readString() { return requireInputStream().readUTF(); }

    @SneakyThrows
    public byte[] result() {
        if(byteOutputStream == null || dataOutputStream == null) { return new byte[0]; }
        dataOutputStream.flush();
        byteOutputStream.flush();
        return byteOutputStream.toByteArray();
    }

    @SneakyThrows
    public void close() {
        if(byteOutputStream != null) { byteOutputStream.close(); }
        if(dataOutputStream != null) { dataOutputStream.close(); }
        if(byteInputStream != null) { byteInputStream.close(); }
        if(dataInputStream != null) { dataInputStream.close(); }
    }

}
