package com.lvefunc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        class Entry {
            int parent = -1;
            TreeNode treeNode;

            Entry() {
            }

            Entry(TreeNode treeNode) {
                this.treeNode = treeNode;
            }

            Entry(int parent, TreeNode treeNode) {
                this.parent = parent;
                this.treeNode = treeNode;
            }
        }

        Map<Integer, Entry> entries = new HashMap<>();

        Arrays.stream(descriptions).forEach(description -> {
            int iParent = description[0];
            int iChild = description[1];
            boolean isLeft = switch (description[2]) {
                case 1 -> true;
                case 0 -> false;
                default -> throw new IllegalArgumentException();
            };

            if (!entries.containsKey(iParent)) {
                TreeNode parentNode = new TreeNode(iParent);
                Entry parentEntry = new Entry(parentNode);
                entries.put(iParent, parentEntry);
            }

            if (!entries.containsKey(iChild)) {
                TreeNode childNode = new TreeNode(iChild);
                Entry childEntry = new Entry(childNode);
                entries.put(iChild, childEntry);
            }

            Entry parentEntry = entries.get(iParent);
            Entry childEntry = entries.get(iChild);

            if (isLeft)
                parentEntry.treeNode.left = childEntry.treeNode;
            else
                parentEntry.treeNode.right = childEntry.treeNode;

            childEntry.parent = parentEntry.treeNode.val;
        });

        Entry root = entries.get(entries.keySet().stream().findFirst().orElseThrow(IllegalArgumentException::new));

        while (root.parent != -1)
            root = entries.get(root.parent);

        return root.treeNode;
    }
}
