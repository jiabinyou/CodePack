package SW.Plan1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (s == null || s.length() == 0) {
            return res;
        }
        //prePare hashing
        char[] hashing = new char[26];
        hashing['A' - 'A'] = 0;
        hashing['C' - 'A'] = 1;
        hashing['G' - 'A'] = 2;
        hashing['T' - 'A'] = 3;
        int mask = 0X3FFFF;

        //representative ds
        Set<Integer> traversed = new HashSet<>();  //encoded number
        Set<Integer> dedupRes = new HashSet<>();
        //FS-SW
        int code = 0;
        for (int j = 0; j < s.length(); j++) {
            //add fast + remove slow
            code = (code & mask) << 2;
            code |= hashing[s.charAt(j) - 'A'];
            //check res
            if (j >= 9) {
                if (!traversed.add(code) && dedupRes.add(code)) {
                    res.add(s.substring(j - 9, j + 1));
                }
            }
        }
        return res;
    }
}
