
/**
 * Author: Harshita Karande
 * Heapsort using arrays
 */
public class HeapSort extends Sorting{

    public static void main(String args[]){
        HeapSort heap=new HeapSort();
        heap.readInput();
        heap.heapSort();

    }

    public void heapify(int i,int N){
        int maxchild;
        int index;
        if((2*i)+1<=N){
            if(arr[2*i]>arr[(2*i)+1]){
                maxchild=arr[2*i];
                index=2*i;
            }
            else{
                maxchild=arr[(2*i)+1];
                index=(2*i)+1;
            }
            if(arr[i]<maxchild)  {
                int temp=arr[i];
                arr[i]=maxchild;
                arr[index]=temp;
                heapify(index,N);
            }
        }
        if((2*i)==N){
            if(arr[i]<arr[2*i])  {
                int temp=arr[i];
                arr[i]=arr[2*i];
                arr[2*i]=temp;
                heapify(2*i,N);

            }

        }


    }

    public void heapSort(){
        for(int i=(N/2);i>=1;i--){
            heapify(i,N);
        }

        for(int i=N;i>=2;i--){
            int temp=arr[1];
            arr[1]= arr[i];
            arr[i]=temp;
            heapify(1,i-1);
        }
         System.out.println("Sorted output");
        for(int i=1;i<=N;i++){
            System.out.println(arr[i]);
        }
    }
}
