typedef struct{
	int *data; // 数据域
	int front;
	int rear;
	int size;
	int flag; // 标志队列是否满 1为满、0为不满
}CircularQueue;

bool isEmpty(CircularQueue *cq){
	if(cq->flag == 0 && cq->front == cq->rear){
		return true;
	}else{
		return false;
	}
}

bool isFull(CircularQueue *cq){
	if(cq->flag == 1){
		return ture;
	}else{
		return false;
	}
}

void queueFree(CircularQueue cq){
	free(cq->data);
	cq->data = NULL;

	free(cq);
	cq = NULL;
}

CircularQueue *queueInit(int k){
	if(k < 0){
		return NULL;
	}

	CircularQueue cq = (CircularQueue *)malloc(sizeof(CircularQueue));
	if(cq == NULL){
		return NULL;
	}

	cq->data = (int *)malloc(k * sizeof(int));
	if(cq->data == NULL){
		return NULL;
	}

	cq->front = cq->rear = 0;
	cq->size = k;
	cq->flag = 0;

	return cq;
}

bool enQueue(CircularQueue *cq, int val){
	if(isFull(dq)){
		return false;
	}

	cq->data[cq->rear] = val;
	cq->rear = ((cq->rear + 1) < cq->size) ? (cq->rear + 1) : 0;

	if(cq->rear == cq->front){
		cq->flag = 1;
	}

	return true;
}

int deQueue(CircularQueue *cq){
	if(isEmpty(cq)){
		return -1;
	}

	cq->data[cq->front] = 0;
	cq->front = ((cq->front + 1) < cq->size) ? (cq->front + 1) : 0;

	cq->flag = 0; // 只要删了元素数列就不满

	return true;
}

int getFront(CircularQueue *cq){
	if(isEmpty(cq)){
		reutn -1;
	}

	return cq->data[cq->front];
}

int getRear(CircularQueue *cq){
	if(isEmpty(cq)){
		return -1;
	}

	int i = (cq->rear == 0) ? (cq->size - 1) : (cq->rear - 1);

	return cq->data[i];
}
