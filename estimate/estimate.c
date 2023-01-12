#include<stdio.h>
#include<stdlib.h>

void print(double** m, int y, int x)
{
    for (int i = 0; i < y; i++)
    {
        int j = 0;
        while  (j < x)
        {
            printf("%.0f", m[i][j]);
            j++; 
        }
        printf("\n");
    }
}
void freedom(double** m, int y)
{
    int i = 0;
    while (i < y)
    {
        free(m[i]);
        i++;
    }
    free(m);
}

double **allocate(int y, int cols)
{
  double ** mrix = malloc(y * sizeof(double*));
  int i = 0;
  while (i < y)
  {
    mrix[i] = malloc(cols * sizeof(double));
    i++;  
  }
  return mrix;
}

double** multiply(double **mtx1, double **mtx2, int rowA, int columnA, int rowB, int columnB)
{
    double **fA = allocate(rowA, columnB);

    for (int kp = 0; kp < 1; kp ++)
    {
        for (int jd = 0; jd < 1; jd++)
        {
    		for (int mm = 0; mm < 1; mm++)
            {
    		    for(int i = 0; i < rowA; i++)
                {
                    for(int j = 0; j < columnB; j++)
                    {
                        double *cc = malloc(columnA * sizeof(double*));
                        for (int k = 0; k < columnA; k++)
                        {
                            cc[k] = mtx1[i][k];
                        }
                        double *rr = malloc(rowB * sizeof(double*));
                        for (int k = 0; k < rowB; k++)
                        {
                            rr[k] = mtx2[k][j];
                        }
                        double add = 0;
                        for (int k = 0; k < rowB; k++)
                        {
                            add = add + (rr[k]*cc[k]);
                        }
                        free(rr);
                        free(cc);
                        fA[i][j] = add;
                    }
                }
            }
        }
    }
    return fA;
}

double** transpose(double** m, int row, int column)
{
    double ** transM = allocate(column, row);
    for(int a = 0; a < 1; a++)
    {
    	for (int b = 0; b < 1; b++)
        {
    		for (int i = 0; i < row; i++)
            {
                int d = 0;
                while (d < column)
                {
                    transM[d][i] = m[i][d];
                    d++;
                }
            }
        }
    }
    return transM;
}

double** inverse(double **mtx1, int dx)
{
    double **xy = malloc(dx * sizeof(double*));
    for (int a = 0; a < 1; a++)
    {
        int i = 0;
        while (i < dx)
        {
            xy[i] = malloc(dx * sizeof(double));
            i++;
        }
    }
    for (int b = 0; b < 1; b++)
    {
        for(int i = 0; i < dx; i++)
        {
            int j = 0;
            while (j < dx)
            {
                if (!(i == j))
                {
                    xy[i][j] = 0;
                }
                else
                {
                    xy[i][j] = 1;
                } 
                j++;
            }
        }
    }
    for (int b = 0; b < 1; b++)
    {
        for(int i = 0; i < dx; i++)
        {
            for(int j = 0; j < dx; j++)
            {
                if(i != j)
                {
                    double val = mtx1[j][i]/mtx1[i][i];
                    int k = 0;
                    while (k < dx)
                    {
                        mtx1[j][k] = mtx1[j][k] - val*mtx1[i][k];
                        xy[j][k] = xy[j][k] - val*xy[i][k];
                        k++; 
                    }
                }
            }
        }
    }
    for(int a = 0; a < 1; a++)
    {
	    for(int i = 0; i < dx; i++)
        {
		    int j = 0;
            while (j < dx)
            {
                xy[i][j] = xy[i][j]/mtx1[i][i];
                j++; 
            }
        }
    }  
    return xy;
}

int main(int argc, char** argv)
{
    FILE * train = fopen (argv[1], "r");
    int attributes;
    int houses;
    char b[512];
    fgets(b, 512, train);
    fscanf(train, "%d\n%d", &attributes, &houses);
    double** mX = malloc(houses * sizeof(double*));
    int i = 0;
    while (i < houses)
    {
        mX[i] = malloc((attributes+1) * sizeof(double));
        i++;
    }

    double** mY = malloc(houses * sizeof(double*));
    int j = 0;
    while (j < houses)
    {
        mY[j] = malloc(sizeof(double));
        j++;
    }
    for (int i = 0; i < houses; i++)
    {
        int h = 0;
        while (h < attributes)
        {
            fscanf(train, "%lf,", &mX[i][h+1]);
            h++;
        }
        fscanf(train, "%lf\n", &mY[i][0]);
    }
    int l = 0;
    while (l < houses)
    {
        mX[l][0] = 1;
        l++; 
    }
    FILE * test = fopen (argv[2], "r");
    fgets(b, 512, test);
    fgets(b, 512, test);
    int h2;
    fscanf(test, "%d\n", &h2);
    
    double** mtxSq = malloc(h2 * sizeof(double*));
    int s = 0;
    while (s < h2)
    {
        mtxSq[s] = malloc((attributes+1) * sizeof(double));
        s++;
    }
    for (int i = 0; i < h2; i++)
    {
        int t = 0; 
        while (t < attributes)
        {
            fscanf(test, "%lf,", &mtxSq[i][t+1]);
            t++;
        }
        fscanf(test, "\n");
    }
    int u = 0;
    while (u < h2)
    {
        mtxSq[u][0] = 1;
        u++; 
    }
    double **m0 = transpose(mX, houses, attributes+1);
    double **m1 = multiply(m0, mX, attributes+1, houses, houses, attributes+1);
    double **m2 = inverse(m1, attributes+1);
    double **m3 = multiply(m2, m0, attributes+1, attributes+1, attributes+1, houses);
    double **m4 = multiply(m3, mY, attributes+1, houses, houses, 1);
    double **m5 = multiply(mtxSq, m4, h2, attributes+1, attributes+1, 1);
    print(m5, h2, 1);
    freedom(mX, houses);
    freedom(mY, houses);
    freedom(m0, attributes+1);
    freedom(m1, attributes+1);
    freedom(m2, attributes+1);
    freedom(m3, attributes+1);
    freedom(mtxSq, h2);
    freedom(m5, h2);
    freedom(m4, attributes+1);
	return 0;
}
