package dynamic;

public class MinSumPath {

	public static int minSumPathMemo(int triangle[][]) {
		return 0;
	}

	public static int minSumPathBottomUp(int triangle[][]) {
		int[] minSumArr = new int[triangle.length + 1];

		for (int i = triangle.length - 1; i >= 0; i--) {
			for (int j = 0; j < triangle[i].length; j++) {
				minSumArr[j] = triangle[i][j] + Math.min(minSumArr[j], minSumArr[j + 1]);
			}
		}

		return minSumArr[0];
	}

	public static void main(String[] args) {
		int[][] triangle = new int[][] { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };
		System.out.println(minSumPathBottomUp(triangle));
	}
}
