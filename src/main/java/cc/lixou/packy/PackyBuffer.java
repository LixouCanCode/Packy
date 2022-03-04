package cc.lixou.packy;

import lombok.SneakyThrows;
import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class PackyBuffer {

    private ByteArrayOutputStream outputStream;

    public PackyBuffer() {
        outputStream = new ByteArrayOutputStream();
    }

    /* == WRITE == */
    public PackyBuffer write(Serializable serializable) { writeBytes(SerializationUtils.serialize(serializable)); return this; }

    @SneakyThrows
    public PackyBuffer writeBytes(byte[] bytes) { outputStream.write(bytes); return this; }
    public PackyBuffer writeByte(byte b) { write(b); return this; }
    public PackyBuffer writeBoolean(boolean b) { write(b); return this; }
    public PackyBuffer writeShort(short s) { write(s); return this; }
    public PackyBuffer writeInt(int i) { write(i); return this; }
    public PackyBuffer writeLong(long l) { write(l); return this; }
    public PackyBuffer writeFloat(float f) { write(f); return this; }
    public PackyBuffer writeDouble(double d) { write(d); return this; }
    public PackyBuffer writeChar(char c) { write(c); return this; }
    public PackyBuffer writeString(String s) { write(s.getBytes(StandardCharsets.UTF_8)); return this; }

    @SneakyThrows
    public String result() {
        String result = outputStream.toString();
        outputStream.flush();
        outputStream.close();
        return result;
    }

}
