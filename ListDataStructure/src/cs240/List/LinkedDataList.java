package cs240.List;


public class LinkedDataList<T> implements ListInterface<T>{
	private Node firstNode;
	private int numberOfEntries;
	protected class Node{
		private T data; 
		private Node next; 
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node(T dataPortion){
			data = dataPortion;
			next = null;
		}
		public Node(T dataPortion, Node nextNode){
			data = dataPortion;
			next = nextNode;
		}
		
	}
	public LinkedDataList(){
		clear();
	}
	@Override
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()){
			firstNode = newNode;
		}
		else {
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.next = newNode;
		}
		numberOfEntries++;		
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if(newPosition >=1 && newPosition <= numberOfEntries+1){
			Node newNode = new Node(newEntry);
			if(newPosition ==1){
				newNode.next = firstNode;
				firstNode = newNode;
			}
			else{
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.next;
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			}
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public T remove(int givenPosition) {
		T result = null; 
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			if (givenPosition == 1){ 
				result = firstNode.data; 
				firstNode = firstNode.next;
			}
			else{
			Node nodeBefore = getNodeAt(givenPosition - 1);
			Node nodeToRemove = nodeBefore.next;
			Node nodeAfter = nodeToRemove.next;
			nodeBefore.next =nodeAfter;
			result = nodeToRemove.data; 
			}
			numberOfEntries--;
		}
		else{
			throw new IndexOutOfBoundsException();
		}		
		return result;
	}

	@Override
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		T temp = null;
		if(!isEmpty()){
			if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
				Node desiredNode = getNodeAt(givenPosition);
				temp = desiredNode.data;
				desiredNode.data=newEntry;
			}
			else{
				throw new IndexOutOfBoundsException();
			}
		}
		else{
			System.out.println("List is empty");
		}
		return temp;
	}

	@Override
	public T getEntry(int givenPosition) {
		T temp= null;
		if(!isEmpty()){
			if(givenPosition >=1 && givenPosition <= numberOfEntries){
				temp = getNodeAt(givenPosition).data;
			}
			else{
				throw new IndexOutOfBoundsException();
			}
		}
		return temp;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[numberOfEntries];
		Node tempNode = firstNode;
		for(int i = 0; i < numberOfEntries && tempNode!=null; i++){
			temp[i]	= tempNode.data;
			tempNode = tempNode.next;
		}
		return temp;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean temp = false;
		Node tempNode = firstNode;
		while(temp == false && tempNode!=null){
				if(anEntry.equals(tempNode.data))
					temp = true;
				else
					tempNode = tempNode.next;
		}
		return temp;
	}

	@Override
	public int getLength() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		return (numberOfEntries == 0 && firstNode == null);
		
	}
	private Node getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		for (int counter = 1; counter < givenPosition; counter++){
			currentNode = currentNode.next;
		}
		assert currentNode != null;
		return currentNode;
	} 
}
