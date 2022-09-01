package test.leetcode;

/**
 * ����һ�� m x n ���������� accounts ������ accounts[i][j] �ǵ� i???????????? λ�ͻ��ڵ� j �������йܵ��ʲ�������������пͻ���ӵ�е� �ʲ����� ��
 *
 * �ͻ��� �ʲ����� ���������ڸ��������йܵ��ʲ�����֮�͡���пͻ����� �ʲ����� ���Ŀͻ���
 *
 * ?
 *
 * ʾ�� 1��
 *
 * ���룺accounts = [[1,2,3],[3,2,1]]
 * �����6
 * ���ͣ�
 * �� 1 λ�ͻ����ʲ����� = 1 + 2 + 3 = 6
 * �� 2 λ�ͻ����ʲ����� = 3 + 2 + 1 = 6
 * ��λ�ͻ�������еģ��ʲ��������� 6 �����Է��� 6 ��
 * ʾ�� 2��
 *
 * ���룺accounts = [[1,5],[7,3],[3,5]]
 * �����10
 * ���ͣ�
 * �� 1 λ�ͻ����ʲ����� = 6
 * �� 2 λ�ͻ����ʲ����� = 10
 * �� 3 λ�ͻ����ʲ����� = 8
 * �� 2 λ�ͻ�����еģ��ʲ������� 10
 * ʾ�� 3��
 *
 * ���룺accounts = [[2,8,7],[7,1,3],[1,9,5]]
 * �����17
 *
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/richest-customer-wealth
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class Solution1672 {
    public static void main(String[] args) {
        Solution1672 solution1672 = new Solution1672();


    }
    public int maximumWealth(int[][] accounts) {
        int arr[] = new int[accounts.length];
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            for (int j = 0; j < accounts[i].length; j++) {
                arr[i] += accounts[i][j];
            }
            if (arr[i] > max) {

                max = arr[i];
            }
        }
        return max;
    }

}
