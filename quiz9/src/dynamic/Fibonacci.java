package dynamic;

public class Fibonacci {
	public static long fibMemo(int n) {
		long[] fibs = new long[n + 1];
		for (int i = 0; i <= n; i++) {
			fibs[i] = Integer.MIN_VALUE;
		}
		return fibHelper(n, fibs);
	}

	public static long fibHelper(int n, long[] fibs) {
		if (fibs[n] >= 0) {
			return fibs[n];
		}
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		fibs[n] = fibHelper(n - 1, fibs) + fibHelper(n - 2, fibs);
		return fibs[n];
	}

	public static long fibBottomUp(int n) {
		long[] fibs = new long[n + 1];

		fibs[0] = 0;
		fibs[1] = 1;

		for (int i = 2; i <= n; i++) {
			fibs[i] = fibs[i - 1] + fibs[i - 2];
		}

		return fibs[n];
	}

	public static void main(String[] args) {
		System.out.println(fibBottomUp(10));
		System.out.println(fibMemo(10));
	}

}
