package com.kritsn.lld.designPattern;

import java.util.ArrayDeque;
import java.util.Deque;

class Editor {
    private String content = "";

    void type(String text) {
        content += text;
    }

    // Memento is now a NESTED class of Editor — same top-level class body,
    // so private access between them is legal
    static class EditorMemento {
        private final String content; // still private — outside world still can't touch this

        private EditorMemento(String content) { // still private constructor
            this.content = content;
        }
    }

    // Editor CAN reach into EditorMemento's private field directly now — same enclosing top-level class
    EditorMemento save() {
        return new EditorMemento(this.content);
    }

    void restore(EditorMemento memento) {
        this.content = memento.content; // legal — Editor and EditorMemento share the same top-level class body
    }

    String getCurrentContent() { return content; }
}

class EditorHistory {
    private final Deque<Editor.EditorMemento> history = new ArrayDeque<>();

    void push(Editor.EditorMemento memento) { history.push(memento); }
    Editor.EditorMemento pop() { return history.pop(); }
    boolean isEmpty() { return history.isEmpty(); }
}

public class _21MementoPatternDemo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        EditorHistory history = new EditorHistory();

        editor.type("Hello");
        history.push(editor.save());

        editor.type(", World");
        history.push(editor.save());

        editor.type("!!!");
        System.out.println("Current: " + editor.getCurrentContent());

        editor.restore(history.pop());
        System.out.println("After undo: " + editor.getCurrentContent());

        editor.restore(history.pop());
        System.out.println("After undo again: " + editor.getCurrentContent());
    }
}