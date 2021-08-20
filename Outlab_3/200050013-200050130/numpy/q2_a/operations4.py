import numpy as np
import pandas as pd

import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path", required = True)
args = vars(ap.parse_args())


a = pd.read_csv(args['path'], delimiter=",", header=None)

b = a.to_numpy()

c= np.sort(np.ndarray.flatten(b))

d, e = np.unique(c, return_counts=True)

print(d)
print(e)
print(e[-2])
