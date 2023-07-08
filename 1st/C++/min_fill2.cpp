#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <bits/stdc++.h>
#include <fstream>

#define edge pair<int, int>

using namespace std;

class Graph {
public:
     // graph consructor
    Graph(int V, int E) {
        n = V;
        m = E;
        parent = new int[n+1];

        /*initialize parents. Every node belongs
        to it's own set; For example node 1 belongs to set 1 etc*/
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }


    // add edge u-v to graph with cost c
    void addEdge(int u, int v, int c) {
        if (u < v) {
            g.push_back(make_pair(c, edge(u, v)));
        }
        else
            g.push_back(make_pair(c, edge(v, u)));
    }


    // merge two sets
    void unionSet(int u, int v) {
        parent[u] = parent[v];
    }

    // find the set that node u belongs to
    int findSet(int u) {
        /* if u is parent of itself then
        u belongs to set u */
        if (u == parent[u]) {
            return u;
        }

        /*if u is not parent of itself then
        u belongs to set i where i is ancestor of
        u and parent of i is i*/
        else
            return findSet(parent[u]);
    }


    // implement kruskal algorithm for MST and return max cost in it
    int kruskal() {
        // sort the edges on increasing cost
        sort(g.begin(), g.end());

        /* for the cheapest edge find the
        sets that it's nodes belong to */
        for (int i = 0; i < m; i++) {
            int u = g[i].second.first;
            int v = g[i].second.second;
            /* find the set of u */
            int uSet = findSet(u);
            /* find the set of v */
            int vSet = findSet(v);
            /* if u and v belong to different sets
            perform union in them. Else they belong
            to the same set so we have cycle*/
            if (uSet != vSet) {
                unionSet(uSet, vSet);
                t.push_back(g[i].first);
            }
        }
        /* return maximum edge in MST
        It's the necessary amount of fuel we need*/
        return *max_element(t.begin(), t.end());
    }

private:
    // graph illustrated as a vector of tuples of edges and costs
    vector < pair<int, edge> > g;

    //MST
    vector <int> t;

    /* array to store the parent of each node
    (used for union-find)*/
    int *parent;

    // number of nodes and edges
    int n, m;
};


int main (int argc, char **argv) {
    ifstream inFile;
    inFile.open(argv[1]);
    int n, m;
    inFile >> n >> m;

    Graph g(n, m);
    int u, v, c;

    for (int i = 0; i < m; i++) {
        inFile >> u >> v >> c;
        g.addEdge(u, v, c);
    }
    //print result
    cout << g.kruskal() << endl;
    return 0;
}
