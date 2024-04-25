package shuhuai.algorithm.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings({"unused"})
public class TreeHelper<NodeType> {
    private final Class<NodeType> clazz;

    public TreeHelper(Class<NodeType> clazz) {
        this.clazz = clazz;
    }

    public NodeType build(String[] str) throws Exception {
        NodeType root = null;
        Queue<NodeType> queue = new LinkedList<>();
        if (str.length > 0) {
            root = createNode(Integer.parseInt(str[0]));
        }
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty() && index < str.length) {
            NodeType current = queue.poll();
            if (!str[index].equals("null")) {
                NodeType left = createNode(Integer.parseInt(str[index]));
                setLeft(current, left);
                queue.offer(left);
            }
            index++;
            if (index < str.length && !str[index].equals("null")) {
                NodeType right = createNode(Integer.parseInt(str[index]));
                setRight(current, right);
                queue.offer(right);
            }
            index++;
        }
        return root;
    }

    private NodeType createNode(int val) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Constructor<NodeType> constructor = clazz.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);
        return constructor.newInstance(val);
    }

    private void setLeft(NodeType node, NodeType left) throws Exception {
        Field leftField = clazz.getDeclaredField("left");
        leftField.setAccessible(true);
        leftField.set(node, left);
    }

    private void setRight(NodeType node, NodeType right) throws Exception {
        Field rightField = clazz.getDeclaredField("right");
        rightField.setAccessible(true);
        rightField.set(node, right);
    }

    public String[] deBuild(NodeType root) throws Exception {
        List<String> result = new LinkedList<>();
        Queue<NodeType> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            NodeType current = queue.poll();
            if (current != null) {
                result.add(String.valueOf(getValue(current)));
                queue.offer(getLeft(current));
                queue.offer(getRight(current));
            } else {
                result.add("null");
            }
        }
        int lastIndex = 0;
        for (int i = 0; i < result.size(); i++) {
            if (!result.get(i).equals("null")) {
                lastIndex = i;
            }
        }
        result.subList(lastIndex + 1, result.size()).clear();
        return result.toArray(new String[0]);
    }

    private NodeType getNode(Field field, NodeType node) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(node);
        return clazz.cast(value);
    }

    private NodeType getLeft(NodeType node) throws Exception {
        Field leftField = clazz.getDeclaredField("left");
        return getNode(leftField, node);
    }

    private NodeType getRight(NodeType node) throws Exception {
        Field rightField = clazz.getDeclaredField("right");
        return getNode(rightField, node);
    }

    private int getValue(NodeType node) throws Exception {
        Field valField = clazz.getDeclaredField("val");
        valField.setAccessible(true);
        return (int) valField.get(node);
    }
}