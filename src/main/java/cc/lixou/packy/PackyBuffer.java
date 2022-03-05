package cc.lixou.packy;

import lombok.SneakyThrows;
import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PackyBuffer {

    private byte[] bytes;

    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    public PackyBuffer(byte[] bytes) {
        this.bytes = bytes;
    }

    public ByteArrayOutputStream requireOutputStream() {
        if(outputStream == null) { outputStream = new ByteArrayOutputStream(); }
        return outputStream;
    }
    public ByteArrayInputStream requireInputStream() {
        if(inputStream == null) { inputStream = new ByteArrayInputStream(bytes); }
        return inputStream;
    }

    /* == WRITE == */
    public PackyBuffer write(Serializable serializable) { writeBytes(SerializationUtils.serialize(serializable)); return this; }

    @SneakyThrows
    public PackyBuffer writeBytes(byte[] bytes) { requireOutputStream().write(bytes); return this; }
    public PackyBuffer writeByte(byte b) { write(b); return this; }
    public PackyBuffer writeBoolean(boolean b) { write(b); return this; }
    public PackyBuffer writeShort(short s) { write(s); return this; }
    public PackyBuffer writeInt(int i) { write(i); return this; }
    public PackyBuffer writeLong(long l) { write(l); return this; }
    public PackyBuffer writeFloat(float f) { write(f); return this; }
    public PackyBuffer writeDouble(double d) { write(d); return this; }
    public PackyBuffer writeChar(char c) { write(c); return this; }
    public PackyBuffer writeString(String s) { write(s.getBytes(StandardCharsets.UTF_8)); return this; }

    /* == READ == */
    @SneakyThrows
    public byte[] readNBytes(int length) { return requireInputStream().readNBytes(length); }
    public byte[] readAll() { return requireInputStream().readAllBytes(); }

    @SneakyThrows
    public boolean readBoolean() {
        System.out.println(Arrays.toString(requireInputStream().readNBytes(1)));
        return false;
    }

    @SneakyThrows
    public byte[] result() {
        if(outputStream == null) { return new byte[0]; }
        byte[] result = outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();
        if(inputStream != null) { inputStream.close(); }
        return result;
    }

}
