package cs240.List;

public class ListArray<T> implements ListInterface<T> {
	T[] list;
	int numEntries;
	@SuppressWarnings("unchecked")
	public ListArray(){
		list = (T[]) new Object[10];
		numEntries = 0;
	}
	@Override
	public void add(T newEntry) {
		if(numEntries < 10){
			list[numEntries] = newEntry;
			numEntries = numEntries++;
		}
		else{
			System.out.println("Nothing was added since the list is full.");
		}
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if(newPosition >=1 && newPosition <=numEntries+1 && numEntries <10){
			if(newPosition <= numEntries){
				for(int i = numEntries-1; i>= newPosition-1;i--){
					list[i+1]= list[i];
				}
			}
			list[newPosition-1] = newEntry;
			numEntries = numEntries++;
		}
		else if (newPosition <1 || newPosition > getLength()+1){
			throw new IndexOutOfBoundsException();
		}
		else{
			System.out.println("Array is full");
		}
	}

	@Override
	public T remove(int givenPosition) {
		T temp = null;
		if(givenPosition <=numEntries && givenPosition >=1 && !isEmpty()){
			temp = list[givenPosition-1];
			if(givenPosition < numEntries){
				for(int i = givenPosition -1; i < numEntries-1; i++){
					list[i] = list[i+1];
				}
			}
			numEntries = numEntries --;
		}
		else if(givenPosition < 1 && givenPosition > getLength()){
			throw new IndexOutOfBoundsException();
		}
		return temp;
	}

	@Override
	public void clear() {
		while(!isEmpty()){
			remove(numEntries);
			numEntries = numEntries--;
		}
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		T temp = null;
		if(givenPosition <= numEntries && givenPosition >=1){
			temp = list[givenPosition-1];
			list[givenPosition-1]= newEntry;
		}
		else if(givenPosition < 1 && givenPosition > getLength()){
			throw new IndexOutOfBoundsException();
		}
		return temp;
	}

	@Override
	public T getEntry(int givenPosition) {
		if(givenPosition >=1 && givenPosition <= numEntries){
			return list[givenPosition-1];
		}
		else if(givenPosition < 1 && givenPosition > getLength()){
			throw new IndexOutOfBoundsException();
		}
		else
			return null;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[getLength()];
		for(int i = 0; i < getLength();i++){
			temp[i] = list[i];
		}
		return temp;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean temp = false;
		for(int i = 0; i < getLength();i++){
			if(temp == false){
				if(anEntry.equals(list[i])){
					temp = true;
				}
			}
		}
		return temp;
	}

	@Override
	public int getLength() {
		return numEntries;
	}

	@Override
	public boolean isEmpty() {
		return numEntries == 0;
	}

}
