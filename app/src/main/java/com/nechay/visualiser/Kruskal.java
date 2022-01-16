package com.nechay.visualiser;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Kruskal {
    class Edge implements Comparable<Edge> {
        int src, dest, weight;
        public int compareTo(Edge compareEdge) {
            return this.weight-compareEdge.weight;
        }
    }
    class subset {
        int parent, rank;
    };
    private Graph ostov1;
    Graph gr;
    int V, E;
    Edge edge[];
    Kruskal (int v, int e) {
        V = v;
        E = e;
        ostov1 = new Graph();
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
    int find(subset subsets[], int i) {

        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    void Union(subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else{
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    void KruskalMST(Graph gr) {
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i=0; i<V; ++i)
            result[i] = new Edge();
        Arrays.sort(edge);
        subset subsets[] = new subset[V];
        for(i=0; i<V; ++i)
            subsets[i]=new subset();
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0;
        while (e < V - 1) {
            Edge next_edge = new Edge();
            try{
                next_edge = edge[i++];
            }catch(Exception exc){
                break;
            }
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        for (i = 0; i < e; ++i) {
            for(com.nechay.visualiser.Edge r:gr.getEdges()) {
                if(r.getVertex1().getNumber()-1==result[i].src && r.getVertex2().getNumber()-1==result[i].dest) {
                    ostov1.getEdges().add(r);
                    if(!ostov1.getVertexList().contains(r.getVertex1())) {
                        ostov1.getVertexList().add(r.getVertex1());
                    }
                    if(!ostov1.getVertexList().contains(r.getVertex2())) {
                        ostov1.getVertexList().add(r.getVertex2());
                    }
                }
            }
        }
    }
    public Graph getOstov() {
        return ostov1;
    }
    public Graph getGr() {
        return gr;
    }
    public void setGr(Graph gr) {
        this.gr = gr;
    }
}
