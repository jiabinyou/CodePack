package Tree.Plan9;

import Tree.TreeNode;

/** preOrder + special signal # denote NULL node
 * time O(n)
 * space O(n)
 *
 * step1: clarification
 * //each node contains integer or other types? single digit?
 * //the serialization result is just String or other form?
 * duplicate values in tree?(if use special signal, then does not matter, only preorder + inorder method need no duplicate)
 * //online/offline?
 *
 * step 2.serializaition
 * use preorder traverse tree recursively + special signal # for null node
 * 使用special signal表示null node原因：
 * 如果没有special node表示null，e.g.
 *      1
 *     / \
 *    2   3
 *       / \
 *      4   5
 * serilize以后：1 2 3 4 5
 * 重建tree以后，可能是：
 *      1                   1
 *     / \                /    \
 *    2   3              2     3
 *   / \                      /  \
 * 4   5                     4   5
 *导致重建的tree不唯一，所以使用special signal放置在null node，帮助我们判定node是在左子树还是右子树
 *TC:O(N)
 * SC:O(HEIGHT)
 *
 * step 3: deserialization (依然是recursively重建树)
 * 对于preOrder来说序列化tree过程是：
 * build root → build left subtree -> boundary -> build right subtree
 * 所以当我们重建tree的时候，就是要找到左右子树之间的boundary：
 * build root
 * build left subtree
 * meet boundary
 * start build right boundary
 *
 * 问题：怎样在序列化的数据上得到boudary信息？
 * e.g.
 * input: [1,2,3,null,null,4,5]
 * preOrder serialize后： 1,2,#,#,3,4,#,#,5,#,#
 *          1
 *        /    \
 *       2     3
 *      /  \
 *     4   5
 * 对于preOrder来说，一路走到mostLeft leaf node，一旦recursion开始return（即遇到base case），再去右子树
 * 这就意味着，在preorder中，base case之前处理的是root，left substree，base case之后处理的是right subtree
 * --》即：base case即为我们要找的boundary，而对于preOrder序列化后的tree数据，base case就是遇到#的时候
 * --》所以我们可以使用一个preOrder pointer来帮助我们什么时候遇到了base case
 * e.g.1,    2,   #,   #,    3,    4,    #,    #,   5,   #   , #            tree
 *     p                                                                      1
 *           p                                                                1
 *                                                                           /
 *                                                                          2
 *                 p  p     p                                                 1   (遇到base case，说明#结束后开始建立右子树)
 *                                                                           /  \
 *                                                                          2   3
 *                                 p                                          1
 *  *                                                                        /  \
 *  *                                                                       2   3
 *                                                                             /
 *                                                                            4
 *                                       p     p    p                         1   (遇到base case，说明#结束后开始建立右子树)
 *  *  *                                                                     /  \
 *  *  *                                                                    2   3
 *  *                                                                          /  \
 *  *                                                                         4   5
 *                                                       p      p->finish
 *
 *
 * */

public class SerializeAndDeserializeBinaryTree {
    private static final String SPLITTER = ",";
    private static final String NN = "#";  //NULL NODE
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        //base case
        if (root == null) {
            sb.append(NN);
            sb.append(SPLITTER);
            return;
        }
        //induction rule
        sb.append(root.val);
        sb.append(SPLITTER);
        //recursion
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    int preIdx = 0;
    public TreeNode deserialize(String data) {
        String[] arr = data.split(SPLITTER);
        return construct(arr);
    }

    private TreeNode construct(String[] arr) {
        if(arr[preIdx].equals(NN)) {
            preIdx++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(arr[preIdx]));
        preIdx++;
        root.left = construct(arr);
        root.right = construct(arr);
        return root;
    }
}
