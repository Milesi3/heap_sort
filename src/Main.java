public class Main<T extends Comparable<T>> {
    private Node<T> root;
    public static void main(String[] args) {
        Main<Integer> tree = new Main<>();
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);
        tree.printInOrder(); // выводит "10 20 30 40 50"
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        boolean isRed;

        Node(T value, boolean isRed) {
            this.value = value;
            this.isRed = isRed;
        }
    }

    public void add(T value) {
        root = add(root, value);
        root.isRed = false;
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) {
            return new Node<T>(value, true);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = add(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = add(node.right, value);
        } else {
            // значение уже существует в дереве
            return node;
        }

        // исправит любые нарушения свойств красно-черного дерева
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private boolean isRed(Node<T> node) {
        if (node == null) {
            return false;
        }
        return node.isRed;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;
        right.isRed = node.isRed;
        node.isRed = true;
        return right;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;
        left.isRed = node.isRed;
        node.isRed = true;
        return left;
    }

    private void flipColors(Node<T> node) {
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node<T> node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " ");
            printInOrder(node.right);
        }
    }
}


