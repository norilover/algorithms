/***
 * Way1: 
 *	使用count来存储元素的数量，进而作为队列满时的判断条件；
 * 	队尾元素的位置可以通过((headIndex + count - 1) % capacity) 推出。
 *  [Learn]
 */
class CircularQueue{
	
	public static void main(String[] args){

	}

	private int[] queue;
	private int headIndex;
	private int count;
	private int capacity;

	public CircularQueue(int k){
		this.capacity = k;
		this.queue = new int[k];
		this.headIndex = 0;
		this.count = 0;
	}

	public boolean enQueue(int value){
		if(this.count == this.capacity){
			retun false;
		}

		this.queue[(this.headIndex + this.count) % this.capacity] = value;
		this.count++;

		return true;
	}

	public boolean deQueue(){
		if(this.count == 0){
			return false;
		}

		this.headIndex = (this.headIndex + 1) % this.capacity;
		this.count -= 1;

		return true;
	}

	public int getFront(){
		if(this.count == 0){
			return -1;
		}

		return this.queue[this.headIndex];
	}

	public int getRear(){
		if(this.count == 0){
			reutn -1;
		}

		int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
		return this.queue[this.tailIndex];
	}

	public boolean isEmpty(){
		return (this.count == 0);
	}

	public boolean isFull(){
		return (this.count == this.capacity);
	}
}


/***
 * Way2: 
 *	留一个位置作为队列满时的判断条件。
 */
class CircularQueue1 {
  
    private int[] qArr;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
      // 留一个元素作队满判断
      size = k + 1;
      qArr = new int[size];
       
      // head指向队头元素所在的位置。
      head = 0;   
      // tail指向队尾元素所在位置的下一个位置。
      tail = 0;
       
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()){
          return false;
        }
        
        qArr[tail] = value;
      
        // 指向向队尾元素的下一个位置。
        tail = (tail + 1) % size;   
      
        return true; 
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
      if(isEmpty()){
        return false;
      }
      
      head = (head + 1) % size;
      
      return true;
    }
    
    /** Get the front item from the queue. */
    public int getFront() {
      if(isEmpty()){
        return -1;
      }
      
      return qArr[head];
    }
    
    /** Get the last item from the queue. */
    public int getRear() {
      if(isEmpty()){
        return -1;
      }
      
      int tTail = tail == 0 ? size - 1 : tail - 1;  
      return qArr[tTail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
      return head == tail;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
      return ((tail + 1) % size) == head;
    }
}


/***
 * Way3: 
 *	设置一个flag作为队列满时的判断条件, 类似Way2。
 */
class CircularQueue2 {
  
    private int[] qArr;
    private int head;
    private int tail;
    private int size;
    private boolean isFull;

    public CirclarQueue2(int k){
    	// 初始化。
    	size = k;
    	qArr = new int[size];
    	head = 0;
    	tail = 0;
    	isFull = false;
    }

    // 注意这里的处理顺序。
    public boolean enQueue(int value){
    	if(isFull){
    		return false;
    	}else{
			
			qArr[tail] = value;

			tail = (tail + 1) % size;
			// 队列空间已经用完。
			if(tail == head){
				isFull = true;
			}
			
			return true;
    	}
    }
    
    public boolean deQueue(){
    	if(isEmpty()){
    		return false;
    	}else{
    		head = (head + 1) % size;
    		// 只要有出队，出对后队列一定不会满。
    		isFull = false;
    		return true;
    	}
    }

    public int getFront(){
    	if(isEmpty()){
    		return -1;
    	}

    	return qArr[head];
    }

    public int getRear(){
    	if(isEmpty()){
    		return -1;
    	}


    	int tTail = tail == 0 ? size - 1 : tail - 1;
    	return qArr[tail];
    }

    public boolean isEmpty(){
    	// 注意队列满时的情况。
    	return head == tail && !isFull;
    }
 }
