package commons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class ImagePacketTest {

    private String content1 = "src/main/resources/fridge.png";
    private String fileName1;
    private String content2 = "src/main/resources/fridge.png";
    private String fileName2;
    private ImagePacket packet1;
    private ImagePacket packet2;


    @Disabled
    @BeforeEach
    void setUp() throws IOException {
        FileInputStream fileInputStream1 = new FileInputStream(content1);
        byte[] byteArr1 = new byte[(int) content1.length()];
        fileInputStream1.read(byteArr1);

        FileInputStream fileInputStream2 = new FileInputStream(content2);
        byte[] byteArr2 = new byte[(int) content2.length()];
        fileInputStream2.read(byteArr2);

        this.fileName1 = "funny name";
        this.fileName2 = "sweet dog picture";
        this.content1 = new String(Base64.getEncoder().encode(byteArr1), StandardCharsets.UTF_8);
        this.content2 = new String(Base64.getEncoder().encode(byteArr2), StandardCharsets.UTF_8);
        this.packet1 = new ImagePacket(new File(content1), fileName1);
        this.packet2 = new ImagePacket(new File(content2), fileName2);
    }

    @Disabled
    @Test
    void getByteArr() {
        byte[] array = Base64.getDecoder().decode(content1.getBytes(StandardCharsets.UTF_8));
        assertEquals(array, packet1.getByteArr());
    }

    @Disabled
    @Test
    void getFileName() {
        assertEquals("sweet dog picture", packet2.getFileName());
    }

    @Disabled
    @Test
    void setFileName() {
        packet1.setFileName("changed");
        assertEquals("changed", packet1.getFileName());
    }

    @Disabled
    @Test
    void getContent() {
        assertEquals(content1, packet1.getContent());
    }

    @Disabled
    @Test
    void setContent() {
        packet2.setContent(content1);
        assertEquals(content1, packet2.getContent());
    }

    @Disabled
    @Test
    void testEquals() throws IOException {
        byte[] byteArr1 = new byte[(int) content1.length()];
        String fileName3 = "funny name";
        String content3 = new String(Base64.getEncoder().encode(byteArr1), StandardCharsets.UTF_8);
        ImagePacket packet3 = new ImagePacket(new File(content1), fileName1);
        assertTrue(packet3.equals(packet1));
    }

    @Disabled
    @Test
    void testHashCode() throws IOException {
        byte[] byteArr1 = new byte[(int) content1.length()];
        String fileName3 = "funny name";
        String content3 = new String(Base64.getEncoder().encode(byteArr1), StandardCharsets.UTF_8);
        ImagePacket packet3 = new ImagePacket(new File(content1), fileName1);
        assertEquals(packet1.hashCode(), packet3.hashCode());
    }


}