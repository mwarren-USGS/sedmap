package gov.cida.sedmap.io.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StringValueIterator implements Iterator<String>, Iterable<String> {

	public static final List<String> EMPTY = new ArrayList<String>();

	protected Iterator<? extends Object> values;


	public StringValueIterator(Iterator<? extends Object> iterator) {
		values = iterator;
	}


	@Override
	public Iterator<String> iterator() {
		return this;
	}


	@Override
	public boolean hasNext() {
		return values.hasNext();
	}


	@Override
	public String next() {
		String val = null;
		Object obj = null;
		try {
			obj = values.next();
			val = obj.toString();
		} catch (Exception e) {
			throw new RuntimeException("Value could not be converted to String " + val,e);
		}

		return val;
	}


	@Override
	public void remove() {
		throw new RuntimeException("Not implemented.");
	}

}
