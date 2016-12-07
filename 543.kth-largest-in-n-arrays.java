
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Arrays;


class Node {
    public int value, id, id_lst;

    public Node(int value, int id, int id_lst) {
        this.value = value;
        this.id = id;
        this.id_lst = id_lst;
    }
}


public class Solution {
    /**
     * @param arrays a list of array
     * @param k      an integer
     * @return an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        //
        Queue<Node> queue = new PriorityQueue<Node>(k, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                if (n1.value > n2.value) {
                    return -1;
                } else if (n1.value < n2.value) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        int n_lst = arrays.length;

        for (int idx_lst=0; idx_lst<n_lst; idx_lst++) {
            if (arrays[idx_lst].length > 0) {
                // sort
                Arrays.sort(arrays[idx_lst]);

                // push the max of each queue
                int id_max = arrays[idx_lst].length - 1;
                int value = arrays[idx_lst][id_max];
                queue.add(new Node(value, id_max, idx_lst));
            }
        }

        for (int i=0; i<k; i++) {
            // pop the max of the queue
            Node n = queue.poll();

            if (i == k - 1) {
                return n.value;
            }

            if (n.id > 0) {
                int id = n.id - 1;
                int value = arrays[n.id_lst][id];
                queue.add(new Node(value, id, n.id_lst));
            }
        }

        return -1;
    }
}