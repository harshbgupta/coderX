package com.kritsn.ques.hashmap;

import java.util.*;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 13, 2025
 */


/**
 * MyHashMap - a simplified HashMap implementation that:
 * - uses separate chaining (linked lists) for collisions,
 * - converts (treeifies) a bucket into a red-black tree when chain grows beyond threshold,
 * - supports put/get/remove/size/containsKey,
 * - resizes when load factor exceeded.
 *
 * NOTE: This is educational and not fully optimized like OpenJDK HashMap.
 *
 * Key design constants mirror Java's choices:
 * - TREEIFY_THRESHOLD: when a bucket's linked list length >= this, convert to tree
 * - UNTREEIFY_THRESHOLD: revert to list when tree becomes small
 * - MIN_TREEIFY_CAPACITY: minimum table capacity before treeification (avoid treeifying tiny tables)
 */
public class MyHashMap<K, V> {

    /* ---------------- Constants ---------------- */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //1 << 4  →  1 × 2⁴  →  16
    static final int MAXIMUM_CAPACITY = 1 << 30; //1 << 30 -> 2³⁰ = 1,073,741,824
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // thresholds for converting between list and tree
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;
    static final int MIN_TREEIFY_CAPACITY = 64;

    /* ---------------- Fields ---------------- */
    transient Node<K, V>[] table;
    transient int size;
    int threshold;
    final float loadFactor;

    /* ---------------- Constructors ---------------- */

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) throw new IllegalArgumentException("Illegal initial capacity");
        if (initialCapacity > MAXIMUM_CAPACITY) initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) throw new IllegalArgumentException("Illegal load factor");

        // normalize capacity to power of two
        int cap = 1;
        while (cap < initialCapacity) cap <<= 1;
        this.loadFactor = loadFactor;
        this.threshold = (int) (cap * loadFactor);
        this.table = (Node<K, V>[]) new Node[cap];
    }

    /* ---------------- Node and TreeNode ---------------- */

    /**
     * Base node used in the buckets (linked list).
     */
    static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * TreeNode used when bucket is treeified (red-black tree node).
     * Extends Node to reuse key/hash/value/next storage.
     */
    static final class TreeNode<K, V> extends Node<K, V> {
        TreeNode<K, V> parent;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        boolean red;

        TreeNode(int hash, K key, V val, Node<K, V> next) {
            super(hash, key, val, next);
        }

        // Utility: returns root of tree containing this node
        TreeNode<K, V> root() {
            TreeNode<K, V> r = this;
            while (r.parent != null) r = r.parent;
            return r;
        }
    }

    /* ---------------- Basic helpers ---------------- */

    // Hash function similar to Java's HashMap (spread)
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // Returns index for hash in table
    static final int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    /* ---------------- Core operations ---------------- */

    public V get(K key) {
        int h = hash(key);
        Node<K, V>[] tab = table;
        int idx = indexFor(h, tab.length);
        Node<K, V> first = tab[idx];
        if (first == null) return null;
        if (first instanceof TreeNode) {
            TreeNode<K, V> t = (TreeNode<K, V>) first;
            TreeNode<K, V> node = getTreeNode(t, h, key);
            return node == null ? null : node.value;
        } else {
            for (Node<K, V> e = first; e != null; e = e.next) {
                if (e.hash == h && Objects.equals(e.key, key)) return e.value;
            }
            return null;
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V put(K key, V value) {
        int h = hash(key);
        Node<K, V>[] tab = table;
        int idx = indexFor(h, tab.length);
        Node<K, V> first = tab[idx];

        if (first == null) {
            tab[idx] = new Node<>(h, key, value, null);
            size++;
        } else {
            if (first instanceof TreeNode) {
                TreeNode<K, V> root = (TreeNode<K, V>) first;
                TreeNode<K, V> t = putTreeVal(root, tab, h, key, value);
                if (t == null) {
                    // put updated an existing key, nothing to increment
                } else {
                    size++;
                }
            } else {
                Node<K, V> e;
                int binCount = 0;
                for (e = first; e.next != null; e = e.next) {
                    binCount++;
                    if (e.hash == h && Objects.equals(e.key, key)) {
                        V oldVal = e.value;
                        e.value = value;
                        return oldVal;
                    }
                }
                // check last node
                if (e.hash == h && Objects.equals(e.key, key)) {
                    V oldVal = e.value;
                    e.value = value;
                    return oldVal;
                }
                // append new node
                e.next = new Node<>(h, key, value, null);
                binCount++;

                // if bin too long, treeify
                if (binCount >= TREEIFY_THRESHOLD - 1) { // -1 because we counted edges
                    treeifyBin(tab, idx);
                }
                size++;
            }
        }

        // resize if necessary
        if (size > threshold) resize();
        return null;
    }

    public V remove(K key) {
        int h = hash(key);
        Node<K, V>[] tab = table;
        int idx = indexFor(h, tab.length);
        Node<K, V> first = tab[idx];
        if (first == null) return null;

        if (first instanceof TreeNode) {
            TreeNode<K, V> root = (TreeNode<K, V>) first;
            TreeNode<K, V> node = getTreeNode(root, h, key);
            if (node == null) return null;
            V old = node.value;
            removeTreeNode(root, node, tab, idx);
            size--;
            return old;
        } else {
            Node<K, V> prev = null;
            Node<K, V> e = first;
            while (e != null) {
                if (e.hash == h && Objects.equals(e.key, key)) {
                    V old = e.value;
                    if (prev == null) {
                        tab[idx] = e.next;
                    } else {
                        prev.next = e.next;
                    }
                    size--;
                    return old;
                }
                prev = e;
                e = e.next;
            }
            return null;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    /* ---------------- Resize ---------------- */

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = oldTab.length;
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        int newCap = oldCap << 1;
        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        threshold = (int) (newCap * loadFactor);
        table = newTab;

        // rehash nodes into new table
        for (int i = 0; i < oldCap; i++) {
            Node<K, V> e = oldTab[i];
            if (e == null) continue;
            oldTab[i] = null;
            if (e.next == null) {
                // single node
                int idx = indexFor(e.hash, newCap);
                newTab[idx] = e;
            } else if (e instanceof TreeNode) {
                // For tree, we must split properly: for simplicity, we traverse nodes and reinsert into new table
                List<Node<K, V>> nodes = new ArrayList<>();
                // flatten tree to list (inorder not necessary)
                Queue<TreeNode<K, V>> q = new ArrayDeque<>();
                q.add((TreeNode<K, V>) e);
                while (!q.isEmpty()) {
                    TreeNode<K, V> t = q.poll();
                    nodes.add(new Node<>(t.hash, t.key, t.value, null));
                    if (t.left != null) q.add(t.left);
                    if (t.right != null) q.add(t.right);
                }
                for (Node<K, V> n : nodes) {
                    int idx = indexFor(n.hash, newCap);
                    n.next = newTab[idx];
                    newTab[idx] = n;
                }
            } else {
                // preserve order by pushing into new bucket (simple approach)
                Node<K, V> loHead = null, loTail = null;
                Node<K, V> hiHead = null, hiTail = null;
                Node<K, V> next;
                while (e != null) {
                    next = e.next;
                    if ((e.hash & oldCap) == 0) {
                        if (loTail == null) loHead = e;
                        else loTail.next = e;
                        loTail = e;
                    } else {
                        if (hiTail == null) hiHead = e;
                        else hiTail.next = e;
                        hiTail = e;
                    }
                    e = next;
                }
                if (loTail != null) {
                    loTail.next = null;
                    newTab[i] = loHead;
                }
                if (hiTail != null) {
                    hiTail.next = null;
                    newTab[i + oldCap] = hiHead;
                }
            }
        }
    }

    /* ---------------- Treeify / Untreeify ---------------- */

    // Convert bucket at index to a red-black tree
    private void treeifyBin(Node<K, V>[] tab, int index) {
        Node<K, V> b = tab[index];
        if (b == null) return;

        // If table too small, prefer resize rather than treeify (like Java)
        if (tab.length < MIN_TREEIFY_CAPACITY) {
            resize();
            return;
        }

        TreeNode<K, V> root = null;
        Node<K, V> e = b;
        while (e != null) {
            TreeNode<K, V> tn = new TreeNode<>(e.hash, e.key, e.value, null);
            if (root == null) {
                root = tn;
                root.red = false;
            } else {
                // insert tn into tree rooted at root
                K k = tn.key;
                int h = tn.hash;
                TreeNode<K, V> p = root;
                while (true) {
                    int dir;
                    K pk = p.key;
                    if (h < p.hash) dir = -1;
                    else if (h > p.hash) dir = 1;
                    else {
                        // if same hash, try comparing keys when possible
                        dir = tieBreakOrder(k, pk);
                    }
                    if (dir <= 0) {
                        if (p.left == null) {
                            p.left = tn;
                            tn.parent = p;
                            break;
                        } else p = p.left;
                    } else {
                        if (p.right == null) {
                            p.right = tn;
                            tn.parent = p;
                            break;
                        } else p = p.right;
                    }
                }
                // fix red-black properties after insertion
                root = balanceInsertion(root, tn);
            }
            e = e.next;
        }

        // put tree root into bucket (note: in Java root is placed at head)
        tab[index] = root;
    }

    // Convert a tree bin back to a linked list if needed (not used heavily here)
    private void untreeifyBin(Node<K, V>[] tab, int index) {
        Node<K, V> e = tab[index];
        if (!(e instanceof TreeNode)) return;
        // flatten tree to linked list (simple BFS)
        Queue<TreeNode<K, V>> q = new ArrayDeque<>();
        q.add((TreeNode<K, V>) e);
        Node<K, V> head = null;
        Node<K, V> tail = null;
        while (!q.isEmpty()) {
            TreeNode<K, V> t = q.poll();
            Node<K, V> n = new Node<>(t.hash, t.key, t.value, null);
            if (head == null) head = n;
            else tail.next = n;
            tail = n;
            if (t.left != null) q.add(t.left);
            if (t.right != null) q.add(t.right);
        }
        tab[index] = head;
    }

    /* ---------------- Tree helpers (search/insert/delete + balancing) ---------------- */

    // Returns TreeNode for key
    private TreeNode<K, V> getTreeNode(TreeNode<K, V> root, int h, Object key) {
        TreeNode<K, V> p = root;
        while (p != null) {
            int ph = p.hash;
            if (h < ph) p = p.left;
            else if (h > ph) p = p.right;
            else { // hashes equal, compare keys
                K pk = p.key;
                if (Objects.equals(pk, key)) return p;
                // try comparing comparable
                int dir = tieBreakOrder((K) key, pk);
                p = (dir <= 0) ? p.left : p.right;
            }
        }
        return null;
    }

    // Insert key/value into tree rooted at root. Returns inserted TreeNode if inserted new,
    // or null if updated existing key (so caller knows whether to increment size).
    private TreeNode<K, V> putTreeVal(TreeNode<K, V> root, Node<K, V>[] tab, int h, K k, V v) {
        TreeNode<K, V> p = root;
        while (true) {
            int ph = p.hash;
            int dir;
            if (h < ph) dir = -1;
            else if (h > ph) dir = 1;
            else {
                dir = tieBreakOrder(k, p.key);
            }
            if (dir <= 0) {
                if (p.left != null) p = p.left;
                else {
                    TreeNode<K, V> x = new TreeNode<>(h, k, v, null);
                    p.left = x;
                    x.parent = p;
                    root = balanceInsertion(root, x);
                    // ensure root at bucket head
                    int idx = indexFor(root.hash, tab.length);
                    tab[idx] = root;
                    // if a tree grows too big or small, handle separately (we already are tree)
                    return x;
                }
            } else {
                if (p.right != null) p = p.right;
                else {
                    TreeNode<K, V> x = new TreeNode<>(h, k, v, null);
                    p.right = x;
                    x.parent = p;
                    root = balanceInsertion(root, x);
                    int idx = indexFor(root.hash, tab.length);
                    tab[idx] = root;
                    return x;
                }
            }
            // check equal key during traversal (hash equal but equals true)
            if (p.hash == h && Objects.equals(p.key, k)) {
                p.value = v; // update
                return null;
            }
        }
    }

    // Remove tree node, rebalance, possibly untreeify
    private void removeTreeNode(TreeNode<K, V> root, TreeNode<K, V> node, Node<K, V>[] tab, int index) {
        // Standard BST deletion: if node has two children, replace with successor
        if (node.left != null && node.right != null) {
            TreeNode<K, V> s = successor(node);
            // swap contents
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash; // note: hash is final in Node type -- but in our Node it's final, so we can't reassign.
            // Because we made hash final in Node, we cannot swap hash. To keep things simple,
            // instead we will swap key/value only and delete successor by recursion.
            // (In production code you'd not have hash final or you'd handle replacement differently.)
            // We'll keep key/value swap and then delete successor using node = s
            // (we already copied values).
            node = s;
        }

        // Now node has at most one child
        TreeNode<K, V> replacement = (node.left != null) ? node.left : node.right;
        if (replacement != null) {
            // link replacement into parent
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            node.left = node.right = node.parent = null;

            if (!node.red) {
                // fix colors
                root = balanceDeletion(root, replacement);
            }
        } else if (node.parent == null) {
            // node is root and no children
            root = null;
        } else {
            // node has no children and is not root
            if (!node.red) {
                root = balanceDeletion(root, node);
            }

            if (node.parent != null) {
                if (node == node.parent.left) node.parent.left = null;
                else if (node == node.parent.right) node.parent.right = null;
                node.parent = null;
            }
        }

        // put possibly new root into table
        if (root != null) {
            tab[index] = root.root();
        } else {
            tab[index] = null;
        }

        // Optionally, if tree now small, untreeify
        // For simplicity, if size of this tree < UNTREEIFY_THRESHOLD convert back.
        // We'll not count exact size here; leave as-is or implement if needed.
    }

    // Successor (inorder) of given node
    private TreeNode<K, V> successor(TreeNode<K, V> t) {
        if (t == null) return null;
        else if (t.right != null) {
            TreeNode<K, V> p = t.right;
            while (p.left != null) p = p.left;
            return p;
        } else {
            TreeNode<K, V> p = t.parent;
            TreeNode<K, V> ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    /* ---------------- Red-Black balancing utilities ---------------- */

    // After inserting x, rebalance and return new root.
    private TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x) {
        x.red = true;
        while (x != null && x != root && x.parent.red) {
            TreeNode<K, V> xp = x.parent;
            TreeNode<K, V> xpp = xp.parent;
            if (xpp == null) break;
            if (xp == xpp.left) {
                TreeNode<K, V> y = xpp.right;
                if (y != null && y.red) {
                    xp.red = false;
                    y.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.right) {
                        x = xp;
                        root = rotateLeft(root, x);
                        xp = x.parent;
                        xpp = xp == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateRight(root, xpp);
                        }
                    }
                }
            } else {
                TreeNode<K, V> y = xpp.left;
                if (y != null && y.red) {
                    xp.red = false;
                    y.red = false;
                    xpp.red = true;
                    x = xpp;
                } else {
                    if (x == xp.left) {
                        x = xp;
                        root = rotateRight(root, x);
                        xp = x.parent;
                        xpp = xp == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
        root.red = false;
        return root;
    }

    // After deletion, rebalance and return new root.
    private TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x) {
        while (x != null && x != root && !x.red) {
            TreeNode<K, V> xp = x.parent;
            if (xp == null) {
                x = root;
                break;
            }
            if (x == xp.left) {
                TreeNode<K, V> sib = xp.right;
                if (sib == null) {
                    x = xp;
                } else {
                    if (sib.red) {
                        sib.red = false;
                        xp.red = true;
                        root = rotateLeft(root, xp);
                        sib = xp.right;
                    }
                    if ((sib.left == null || !sib.left.red) && (sib.right == null || !sib.right.red)) {
                        sib.red = true;
                        x = xp;
                    } else {
                        if (sib.right == null || !sib.right.red) {
                            if (sib.left != null) sib.left.red = false;
                            sib.red = true;
                            root = rotateRight(root, sib);
                            sib = xp.right;
                        }
                        sib.red = xp.red;
                        xp.red = false;
                        if (sib.right != null) sib.right.red = false;
                        root = rotateLeft(root, xp);
                        x = root;
                    }
                }
            } else {
                TreeNode<K, V> sib = xp.left;
                if (sib == null) {
                    x = xp;
                } else {
                    if (sib.red) {
                        sib.red = false;
                        xp.red = true;
                        root = rotateRight(root, xp);
                        sib = xp.left;
                    }
                    if ((sib.left == null || !sib.left.red) && (sib.right == null || !sib.right.red)) {
                        sib.red = true;
                        x = xp;
                    } else {
                        if (sib.left == null || !sib.left.red) {
                            if (sib.right != null) sib.right.red = false;
                            sib.red = true;
                            root = rotateLeft(root, sib);
                            sib = xp.left;
                        }
                        sib.red = xp.red;
                        xp.red = false;
                        if (sib.left != null) sib.left.red = false;
                        root = rotateRight(root, xp);
                        x = root;
                    }
                }
            }
        }
        if (x != null) x.red = false;
        return root;
    }

    private TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p) {
        if (p == null) return root;
        TreeNode<K, V> r = p.right;
        TreeNode<K, V> rl;
        if (r != null) {
            rl = r.left;
            p.right = rl;
            if (rl != null) rl.parent = p;
            r.parent = p.parent;
        } else {
            return root;
        }
        if (p.parent == null) {
            root = r;
        } else if (p.parent.left == p) {
            p.parent.left = r;
        } else {
            p.parent.right = r;
        }
        r.left = p;
        p.parent = r;
        return root;
    }

    private TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p) {
        if (p == null) return root;
        TreeNode<K, V> l = p.left;
        TreeNode<K, V> lr;
        if (l != null) {
            lr = l.right;
            p.left = lr;
            if (lr != null) lr.parent = p;
            l.parent = p.parent;
        } else {
            return root;
        }
        if (p.parent == null) {
            root = l;
        } else if (p.parent.right == p) {
            p.parent.right = l;
        } else {
            p.parent.left = l;
        }
        l.right = p;
        p.parent = l;
        return root;
    }

    /* ---------------- Tie-break ordering ---------------- */

    /**
     * A deterministic tie-breaker for keys when hashes are equal and keys not equal.
     * <p>
     * Returns:
     * - negative if k1 <= k2
     * - positive if k1 > k2
     * <p>
     * Prefers natural ordering when keys are Comparable and same class; otherwise uses
     * System.identityHashCode fallback to have some deterministic order.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    final int tieBreakOrder(K k1, K k2) {
        if (k1 == k2) return 0;
        if (k1 == null) return -1;
        if (k2 == null) return 1;
        if (k1.getClass() == k2.getClass() && k1 instanceof Comparable) {
            try {
                Comparable kc = (Comparable) k1;
                return kc.compareTo(k2);
            } catch (ClassCastException ex) {
                // fall through
            }
        }
        int h1 = System.identityHashCode(k1);
        int h2 = System.identityHashCode(k2);
        return Integer.compare(h1, h2);
    }

    /* ---------------- Debugging / Utilities ---------------- */

    // Dump contents (not optimized—useful for debugging)
    public void debugDump() {
        System.out.println("MyHashMap size=" + size + " cap=" + table.length);
        for (int i = 0; i < table.length; i++) {
            System.out.print("bucket[" + i + "]: ");
            Node<K, V> e = table[i];
            if (e == null) {
                System.out.println("null");
                continue;
            }
            if (e instanceof TreeNode) {
                System.out.print("(tree) ");
                printTree((TreeNode<K, V>) e);
                System.out.println();
            } else {
                while (e != null) {
                    System.out.print("[" + e.key + "=" + e.value + "] -> ");
                    e = e.next;
                }
                System.out.println("null");
            }
        }
    }

    private void printTree(TreeNode<K, V> root) {
        // simple BFS print
        Queue<TreeNode<K, V>> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode<K, V> t = q.poll();
            System.out.print("{" + t.key + ":" + (t.red ? "R" : "B") + "} ");
            if (t.left != null) q.add(t.left);
            if (t.right != null) q.add(t.right);
        }
    }

    /* ---------------- Sample usage and testing ---------------- */

    public static void main(String[] args) {
        MyHashMap<CollidingKey, String> map = new MyHashMap<>();
        // insert many colliding keys to force treeify
        for (int i = 0; i < 12; i++) {
            map.put(new CollidingKey("k" + i), "v" + i);
        }

        // debug dump - should show tree in the bucket where colliding keys landed
        map.debugDump();

        // check lookups
        System.out.println("Lookup k3: " + map.get(new CollidingKey("k3")));

        // demonstrate remove
        System.out.println("Removing k3 -> " + map.remove(new CollidingKey("k3")));
        System.out.println("Lookup k3 after remove: " + map.get(new CollidingKey("k3")));

        map.debugDump();
    }

    /**
     * Helper key class that intentionally collides: returns a constant hash code so we can
     * force many keys into same bucket to test treeification.
     */
    static class CollidingKey {
        final String id;

        CollidingKey(String id) {
            this.id = id;
        }

        public int hashCode() {
            return 42;
        } // intentionally awful hash

        public boolean equals(Object o) {
            return (o instanceof CollidingKey) && Objects.equals(id, ((CollidingKey) o).id);
        }

        public String toString() {
            return id;
        }
    }
}