/**
 * @author: Harshita Karande
 * Lowest Common Ancestor of node u and v is the deepest node that has both u and v as descendants
 * This class implements 2 solutions for LCA
 * The first solution finds the LCA in O(n) time
 * The second solution relies on parent pointers.
 * With parent pointers, we could find the same solution in O(h) time where h is the height of the tree
 */
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int data) {
        this.setData(data);
    }
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }


}
public class LowestCommonAncestor {
    //Get height of node
    public static int getHeight(TreeNode node) {
         int height = 0;

         while (node != null) {
             height++;
             node = node.getParent();
         }
        return height;
    }


    //Recursive solution that finds LCA in O(n) time.
    public static TreeNode getLCA(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null)
            return null;

        if (root == node1 || root == node2)
            return root;

        TreeNode left = getLCA(root.getLeft(), node1, node2);
        TreeNode right = getLCA(root.getRight(), node1, node2);

        //We only find LCA when we have some value in left as well as right
        if (left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    //Non-recursive solution that leverages parent pointer to find LCA in O(h) time.
    public static TreeNode getLCAUsingParentPointer(TreeNode node1, TreeNode node2) {
        int hn1 = getHeight(node1);
        int hn2 = getHeight(node2);

        //make second node the deeper node.
        //Swap the heights and the nodes
        if (hn1 > hn2) {
            TreeNode tempNode = node1;
            node1 = node2;
            node2 = tempNode;
        }

        //Lets get node2 at the same level as the shallower node
        for (int i = 1; i < Math.abs(hn1 - hn2); i++) {
            node2 = node2.getParent();
        }

        //Now node1 and node2 are at the same height.
        // Just keep moving up simultaneously until we get a common node
        while (node1 != null && node2 != null) {
            if (node1 == node2)
                return node1;
            node1 = node1.getParent();
            node2 = node2.getParent();
        }

        return null; // No LCA found
    }

    public static void main(String args[]) {
        TreeNode tn = new TreeNode(8);
        TreeNode tn1 = new TreeNode(5);
        TreeNode tn2 = new TreeNode(4);
        TreeNode tn3 = new TreeNode(7);
        TreeNode tn4 = new TreeNode(10);
        TreeNode tn5 = new TreeNode(9);
        TreeNode tn6 = new TreeNode(11);
        TreeNode tn7 = new TreeNode(13);

        //Set right subtree
        tn6.setRight(tn7);
        tn4.setLeft(tn5);
        tn4.setRight(tn6);
        tn.setRight(tn4);

        //Set left subtree
        tn1.setLeft(tn2);
        tn1.setRight(tn3);
        tn.setLeft(tn1);

        //Set parent pointers of left subtree
        tn2.setParent(tn1);
        tn3.setParent(tn1);
        tn1.setParent(tn);

        //Set parent pointers of right subtree
        tn7.setParent(tn6);
        tn6.setParent(tn4);
        tn5.setParent(tn4);
        tn4.setParent(tn);

        System.out.println("The LCA of 7 and 9 using recursive solution is: " + LowestCommonAncestor.getLCA(tn, tn3, tn5).getData());

        System.out.println("The LCA of 7 and 9 using parent pointer solution is: " + LowestCommonAncestor.getLCAUsingParentPointer(tn3, tn5).getData());

    }
}
