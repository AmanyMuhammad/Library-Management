import javax.swing.table.DefaultTableModel;

public class WaitingList {
    public WaitingRequest[] waitingRequests;
    public int capacity;
    public int currentSize;

    public WaitingList(int capacity) {
        this.capacity = capacity;
        this.waitingRequests = new WaitingRequest[capacity];
        this.currentSize = 0;
    }

    public void swap(int index1,int index2){
        WaitingRequest temp=waitingRequests[index1];
        waitingRequests[index1]=waitingRequests[index2];
        waitingRequests[index2]=temp;
    }

    public int parent(int index){
        return (index-1)/2;
    }

    public int left(int index){
        return index*2+1;
    }

    public int right(int index){
        return index*2+2;
    }

    public void heapifyUp(int index){
        if(index==0)
            return;

        int parent=parent(index);
        if(waitingRequests[index].isGraduate() && !waitingRequests[parent].isGraduate()){
            swap(index,parent);
            heapifyUp(parent);
        }

        else if(waitingRequests[index].isGraduate()==waitingRequests[parent].isGraduate()){
            if(waitingRequests[index].getRequestDate().isBefore(waitingRequests[parent].getRequestDate())){
                swap(index,parent);
                heapifyUp(parent);
            }
        }
    }

    public void heapify(int index){
        int max=index;
        int left=left(index);
        int right=right(index);

        if(left<currentSize){
            if(waitingRequests[left].isGraduate() && !waitingRequests[max].isGraduate()){
                max=left;
            }

            else if(waitingRequests[left].isGraduate()==waitingRequests[max].isGraduate()){
                if(waitingRequests[left].getRequestDate().isBefore(waitingRequests[max].getRequestDate()))
                    max=left;
            }
        }

        if(right<currentSize){
            if(waitingRequests[right].isGraduate() && !waitingRequests[max].isGraduate()){
                max=right;
            }

            else if(waitingRequests[right].isGraduate()==waitingRequests[max].isGraduate()){
                if(waitingRequests[right].getRequestDate().isBefore(waitingRequests[max].getRequestDate()))
                    max=right;
            }
        }

        if(max!=index){
            swap(index,max);
            heapify(max);
        }

    }

    public void buildMaxHeap(){
        for(int i=currentSize/2-1;i>=0;i--){
            heapify(i);
        }
    }

    public boolean insert(WaitingRequest request){
        if(currentSize==capacity)
            return false;

        int index=currentSize;
        waitingRequests[index]=request;
        currentSize++;
        heapifyUp(index);

        return true;
    }

    public WaitingRequest extractMax(){
        if(currentSize==0)
            return null;

        if(currentSize==1){
            currentSize--;
            return waitingRequests[0];
        }

        WaitingRequest first=waitingRequests[0];
        waitingRequests[0]=waitingRequests[currentSize-1];
        currentSize--;
        heapify(0);

        return first;
    }

    public void fillTableFromHeap(int index, DefaultTableModel tableModel){
        if(index>=currentSize)
            return;

        fillTableFromHeap(left(index),tableModel);

        tableModel.addRow(new Object[]{
                waitingRequests[index].getStudentID(),
                waitingRequests[index].getStudentName(),
                waitingRequests[index].getBookISBN(),
                waitingRequests[index].getRequestDate(),
                (waitingRequests[index].isGraduate() ? "Graduate":"Student")
        });

        fillTableFromHeap(right(index),tableModel);
    }

}
