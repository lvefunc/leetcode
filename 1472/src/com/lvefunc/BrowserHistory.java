package com.lvefunc;

import java.util.ArrayList;
import java.util.List;

class BrowserHistory {
	private int ptr;
	private List<String> history = new ArrayList<>();

	public BrowserHistory(String homepage) {
		history.add(0, homepage);
	}

	public void visit(String url) {
		history.add(++ptr, url);

		if (history.size() > ptr + 1)
			for (int i = history.size() - 1; i > ptr; i--)
				history.remove(i);
	}

	public String back(int steps) {
		if (ptr - steps < 0)
			ptr = 0;
		else
			ptr = ptr - steps;

		return history.get(ptr);
	}

	public String forward(int steps) {
		if (ptr + steps >= history.size())
			ptr = history.size() - 1;
		else
			ptr = ptr + steps;

		return history.get(ptr);
	}
}