package com.lvefunc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

class Solution {
	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (isDeadEnd(root, target))
			return null;

		List<TreeNode> visited = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();

		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode current = stack.pop();

			if (visited.contains(current))
				continue;

			visited.add(current);

			if (!Objects.isNull(current.left))
				if (isDeadEnd(current.left, target))
					current.left = null;
				else
					stack.push(current.left);
			if (!Objects.isNull(current.right))
				if (isDeadEnd(current.right, target))
					current.right = null;
				else
					stack.push(current.right);
		}

		return root;
	}

	public boolean isDeadEnd(TreeNode root, int target) {
		if (root.val != target)
			return false;
		if (Objects.isNull(root.left) && Objects.isNull(root.right))
			return true;

		List<TreeNode> visited = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();

		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode current = stack.pop();

			if (visited.contains(current))
				continue;

			visited.add(current);

			if (!Objects.isNull(current.left))
				if (current.left.val == target)
					stack.push(current.left);
				else
					return false;

			if (!Objects.isNull(current.right))
				if (current.right.val == target)
					stack.push(current.right);
				else
					return false;
		}

		return true;
	}
}