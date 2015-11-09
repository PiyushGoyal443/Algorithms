import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
public class Board {
private int N;
private int[] blocks;
// construct a blocks from an N-by-N array of blocks
// (where blocks[i][j] = block in row i, column j)
public Board(int[][] blocks) {
N = blocks[0].length;
this.blocks = new int[N*N];
for (int i = 0; i < N; i++)
for (int j = 0; j < N; j++)
this.blocks[i*N+j] = blocks[i][j];
}
private Board(int[] blocks) {
N = (int) Math.sqrt(blocks.length);
this.blocks = new int[blocks.length];
for (int i = 0; i < blocks.length; i++)
this.blocks[i] = blocks[i];
}
// blocks dimension N
public int dimension() {
return N;
}
// number of blocks out of place
public int hamming() {
int cnt = 0;
for (int i = 0; i < blocks.length; i++)
if (blocks[i] != 0 && blocks[i] != i+1)
cnt++;
return cnt;
}
// sum of Manhattan distances between blocks and goal
public int manhattan() {
int sum;
int row, col;
sum = 0;
for (int i = 0; i < blocks.length; i++) {
if (blocks[i] != 0 && blocks[i] != i+1) {
col = Math.abs(i % N - (blocks[i]-1) % N);
row = Math.abs(i / N - (blocks[i]-1) / N);
sum += (row + col);
}
}
return sum;
}
// is this blocks the goal blocks?
public boolean isGoal() {
for (int i = 0; i < blocks.length; i++)
if (blocks[i] != 0 && blocks[i] != i+1)
return false;
return true;
}
// a blocks obtained by exchanging two adjacent blocks in the same row
public Board twin() {
Board tBoard;
/* make sure a twin DOES exist */
if (N <= 1) return null;
/* Just swap block[0][0] and block[0][1] if neither is 0.
* Otherwise swap block[1][0] and block[1][1] */
tBoard = new Board(blocks);
if (blocks[0] != 0 && blocks[1] != 0)
exch(tBoard.blocks, 0, 1);
else
exch(tBoard.blocks, N, N+1);
return tBoard;
}
// does this blocks equal y?
public boolean equals(Object y) {
if (y == null) return false;
if (y == this) return true;
if (y.getClass() != this.getClass())
return false;
Board yBoard = (Board) y;
return Arrays.equals(blocks, yBoard.blocks);
}
private void exch(int[] a, int i, int j) {
int temp = a[j];
a[j] = a[i];
a[i] = temp;
}
// all neighboring blocks
public Iterable<Board> neighbors() {
int i;
Board neiBoard;
Queue<Board> bq = new Queue<Board>();
/* find the 0 block */
for (i = 0; i < blocks.length; i++)
if (blocks[i] == 0) break;
/* no 0 block exists. Generally not possible */
if (i >= blocks.length) return null;
/* add all possible neighbor blocks into queue */
if (i >= N) {
/* 0 up */
neiBoard = new Board(blocks);
exch(neiBoard.blocks, i, i-N);
bq.enqueue(neiBoard);
}
if (i < blocks.length - N) {
/* 0 down */
neiBoard = new Board(blocks);
exch(neiBoard.blocks, i, i+N);
bq.enqueue(neiBoard);
}
if (i % N != 0) {
/* 0 left */
neiBoard = new Board(blocks);
exch(neiBoard.blocks, i, i-1);
bq.enqueue(neiBoard);
}
if ((i+1) % N != 0) {
/* 0 right */
neiBoard = new Board(blocks);
exch(neiBoard.blocks, i, i+1);
bq.enqueue(neiBoard);
}
return bq;
}
// string representation of the blocks
public String toString() {
StringBuilder s = new StringBuilder();
int digit = 0;
String format;
/* print dimension first */
s.append(N);
s.append("\n");
/* calculate digits of largest number */
for (int n = blocks.length; n != 0; n /= 10)
digit++;
/* use digit to format output string */
format = "%" + digit + "d ";
for (int i = 0; i < blocks.length; i++) {
s.append(String.format(format, blocks[i]));
if ((i+1) % N == 0)
s.append("\n");
}
return s.toString();
}
public static void main(String[] args) {
In in = new In(args[0]);
int N = in.readInt();
int[][] blocks = new int[N][N];
for (int i = 0; i < N; i++)
for (int j = 0; j < N; j++)
blocks[i][j] = in.readInt();
Board initial = new Board(blocks);
StdOut.print(initial.toString());
StdOut.print(initial.twin().toString());
StdOut.println(initial.hamming());
StdOut.println(initial.manhattan());
StdOut.println(initial.dimension());
StdOut.println(initial.isGoal());
for (Board b : initial.neighbors()) {
StdOut.println(b.toString());
for (Board d : b.neighbors()) {
StdOut.println("===========");
StdOut.println(d.toString());
StdOut.println("===========");
}
}
}
}
