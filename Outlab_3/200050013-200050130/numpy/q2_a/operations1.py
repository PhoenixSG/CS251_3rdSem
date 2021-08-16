import numpy as np
import pandas as pd
import sys

a = pd.read_csv(sys.argv[2], delimiter=",", header=None);

b = a.to_numpy();
b=b.transpose()
x_index=0
y_index=0

for i in b:
    x_index+=1
    for j in i:
        y_index+=1
        if(x_index>=y_index):
            print(j, end=" ")
        else:
            print(" ", end=" ")
    y_index=0
    print()