#include <iostream>
#include <stdio.h>
#include <math.h>
#include <string>

using namespace std;
char out[10000];

/*frequencies of every letter. For
example letter a (small or capital) is used in 8.167% in a typical text.*/
double f[26] = {0.08167, 0.06094, 0.07507, 0.00978, 0.01492, 0.06966, 
              0.01929, 0.02360, 0.02782, 0.00153, 0.00095, 0.00150,
              0.04253, 0.00772, 0.05987, 0.01974, 0.12702, 0.02228, 
              0.02015, 0.04025, 0.02406, 0.06749, 0.06327, 0.09056, 
              0.02758, 0.00074};

/*find decrypted text for some N whereas 0 <= N <= 25
return the entropy for this N(des thn ekfwnhsh)*/
void decryptN (char *buffer, int N) {
    int fN[26]; //used to count the frequency of each letter in the decrypted text
    string input;
    /*initialize frequency of each letter to zero.
    a, A->0, b, B->1 etc*/
    for (int i = 0; i < 25; i++) {
        fN[i] = 0;
    }

    for (int i = 0; i < input.length(); i++) {
        //if letter Capital
        if (65 <= input[i] <= 90) {  
            if (input[i] + N > 90) {
                out[i] = 65 + N - (90 - input[i]) - 1;
            }
            else {
                out[i] = input[i] + N;
            }

            //increase frequency of letter in the decrypted text
            fN[out[i]]++;
        }

        //if letter Small
        else if (97 <= input[i] <= 122) {
            if (input[i] + N > 122) {
                out[i] = 97 + N - (122 - input[i]) - 1;
            }
            else {
                out[i] = input[i] + N;
            }

            //increase frequency of letter in the decrypted text
            fN[out[i]]++;
        }

        //rest characters e.g "", !, 1, ...
        else {
            out[i] = input[i];
        }
        cout << out << endl;
    }
    //find the entropy
    int H = 0;
    for (int i = 0; i < 25; i++) {
        H += fN[i] * log(f[i]);
    }
    //return H;
}


int main (int argc, char **argv) {
    FILE *file;
    char *buffer[10000];
    string input, s;
    char *c_line = nullptr;
    size_t len = 0;

    file = fopen(argv[1], "r");

    if (file == nullptr) {
        fprintf(stderr, "Couldn't open file for reading\n");
        exit(1);
    }
    //read input file and store it in buffer
   
    while (!feof(file)) {
        fread(buffer, sizeof(buffer), 1, file);
    }

    printf("%s\n", buffer);
    int min = decryptN(buffer, 7), candidateMin;
 
    for (int i = 0; i < sizeof(out); i++) {
        printf("%c", out[i]);
    }
    int sum = 0;
    while (getline(&c_line, &len, file) != -1) {
        input.assign(c_line);
        sum += input.length();
        cout << input << endl;
        //decryptN(input, 13);
    }
    cout << sum;

    fclose(file);
    

/*
    for (int i = 1; i < 25; i++) {
        for (int i = 0; i < sizeof(out); i++) {
            saveOutput[i] = out[i];
        }
        candidateMin = decryptN(buffer, i);
        if (candidateMin < min) {
            min = candidateMin;
        }
    }
    for (int i = 0; i < sizeof(saveOutput); i++) {
        cout << saveOutput[i];
    }*/
    return 0;
}