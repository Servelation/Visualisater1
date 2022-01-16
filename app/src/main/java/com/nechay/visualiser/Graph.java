package com.nechay.visualiser;

import java.util.ArrayList;

public class Graph {
    private int size;
    private boolean[][] matrix;
    private boolean orint;
    private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private Graph ostov;
    public Graph() {
        setSize(1);
        matrix = new boolean[][]{{false}};
        Vertex v = new Vertex();
        vertexList.add(v);

    }
    public Graph(int size, boolean[][] m) {
        this.size = size;
        this.matrix = m;
        boolean ort = false;
        for(int i=0; i<size;i++) {
            Vertex v = new Vertex(  450+ (float) Math.sin((double)(2.0/size)*i*3.1415)*380,
                                    450+(float) Math.cos((double)(2.0/size)*i*3.1415)*380,
                                    i+1);
            vertexList.add(v);
            for(int j=0;j<size;j++){
                if( matrix[i][j]!=matrix[j][i] && i!=j) {
                    ort = true;
                }
            }
        }
        this.orint = ort;
        for(int i=0; i<size;i++) {
            for(int j=0;j<size;j++) {
                if (orint) {
                    //Ориентированный граф
                    System.out.println("ORGRAPH");
                    orGraph(i,j);
                } else {
                    //неориентированный граф
                    System.out.println("NEORGRAPH");
                    notOrGraph(i,j);
                }
            }
        }
        System.out.println();
        for(int i=0; i<size;i++) {
            for(int j=0;j<size;j++) {
                System.out.print(matrix[i][j]? "1 ": "0 ");
            }
            System.out.println();
        }
    }
    public void notOrGraph(int i,int j){
        if(i<=j && matrix[i][j]){
            Edge e = new Edge(vertexList.get(i),vertexList.get(j));
            edges.add(e);
        }
    }
    public void orGraph(int i,int j){
        if(matrix[i][j]) {
            if(matrix[i][j]==matrix[j][i]){
                if(!isContainsThisEdge(vertexList.get(i), vertexList.get(j))) {
                    Edge e = new Edge(vertexList.get(i), vertexList.get(j));
                    edges.add(e);
                }
            }else{
                Arc a = new Arc(vertexList.get(i), vertexList.get(j));
                edges.add(a);
            }
        }
    }
    private boolean isContainsThisEdge(Vertex v1, Vertex v2){
        for(Edge e: edges){
            if( (e.getVertex1().equals(v1) && e.getVertex2().equals(v2)) || (e.getVertex1().equals(v2) && e.getVertex2().equals(v1) ) ){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        String str ="";
        for(boolean[] bstr: matrix){
            for(boolean b:bstr){
                str+=b?"1":"0";
            }
            str+=";\n";
        }
        return str;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public boolean[][] getZnach() {
        return matrix;
    }
    public void setZnach(boolean[][] znach) {
        this.matrix = znach;
    }
    public boolean isOrint() {
        return orint;
    }
    public void setOrint(boolean orint) {
        this.orint = orint;
    }
    public ArrayList<Vertex> getVertexList() {
        return vertexList;
    }
    public void setVertexList(ArrayList<Vertex> versh) {
        this.vertexList = versh;
    }
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
    public boolean[][] getMatrix() {
        return matrix;
    }
    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }
    public Edge findEdge(int i, int j){
        for(Edge e: getEdges()){
            if( e.getVertex1().getNumber()-1==i &&
                    e.getVertex2().getNumber()-1==j){
                return e;
            }
        }
        return null;
    }
    public Graph getOstov() {
        return ostov;
    }
    public void setOstov(Graph ostov) {
        this.ostov = ostov;
    }
}
