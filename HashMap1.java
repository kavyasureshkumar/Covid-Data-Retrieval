import java.util.Objects;

public class HashMap1 {
	// Defining a data item consisting data and key,
	// based on which the search is to be conducted in a hash table.
	class Data {
		int key;
		String value;

		// this function allocates key and value to this class
		public Data(int k, String v) {
			key = k;
			value = v;
		}
	};

	// linked list node
	class Node {
		Data data;
		Node next, prev;

		// constructor that allocates new node with given data , next and previous node
		public Node(Data d) {
			data = d;
			next = prev = null;
		}

	}

	Node[] map;

	// this method allocates size for thehashmap array
	public HashMap1(int size) {
		map = new Node[size];
	}

	// this method will return the hash index for the given key
	public int hash(Data n) {
		return n.hashCode() % map.length;
	}

	// this method is to insert data in the hashlist
	public void put(int k, String v) {
		// this will allocate
		Data d = new Data(k, v);
		// get the hash index of key
		int h = Math.abs(hash(d));
		// allocate a new node with the data
		Node n = new Node(d);
		// if the data in that index is null , allocated node will be inserted in hash
		// list
		if (map[h] == null)
			map[h] = n;
		else {
			// else it will traverse through the link till the end ,node will be appended at
			// the end
			Node ptr = map[h];
			for (; ptr.next != null; ptr = ptr.next)
				;
			ptr.next = n;
			n.prev = ptr;
		}
	}

	// this function to get the key(date) for the value(country)
	public int get(String v) {
		int key = 0;
		Node ptr;

		for (int i = 0; i < map.length; i++) {
			// check if the node in int hash list is not null
			if (!Objects.isNull(map[i])) {
				ptr = map[i];
				/*
				 * if the hashcode of the value of data in the list is equal to hashcode of
				 * value it will return key for that value
				 */
				if ((map[i].data.value).hashCode() == v.hashCode()) {
					key = map[i].data.key;
				}
				/*
				 * traverse through the linked list in the hash map and check if the hashcode of
				 * the value of data in the list is equal to hashcode of given value it will
				 * return key for that value
				 */
				while (ptr.next != null) {
					if (ptr.next.data.value.hashCode() == v.hashCode()) {
						key = ptr.next.data.key;
					}
					;
					ptr = ptr.next;
				}
			}
		}
		return (key);
	}


}
