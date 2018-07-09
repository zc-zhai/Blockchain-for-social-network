package ProducerAndConsumer.Version2;

/*
PCData - stored data struct
 */
public class PCData {
    private long value; // PCData has a characteristic - value
    public void set(long value){
        this.value = value;

    }
    public long get(){
        return value;
    }
}