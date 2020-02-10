import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {

    private List<T> Min_Heap;

    public MinHeap() {
        this.Min_Heap = new ArrayList<T>();
    }

    protected void filterdown(int start, int end) {
        int current = start;
        int left = 2*current + 1;
        T result = Min_Heap.get(current);

        while(left <= end) {
            int result2 = Min_Heap.get(left).compareTo(Min_Heap.get(left+1));

            if(left < end && result2>0)
                left++;

            result2 = result.compareTo(Min_Heap.get(left));
            if(result2 <= 0)
                break;
            else {
                Min_Heap.set(current, Min_Heap.get(left));
                current = left;
                left = 2*left + 1;
            }
        }
        Min_Heap.set(current, result);
    }

    public int remove(T data) {

        if(Min_Heap.isEmpty() == true)
            return -1;

        int index = Min_Heap.indexOf(data);
        if (index==-1)
            return -1;

        int size = Min_Heap.size();
        Min_Heap.set(index, Min_Heap.get(size-1));
        Min_Heap.remove(size - 1);
        if (Min_Heap.size() > 1)
            filterdown(index, Min_Heap.size()-1);

        return 0;
    }

    protected void filterup(int start) {
        int current = start;
        int parennt = (current-1)/2;
        T result = Min_Heap.get(current);

        while(current > 0) {
            int result2 = Min_Heap.get(parennt).compareTo(result);
            if(result2 <= 0)
                break;
            else {
                Min_Heap.set(current, Min_Heap.get(parennt));
                current = parennt;
                parennt = (parennt-1)/2;
            }
        }
        Min_Heap.set(current, result);
    }

    public void insert(T data) {
        int size = Min_Heap.size();

        Min_Heap.add(data);
        filterup(size);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<Min_Heap.size(); i++)
            sb.append(Min_Heap.get(i) +" ");

        return sb.toString();
    }

    public void getMin()
    {
        System.out.println("ArrayList contains: " + Min_Heap);
        if (Min_Heap != null && !Min_Heap.isEmpty()) {
            System.out.println("Smallest element is: "
                    + Min_Heap.get(0));
            System.out.println("Biggest element is: "
                    + Min_Heap.get(Min_Heap.size() - 1));
        }
    }

    public void extractMin(){
        Min_Heap.remove(0);
    }

    public static void main(String[] args) {
        int i;
        int a[] = {30, 60, 320, 20, 80, 330, 15, 21, 43};
        MinHeap<Integer> tree=new MinHeap<Integer>();
        System.out.printf("== ADD: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== MIN: %s", tree);

        i=15;
        tree.insert(i);
        System.out.printf("\n== INSERT: %d", i);
        System.out.printf("\n== MIN: %s", tree);

        i=10;
        tree.remove(i);
        System.out.printf("\n== DELETE: %d", i);
        System.out.printf("\n== MIN: %s", tree);
        System.out.printf("\n");
        System.out.printf("\n");

        tree.getMin();
        tree.extractMin();
        System.out.printf("\n== MIN: %s", tree);
    }

}
