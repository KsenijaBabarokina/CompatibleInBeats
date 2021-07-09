#include <bits/stdc++.h>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);

/*
 * Complete the 'downToZero' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER n as parameter.
 */

struct Node {
    Node(long long value, long long depth = 0) 
        : value_(value), depth_(depth) {
    }

    long long value_;
    long long depth_;
};

long long downToZero(int n) {
    std::queue<Node> q;
    q.push(Node(n));
    
    while (!q.empty()) {
        Node current = q.front();
        q.pop();
        
        cout << current.value_ << " " << current.depth_ << endl;
        
        if (current.value_ == 4) {
            return current.depth_ + 3;
        } else if (current.value_ < 4) {
            return current.depth_ + current.value_;
        }
        
        // decrease by 1 -> 1st option in a move -> works always
        q.push(Node(current.value_ - 1, current.depth_ + 1));
        
        // we need to find the minimum factors of the current value
        // minimum factor is a square root
        long long factor = static_cast<long long>(sqrt(current.value_));

        for (int i = factor; i >= 2; i--) {
            // make sure the number is not prime
            // if it's prime we have already inserted the decremented one in the queue
            // otherwise insert it's factors with the same depth,
            // so that it will be counter
            if (current.value_ % i == 0) {
                q.push(Node(current.value_ / i, current.depth_ + 1));
            }
        }
    }
    
    return n;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string q_temp;
    getline(cin, q_temp);

    int q = stoi(ltrim(rtrim(q_temp)));

    for (int q_itr = 0; q_itr < q; q_itr++) {
        string n_temp;
        getline(cin, n_temp);

        int n = stoi(ltrim(rtrim(n_temp)));

        long long result = downToZero(n);

        fout << result << "\n";
    }

    fout.close();

    return 0;
}

string ltrim(const string &str) {
    string s(str);

    s.erase(
        s.begin(),
        find_if(s.begin(), s.end(), not1(ptr_fun<int, int>(isspace)))
    );

    return s;
}

string rtrim(const string &str) {
    string s(str);

    s.erase(
        find_if(s.rbegin(), s.rend(), not1(ptr_fun<int, int>(isspace))).base(),
        s.end()
    );

    return s;
}

