
	//An AVL tree node 
	public class TNode {
		public int data, confirmed, dead, recovered, height;
		public String date;
		public TNode right, left;
		/* Helper function(constructor) that allocates a  
		   new node(date) with the given data ,
		   confirmed ,death ,recover cases,
		   NULL left and right pointers. */
		public TNode(int e, int confirm, int death, int recover, String da) {
			data = e;
			confirmed = confirm;
			dead = death;
			recovered = recover;
			date = da;
			height = 1;
			right = left = null;
		}

	}

