#include <iostream>
#include <stdio.h>
#include <math.h>
#include <string>
#include <bits/stdc++.h>

using namespace std;
float entropies[26];
int fN[26]; //used to count the frequency of each letter in the decrypted text
int j = 0;

char *out;

/*frequencies of every letter. For
example letter a (small or capital) is used in 8.167% in a typical text.*/
float f[26] = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015,
              0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749,
              0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758,
              0.00978, 0.02360, 0.00150, 0.01974, 0.00074};

/*find decrypted text for some N whereas 0 <= N <= 25
return the entropy for this N(des thn ekfwnhsh)*/
float decryptN (char *buffer, int N, int length) {
    //out = new char [length];

    /*initialize frequency of each letter to zero.
    a, A->0, b, B->1 etc*/
    for (int i = 0; i < 26; i++) {
        fN[i] = 0;
    }

    for (int i = 0; i < length; i++) {
        //if letter Capital
        if (65 <= buffer[i] && buffer[i] <= 90) {
            if (buffer[i] + N > 90) {
                out[i] = 65 + N - (90 - buffer[i]) - 1;
            }
            else {
                out[i] = buffer[i] + N;
            }

            //increase frequency of letter in the decrypted text
            fN[out[i] - 65]++;
        }

        //if letter Small
        else if (97 <= buffer[i] && buffer[i] <= 122) {
            if (buffer[i] + N > 122) {
                out[i] = 97 + N - (122 - buffer[i]) - 1;
            }
            else {
                out[i] = buffer[i] + N;
            }

            //increase frequency of letter in the decrypted text
            fN[out[i] - 97]++;
        }

        //rest characters e.g "", !, 1, ...
        else {
            out[i] = buffer[i];
        }
        cout << out[i];
    }

    //find the entropy
    float H = 0.0;
    for (int i = 0; i < 26; i++) {
        H -= (float(fN[i])) * log10(f[i]);
    }
    cout << H << endl;
    return H;
}


int main (int argc, char **argv) {
    FILE *file;
    string s;
    char *c_line = nullptr;
    size_t len = 0;
    char *buffer;

    file = fopen(argv[1], "r");

    if (file == nullptr) {
        fprintf(stderr, "Couldn't open file for reading\n");
        exit(1);
    }

    int sum = 0;

    while (getline(&c_line, &len, file) != -1) {
        s.assign(c_line);
        sum += s.length();
    }
    fclose(file);

    file = fopen(argv[1], "r");
    if (file == nullptr) {
        fprintf(stderr, "Couldn't open file for reading\n");
        exit(1);
    }

    buffer = new char [sum];
    out = new char [sum];

    //read file and store it in buffer
    while (!feof(file)) {
        fread(buffer, sum, 1, file);
    }

    for (int i = 0; i < 26; i++) {
        entropies[i] = decryptN(buffer, i, sum);
    }

    float leastEntropy = entropies[0];
    int pos = 0;
    for (int i = 1; i < 26; i++) {
        if (entropies[i] < leastEntropy) {
            leastEntropy = entropies[i];
            pos = i;
        }
    }
    decryptN(buffer, pos, sum);
    for (int i = 0; i < sum; i++) {
        cout << out[i];
    }

    delete [] out;
    fclose(file);

    return 0;
}
