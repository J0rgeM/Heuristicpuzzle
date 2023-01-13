import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

// Tests will go here
public class PuzzleUnitTests {
    @Test
    public void testConstructor() {
        BestFirst alg = new BestFirst();
        long startTime1 = System.nanoTime();
        Iterator<BestFirst.State> it1 = alg.solve(new Board("BBBBBBBEBRRRRRRRR"), new Board("RRERRBRRRBBBBRBBB"));
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        Iterator<BestFirst.State> it2 = alg.solve(new Board("BBBBBBBEBRRRRRRRR"), new Board("RRRRBRRREBBBBRBBB"));
        long endTime2 = System.nanoTime();

        long startTime3 = System.nanoTime();
        Iterator<BestFirst.State> it3 = alg.solve(new Board("BBBBBBBEBRRRRRRRR"), new Board("RRRRRRRREBBBBBBBB"));
        long endTime3 = System.nanoTime();

        long startTime4 = System.nanoTime();
        Iterator<BestFirst.State> it4 = alg.solve(new Board("BBBBBBBEBRRRRRRRR"), new Board("RRRRRRRREBBBBBBBB"));
        long endTime4 = System.nanoTime();

        long startTime5 = System.nanoTime();
        Iterator<BestFirst.State> it5 = alg.solve(new Board("BBBBBBBBERRRRRRRR"), new Board("RRRRRRRREBBBBBBBB"));
        long endTime5 = System.nanoTime();

        System.out.println((float) (endTime1 - startTime1) / 1000000000 + " Seconds");
        System.out.println((float) (endTime2 - startTime2) / 1000000000 + " Seconds");
        System.out.println((float) (endTime3 - startTime3) / 1000000000 + " Seconds");
        System.out.println((float) (endTime4 - startTime4) / 1000000000 + " Seconds");
        System.out.println((float) (endTime5 - startTime5) / 1000000000 + " Seconds");
        
        while (it1.hasNext()) {
            BestFirst.State i = it1.next();
            if (!it1.hasNext()) {
                System.out.println(i.getG());
                assertEquals(39, (int) i.getG());
            }
        }
        while (it2.hasNext()) {
            BestFirst.State i = it2.next();
            if (!it2.hasNext()) {
                System.out.println(i.getG());
                assertEquals(38, (int) i.getG());
            }
        }
        while (it3.hasNext()) {
            BestFirst.State i = it3.next();
            if (!it3.hasNext()) {
                System.out.println(i.getG());
                assertEquals(45, (int) i.getG());
            }
        }
        while (it4.hasNext()) {
            BestFirst.State i = it4.next();
            if (!it4.hasNext()) {
                System.out.println(i.getG());
                assertEquals(45, (int) i.getG());
            }
        }
        while (it5.hasNext()) {
            BestFirst.State i = it5.next();
            if (!it5.hasNext()) {
                System.out.println(i.getG());
                assertEquals(46, (int) i.getG());
            }
        }
    }
}