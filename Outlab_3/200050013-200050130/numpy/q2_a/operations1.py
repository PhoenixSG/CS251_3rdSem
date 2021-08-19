import numpy as np
import pandas as pd

import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path", required = True)
args = vars(ap.parse_args())


a = pd.read_csv(args['path'], delimiter = ",", header = None);

b = a.to_numpy();
b = b.transpose()
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
