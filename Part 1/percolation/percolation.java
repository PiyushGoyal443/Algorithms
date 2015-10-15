import edu.princeton.cs.algs4.*;

public class Percolation
{

    private boolean[][] grid;		//by default the entries in the grid will be false
    private int top=0;
    private int botm;
    private int size;
    private WeightedQuickUnionUF qF;

    public Percolation(int N)		//creates N x N grid 
    {
        size = N;
        botm = size * size + 1;
        qF = new WeightedQuickUnionUF(botm + 1);
        grid = new boolean[size][size];
    }
    
    public void open(int i, int j) 	//opens i ,j the position if its not open
    {
        grid[i - 1][j - 1] = true;
        if (i == 1) 
        {
            qF.union(getidIndex(i, j), top);
        }
        if (i == size) 
        {
            qF.union(getidIndex(i, j), botm);
        }

        if (j > 1 && isOpen(i, j - 1))
        {
            qF.union(getidIndex(i, j), getidIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) 
        {
            qF.union(getidIndex(i, j), getidIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) 
        {
            qF.union(getidIndex(i, j), getidIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) 
        {
            qF.union(getidIndex(i, j), getidIndex(i + 1, j));
        }
    }

    public boolean isOpen(int i, int j) 	//checks whether site is open or not
    {
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) 	//checks whether site is full or not
    {
        if (0 < i && i <= size && 0 < j && j <= size) 
        {
            return qF.connected(top, getidIndex(i , j));
        } 
        else 
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean percolates() 	//return true if system percolates else false
    {
        return qF.connected(top, botm);
    }

    private int getidIndex(int i, int j) 
    {
        return size * (i - 1) + j;
    }
    
}
