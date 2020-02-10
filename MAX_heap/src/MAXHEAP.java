import java.util.ArrayList;
import java.util.List;

public class MAXHEAP<Z extends Comparable<Z>> {

    private List<Z> Max_Heap;

    public MAXHEAP() {
        this.Max_Heap = new ArrayList<Z>();
    }

    protected void filterdownMax(int start, int end) {
        int c = start;
        int l = 2*c + 1;
        Z result = Max_Heap.get(c);

        while(l <= end) {
            int result2 = Max_Heap.get(l).compareTo(Max_Heap.get(l+1));

            if(l < end && result2<0)
                l++;
            result2 = result.compareTo(Max_Heap.get(l));
            if(result2 >= 0)
                break;
            else {
                Max_Heap.set(c, Max_Heap.get(l));
                c = l;
                l = 2*l + 1;
            }
        }
        Max_Heap.set(c, result);
    }


    public int removeMax(Z data) {

        if(Max_Heap.isEmpty() == true)
            return -1;

        int index = Max_Heap.indexOf(data);
        if (index==-1)
            return -1;

        int size = Max_Heap.size();
        Max_Heap.set(index, Max_Heap.get(size-1));
        Max_Heap.remove(size - 1);

        if (Max_Heap.size() > 1)
            filterdownMax(index, Max_Heap.size()-1);

        return 0;
    }

    protected void filterupMax(int start) {
        int c = start;
        int p = (c-1)/2;
        Z result = Max_Heap.get(c);

        while(c > 0) {
            int result2 = Max_Heap.get(p).compareTo(result);
            if(result2 >= 0)
                break;
            else {
                Max_Heap.set(c, Max_Heap.get(p));
                c = p;
                p = (p-1)/2;
            }
        }
        Max_Heap.set(c, result);
    }

    public void insertMax(Z data) {
        int size = Max_Heap.size();
        Max_Heap.add(data);
        filterupMax(size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<Max_Heap.size(); i++)
            sb.append(Max_Heap.get(i) +" ");
        return sb.toString();
    }

    public void getMax()
    {
        System.out.println("ArrayList contains: " + Max_Heap);
        if (Max_Heap != null && !Max_Heap.isEmpty()) {
            System.out.println("Biggest element is: "
                    + Max_Heap.get(0));
            System.out.println("Smallest element is: "
                    + Max_Heap.get(Max_Heap.size() - 1));
        }
    }

    public void extractMax(){
        Max_Heap.remove(0);
    }


    public static void main(String[] args) {
        int i;
        int a[] = {30, 60, 320, 20, 80, 330, 15, 21, 43};
        MAXHEAP<Integer> treeM=new MAXHEAP<Integer>();
        System.out.printf("== ADD: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            treeM.insertMax(a[i]);
        }

        System.out.printf("\n== Max: %s", treeM);

        i=15;
        treeM.insertMax(i);
        System.out.printf("\n== insertMax: %d", i);
        System.out.printf("\n== Max: %s", treeM);

        i=43;
        treeM.removeMax(i);
        System.out.printf("\n== DELETE: %d", i);
        System.out.printf("\n== Max: %s", treeM);
        System.out.printf("\n");
        System.out.printf("\n");

        treeM.getMax();
        treeM.extractMax();
        System.out.printf("\n== Max: %s", treeM);
    }

}
