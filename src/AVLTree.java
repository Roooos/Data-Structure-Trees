
class AVLTree {
	AVLTreeNode root;
	int max = 7;

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.createTestTree();
		tree.print();
	}
	
	public AVLTree() {

	}

	public void createTestTree() {
		for (int i = 1; i <= max; i++)
			insert("" + i);
	}

	public void print() {
		printRec(root, "");
	}

	void printRec(AVLTreeNode node, String space) {
		if (node != null) {
			System.out.println(space + node.element);
			printRec(node.left, space + " ");
			printRec(node.right, space + " ");
		}
	}

	public boolean inTree(String e) {
		return inTreeRec(root, e);
	}

	private boolean inTreeRec(AVLTreeNode node, String e) {
		if (node == null) return false;
		else if (e.compareTo(node.element) > 0) 
			return inTreeRec(node.right, e);
		else if (e.compareTo(node.element) < 0) 
			return inTreeRec(node.left, e);
		else return true;
	}

	public void insert(String e) {
		root = insertRec(root, e);
	}

	public AVLTreeNode insertRec(AVLTreeNode node, String e) {
		if (node == null) 
			return new AVLTreeNode(e);
		else if (e.compareTo(node.element) < 0)
			node.left = insertRec(node.left, e);
		else if (e.compareTo(node.element) > 0)
			node.right = insertRec(node.right, e);
		else return node;

		increaseHeight(node);
		return balance(node, e);
	}

	//https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
	public AVLTreeNode balance(AVLTreeNode node, String e) {
		int balance = getBalance(node);

		if (balance > 1) {
			if (e.compareTo(node.left.element) > 0)
				node.left = rotLeft(node.left);
			return rotRight(node);
		}
		
		if (balance < -1) {
			if (e.compareTo(node.right.element) < 0)
				node.right = rotRight(node.right);
			return rotLeft(node);
		}
		
		return node;
	}
	
	int getBalance(AVLTreeNode node) {
		int left, right;

		if (node == null) return 0;

		if (node.left == null) left = 0;
		else left = node.left.height;

		if (node.right == null) right = 0;
		else right = node.right.height;

		return left - right;
	}

	public int maxHeight(AVLTreeNode node) {
		int left, right;

		if (node == null) return 0;

		if (node.left == null) left = 0;
		else left = node.left.height;

		if (node.right == null) right = 0;
		else right = node.right.height;

		if (left >= right) return left;
		else return right;
	}
	
	public void increaseHeight(AVLTreeNode node) {
		node.height = maxHeight(node) + 1;
	}

	public AVLTreeNode rotRight(AVLTreeNode node) {
		AVLTreeNode l = node.left;
		AVLTreeNode lr = l.right;

		l.right = node;
		node.left = lr;

		increaseHeight(node);
		increaseHeight(l);

		return l;
	}

	public AVLTreeNode rotLeft(AVLTreeNode node) {
		AVLTreeNode r = node.right;
		AVLTreeNode rl = r.left;

		r.left = node;
		node.right = rl;

		increaseHeight(node);
		increaseHeight(r);

		return r;
	}
}
