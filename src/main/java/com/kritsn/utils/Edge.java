package com.kritsn.utils;

import java.util.Objects;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Aug 23, 2026
 */

public final class Edge {
    public final int src;
    public final int dest;
    public final int weight;

    /**
     *
     */
    public Edge(
            int src, int dest, int weight
    ) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int src() {
        return src;
    }

    public int dest() {
        return dest;
    }

    public int weight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Edge) obj;
        return this.src == that.src &&
                this.dest == that.dest &&
                this.weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest, weight);
    }

    @Override
    public String toString() {
        return "Edge[" +
                "src=" + src + ", " +
                "dest=" + dest + ", " +
                "weight=" + weight + ']';
    }

}
