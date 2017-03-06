package cs240.ListSorted;

import cs240.List.LinkedDataList;

public class LinkedDataListSorted<T extends Comparable<? super T>> extends LinkedDataList<T> implements SortedListInterface<T>{
	@Override
	public void add(T newEntry){
		int index = Math.abs(getPosition(newEntry));
		super.add(index, newEntry);
	}
	@Override
	public boolean remove(T anEntry) {
		boolean temp = false;
		int index = getPosition(anEntry);
		if(index > 0){
			super.remove(index);
			temp = true;
		}
		return temp;
	}
	@Override
	public int getPosition(T anEntry) {
		int index = 1;
		int length = super.getLength();
		while(index<=length && anEntry.compareTo(super.getEntry(index))>0){
			index = index++;
		}
		if(index > length || anEntry.compareTo(super.getEntry(index))!=0){
			index = -index;
		}
		return index;
	}
}
