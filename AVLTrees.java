
public class AVLTrees {

	public TNode root = null;

	// returns the height of a Tree Node ...
	public int height(TNode n) {
		if (n == null)
			return 0;

		return n.height;
	}
	// A utility function to get maximum
	// of two integers

	public int max(int x, int y) {
		return (x > y) ? x : y;
	}

	// return the balance state of a node ...
	public int getbalance(TNode n) {
		if (n == null)
			return 0;

		return height(n.left) - height(n.right);
	}

	// Right Rotate ...
	public TNode rightRotate(TNode z) {
		TNode y = z.left;
		TNode T3 = y.right;

		// Performing Right Rotation ...
		z.left = T3;
		y.right = z;

		// Updating heights
		z.height = 1 + max(height(z.left), height(z.right));
		y.height = 1 + max(height(y.left), height(y.right));

		return y;

	}

	// Left Rotate ...
	public TNode leftRotate(TNode z) {
		TNode y = z.right;
		TNode T2 = y.left;

		// Performing Left Rotation ...
		z.right = T2;
		y.left = z;

		// Updating heights
		z.height = 1 + max(height(z.left), height(z.right));
		y.height = 1 + max(height(y.left), height(y.right));
		return y;

	}

	// to check whether the tree is empty
	public boolean isEmpty() {
		return root == null;
	}

	// Insert into BST
	private TNode insert_rec(int e, int confirm, int death, int recover, String date, TNode r) {
		/*
		 * 1. Perform the normal BST insertion returning a new node with the date,
		 * confirmed, dead and recovered cases...
		 */

		if (r == null)
			return (new TNode(e, confirm, death, recover, date));

		else {
			// Key is smaller than root's key
			if (e < r.data) {
				r.left = insert_rec(e, confirm, death, recover, date, r.left);
			}
			// Key is greater than root's key
			else if (e > r.data) {
				r.right = insert_rec(e, confirm, death, recover, date, r.right);
			}
			/*
			 * if the current data is equal to the node in the tree ,confirmed ,death and
			 * recovered case of that node will be added with current one
			 */

			else if (e == r.data) {
				r.confirmed = r.confirmed + confirm;
				r.dead = r.dead + death;
				r.recovered = r.recovered + recover;
			} else
				return r;

			// Update height of current node...
			// This node is compromised by an insertion ...
			r.height = 1 + max(height(r.left), height(r.right));

			// Check is the node is in balance ...
			// that is ... the height difference between the left and right is 1
			int balance = getbalance(r);

			// Left Left case ...
			if (balance > 1 && e < r.left.data)
				return rightRotate(r);

			// Right Right case ...
			if (balance < -1 && e > r.right.data)
				return leftRotate(r);

			// Left Right case ...
			if (balance > 1 && e > r.left.data) {
				r.left = leftRotate(r.left);
				return rightRotate(r);
			}

			// Right Left case ...
			if (balance > 1 && e > r.left.data) {
				r.right = rightRotate(r.right);
				return leftRotate(r);
			}
			return r;

		}

	}

	// This method mainly calls insertRec()
	public void insert(int e, int confirm, int death, int recover, String date) {
		root = insert_rec(e, confirm, death, recover, date, root);
	}

	// this method will return the root of AVL tree
	public TNode root(AVLTrees T) {
		return (root);
	}

	// recursive function to delete key
	public TNode delete_rec(int key, TNode r) {
		/* Base Case: If the tree is empty */
		if (r == null)
			return r;
		else {
			// If the key to be deleted is smaller than
			// the root's key, then it lies in left subtree
			if (key < r.data)
				r.left = delete_rec(key, r.left);
			// If the key to be deleted is greater than the
			// root's key, then it lies in right subtree
			else if (key > r.data)
				r.right = delete_rec(key, r.right);
			// if key is same as root's key, then this is the node
			// to be deleted
			else {
				// node with only one child or no child
				if (r.left == null)
					return r.right;
				else if (r.right == null)
					return r.left;
				// node with two children: Get the inorder successor (smallest
				// in the right subtree)
				r.data = minRValue(r.right);
				// Delete the inorder successor
				r.right = delete_rec(r.data, r.right);
			}

		}
		// UPDATE HEIGHT OF THE CURRENT NODE
		root.height = max(height(root.left), height(root.right)) + 1;

		// GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = getbalance(root);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && getbalance(root.left) >= 0)
			return rightRotate(root);

		// Left Right Case
		if (balance > 1 && getbalance(root.left) < 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Right Case
		if (balance < -1 && getbalance(root.right) <= 0)
			return leftRotate(root);

		// Right Left Case
		if (balance < -1 && getbalance(root.right) > 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		return r;
	}

	private int minRValue(TNode r) {
		// loop to find left most leaf
		while (r.left != null)
			r = r.left;

		return r.data;
	}

	// calls delete _rec
	public void delete(int key) {
		root = delete_rec(key, root);
	}

	/*
	 * this method is to traverse through the tree and to return the node of the
	 * given key
	 */
	public TNode find(int key) {
		TNode current = root;
		while (current != null) {
			// check if the current node's data is equal to the given tree
			if (current.data == key) {
				break;
			}
			/*
			 * if the current node's data is less than key , it will return right node of
			 * current node otherwise it will return left node of current node
			 */

			current = current.data < key ? current.right : current.left;
		}
		return current;
	}
	
	public static void main(String[] args) {
		// BST tree = new BST();
		AVLTrees tree = new AVLTrees();

		tree.root(tree);
		TNode s = tree.find(15);
		System.out.println(s.data);

	}

}

