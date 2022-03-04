package commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    @Test
    public void checkConstructor() {
         Activity act1 = new Activity(1L,"Cook one egg",10);
         assertNotNull(act1);
    }
    @Test
    public void testEquals(){
        Activity act1 = new Activity(1L,"Cook one egg",10);
        Activity act2 = new Activity(1L,"Cook one egg",10);
        assertTrue(act1.equals(act2));
    }
    @Test
    public void testEqualHashCode(){
        Activity act1 = new Activity(1L,"Cook one egg",10);
        Activity act2 = new Activity(1L,"Cook one egg",10);
        assertTrue(act1.hashCode()==act2.hashCode());
    }
    @Test
    public void testNotEquals(){
        Activity act1 = new Activity(1L,"Cook one egg",10);
        Activity act2 = new Activity(2L,"Cook two egg",20);
        assertNotEquals(act1,act2);
    }
    @Test
    public void testNotEqualHashCode(){
        Activity act1 = new Activity(1L,"Cook one egg",10);
        Activity act2 = new Activity(2L,"Cook two egg",20);
        assertNotEquals(act1,act2);
    }
    @Test
    public void testToString(){
        Activity act1 = new Activity(1L,"Cook one egg",10);

        assertEquals(act1.toString(),"Activity{Id=1, text='Cook one egg', correctAnswer=10}");
    }
}
