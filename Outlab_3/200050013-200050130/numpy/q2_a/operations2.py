import numpy as np
import pandas as pd

import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path", required = True)
args = vars(ap.parse_args())


a = pd.read_csv(args['path'], delimiter=",", header=None);

b = a.to_numpy();

print( np.mean(b, axis=0))
print( np.median(b, axis=0))
std_dev= np.std(b, axis=0)
std_dev = np.round_(std_dev, decimals=2)
print(std_dev)
print( np.linalg.det(b) )
inverse =  np.linalg.pinv(b)
inverse = np.round_(inverse, decimals=2)
print(inverse)
