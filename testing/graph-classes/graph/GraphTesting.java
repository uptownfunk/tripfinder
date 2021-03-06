package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author Ajai Sharma
 */
public class GraphTesting {

    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    public void testAddRemoveVertices(GraphObj g) {
        g.add();
        assertEquals(1, g.vertexSize());
        assertEquals(1, g.maxVertex());
        g.add();
        assertEquals(2, g.vertexSize());
        assertEquals(2, g.maxVertex());
        g.add();
        g.remove(2);
        assert (g.contains(1));
        assert (!g.contains(2));
        assert (g.contains(3));
        assertEquals(2, g.vertexSize());
        assertEquals(3, g.maxVertex());
        g.add();
        assert (g.contains(2));
        assertEquals(3, g.vertexSize());
        assertEquals(3, g.maxVertex());
        int j = 0;
        for (int i : g.vertices()) {
            j++;
            assert (i == j);
        }
        g.add();
        g.remove(3);
        j = 0;
        for (int i : g.vertices()) {
            j++;
            if (j == 3) {
                j++;
            }
            assert (i == j);
        }
        for (int i = 0; i < 100; i++) {
            g.remove(i);
        }
        for (int i = 0; i < 100; i++) {
            for (int q = 0; q < 100; q++) {
                g.remove(i, q);
            }
        }
    }

    @Test
    public void foo() {
        testAddRemoveVertices(new DirectedGraph());
        testAddRemoveVertices(new UndirectedGraph());
    }

    @Test
    public void testUndirectedEdges() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 6; i++) {
            g.add();
        }
        assertEquals(1, g.add(1, 6));
        g.add(6, 2);
        g.add(1, 2);
        g.add(1, 2);
        g.add(2, 1);
        g.add(1, 1);
        g.remove(1, 1);
        g.remove(1, 1);
        g.add(1, 1);
        assertEquals(4, g.edgeSize());
        assert (g.contains(2, 6));
        int[] temp = new int[] {6, 2, 1};
        int i = 0;
        for (int v : g.successors(1)) {
            assertEquals(temp[i], v);
            i++;
        }
        g.remove(1);
        assertEquals(1, g.edgeSize());
        g.add(1, 2);
        assertEquals(1, g.edgeSize());
        g.add();
        assert (g.contains(1));
        assert (!g.contains(1, 2));
    }

    @Test
    public void testDirectedEdges() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            g.add();
        }
        assert (g.isDirected());
        assertEquals(0, g.edgeSize());
        assertEquals(1, g.add(1, 6));
        g.add(1, 6);
        g.add(6, 2);
        g.add(1, 2);
        g.add(2, 1);
        g.add(1, 1);
        assertEquals(5, g.edgeSize());
        g.add(1, 1);
        assertEquals(5, g.edgeSize());
        assert (g.contains(1, 1));
        assert (!g.contains(2, 6));
        assert (g.contains(1, 2));
        int[] temp = new int[] {6, 2, 1};
        int i = 0;
        for (int v : g.successors(1)) {
            assertEquals(temp[i], v);
            i++;
        }
        temp = new int[] {2, 1};
        i = 0;
        for (int v : g.predecessors(1)) {
            assertEquals(temp[i], v);
            i++;
        }
        g.remove(1);
        assertEquals(1, g.edgeSize());
        g.add(1, 2);
        assertEquals(1, g.edgeSize());
        g.add();
        assert (g.contains(1));
        assert (!g.contains(1, 2));
    }
}
