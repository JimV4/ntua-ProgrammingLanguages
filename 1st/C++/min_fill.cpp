#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <bits/stdc++.h>
#include <fstream>

using namespace std;

class Graph {
public:
     // graph consructor
    Graph(int V, int E) {
        n = V;
        m = E;
        nodes = new node[n+1];
        /*Initialize nodes. For every node, rank is zero, and parrent
        of every node is itself*/
        for (int i = 0; i <= n; i++) {
            nodes[i].rank = 0;
            nodes[i].name = i;
            nodes[i].parrent = &nodes[i];
        }
    }

    // add edge u-v to graph with cost c
    void addEdge(int u, int v, int c) {
        if (u < v) {
            g.push_back(make_pair(c, make_pair(nodes[u].name, nodes[v].name)));
        }
        else
            g.push_back(make_pair(c, make_pair(nodes[v].name, nodes[u].name)));
    }

    // merge two sets
    void unionSet(int u, int v) {

        /* Make the set with smaller rank a subtree of
        the set with greater rank. Increase the rank of final tree*/
        if (nodes[u].rank > nodes[v].rank) {
            nodes[v].parrent = &nodes[u];
            nodes[v].rank += nodes[u].rank;
        }
        else {
            nodes[u].parrent = &nodes[v];
            nodes[u].rank += nodes[v].rank;
        }
    }

    // find the set that node u belongs to
    int findSet(int u) {
        /* if u is parent of itself then
        u belongs to set u */
        if (u == (nodes[u].parrent)->name) {
            return u;
        }

        /*if u is not parent of itself then
        traverse from u till we found the root of the tree.
        The root is parrent of itself. Also apply path compression.*/
        else {
            int set = findSet((nodes[u].parrent)->name);
            nodes[u].parrent = nodes[set].parrent;
            return set;
        }
    }


    // implement kruskal algorithm for MST and return max cost in it
    int kruskal() {
        // sort the edges on increasing cost
        sort(g.begin(), g.end());

        /*for the cheapest edge find the
        sets that it's nodes belong to */
        for (int i = 0; i < m; i++) {
            int u = g[i].second.first;
            int v = g[i].second.second;
            /* find the set of u */
            int uSet = findSet(nodes[u].name);
            /* find the set of v */
            int vSet = findSet(nodes[v].name);
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

    struct node {
        int name;
        int rank;
        node *parrent;
    };

    // graph illustrated as a vector of tuples of edges and costs
    vector < pair<int, pair<int, int>> > g;

    //MST
    vector <int> t;

    // number of nodes and edges
    int n, m;

    // array to store every node
    node *nodes;
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
