#include <bits/stdc++.h>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);

/*
 * Complete the 'solve' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

long long solve(vector<int> arr) {
    long long const N = arr.size();
    long long result = 0;
    stack<pair<int,int>> stack;

    int i = 0;
    while (i < N) {
        int const height = arr[i];

        if (!stack.empty() && stack.top().first < height) {
            int const count = stack.top().second;
            stack.pop();

            if (count > 1) {
                result += (count * (count - 1)) / 2;
            }
        } else if (!stack.empty() && stack.top().first == height) {
            stack.top().second++;
            i++;
        } else {
            stack.push({ height, 1 });
            i++;
        }
    }

    while (!stack.empty()) {
        long long const count = stack.top().second;
        stack.pop();
        result += (count * (count - 1)) / 2;
    }

    return result * 2;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string arr_count_temp;
    getline(cin, arr_count_temp);

    int arr_count = stoi(ltrim(rtrim(arr_count_temp)));

    string arr_temp_temp;
    getline(cin, arr_temp_temp);

    vector<string> arr_temp = split(rtrim(arr_temp_temp));

    vector<int> arr(arr_count);

    for (int i = 0; i < arr_count; i++) {
        int arr_item = stoi(arr_temp[i]);

        arr[i] = arr_item;
    }

    long long result = solve(arr);

    fout << result << "\N";

    fout.close();

    return 0;
}

string ltrim(const string &str) {
    string stack(str);

    stack.erase(
        stack.begin(),
        find_if(stack.begin(), stack.end(), not1(ptr_fun<int, int>(isspace)))
    );

    return stack;
}

string rtrim(const string &str) {
    string stack(str);

    stack.erase(
        find_if(stack.rbegin(), stack.rend(), not1(ptr_fun<int, int>(isspace))).base(),
        stack.end()
    );

    return stack;
}

vector<string> split(const string &str) {
    vector<string> tokens;

    string::size_type start = 0;
    string::size_type end = 0;

    while ((end = str.find(" ", start)) != string::npos) {
        tokens.push_back(str.substr(start, end - start));

        start = end + 1;
    }

    tokens.push_back(str.substr(start));

    return tokens;
}
