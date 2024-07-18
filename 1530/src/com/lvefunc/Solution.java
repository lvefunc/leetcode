package com.lvefunc;

import java.util.*;

public class Solution {
    private Map<TreeNode, TreeNode> parents;
    private Set<TreeNode> leafs;
    private int result;

    public int countPairs(TreeNode root, int distance) {
        parents = new HashMap<>();
        leafs = new HashSet<>();
        result = 0;

        // initial traversal

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (Objects.nonNull(current.left)) {
                parents.put(current.left, current);
                stack.push(current.left);
            }

            if (Objects.nonNull(current.right)) {
                parents.put(current.right, current);
                stack.push(current.right);
            }

            if (Objects.isNull(current.left) && Objects.isNull(current.right)) leafs.add(current);
        }

        leafs.forEach(leaf -> dfs(new HashSet<>(), leaf, leaf, 0, distance));

        return result % 2 == 0 ? result / 2 : (result - 1) / 2 + 1;
    }

    private void dfs(Set<TreeNode> visited, TreeNode initial, TreeNode current, int depth, int distance) {
        visited.add(current);

        if (current != initial && leafs.contains(current)) result++;

        if (parents.containsKey(current) && !visited.contains(parents.get(current)) && depth < distance)
            dfs(visited, initial, parents.get(current), depth + 1, distance);
        if (Objects.nonNull(current.left) && !visited.contains(current.left) && depth < distance)
            dfs(visited, initial, current.left, depth + 1, distance);
        if (Objects.nonNull(current.right) && !visited.contains(current.right) && depth < distance)
            dfs(visited, initial, current.right, depth + 1, distance);
    }
}
