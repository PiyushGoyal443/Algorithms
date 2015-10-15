public class PercolationStats
{

    private int expCnt;
    private Percolation per;
    private double[] frac;

    public PercolationStats(int N, int T) 
    {
        if (N <= 0 || T <= 0) 
        {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        expCnt = T;
        frac = new double[expCnt];
        for (int expNum = 0; expNum < expCnt; expNum++) 
        {
            per = new Percolation(N);
            int openSites = 0;
            while (!per.percolates()) {
                int i = StdRandom.uniform(1, N + 1);
                int j = StdRandom.uniform(1, N + 1);
                if (!per.isOpen(i, j)) {
                    per.open(i, j);
                    openSites++;
                }
            }
            double fraction = (double) openSites / (N * N);
            frac[expNum] = fraction;
        }
    }

    public double mean() 
    {
        return StdStats.mean(frac);
    }

    public double stddev() 
    {
        return StdStats.stddev(frac);
    }


    public double confidenceLo() 
    {
        return mean() - ((1.96 * stddev()) / Math.sqrt(expCnt));
    }

    public double confidenceHi() 
    {
        return mean() + ((1.96 * stddev()) / Math.sqrt(expCnt));
    }

    public static void main(String[] args) 
    {
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats ps = new PercolationStats(N, T);

        String confi = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confi);
    }
}
