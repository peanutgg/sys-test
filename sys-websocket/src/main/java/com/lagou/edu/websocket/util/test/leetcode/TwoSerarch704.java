package com.sys.test.websocket.util.test.leetcode;

/**
 * 二分查找
 */
public class TwoSerarch704 {

    public static void main(String[] args) {
        int[] src = {2, 5};
        System.out.println(search(src, 5, 0, src.length - 1));
    }

    public static int search(int[] src, int t, int s, int e) {
        int res = -1;
        int mid = (e + s) / 2;
        if (src.length <= 2) {
            if (src[0] == t) {
                return 0;
            }else if(src.length == 2 && src[1] == t) {
                return 1;
            }
        }
        if (mid == e && src[mid] != t) {
            return -1;
        }
        if (src[mid] == t) {
            return mid;
        }
        if (src[mid] > t) {//目标值比中间值小,证明目标值在中间值的左边
            res = search(src, t, s, mid);
        } else if (src[mid] < t) {//目标值比中间值大，目标值在中间值的右边
            res = search(src, t, mid + 1, e);
        }

        return res;
    }
}
