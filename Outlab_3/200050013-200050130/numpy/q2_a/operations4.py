import numpy as np
import pandas as pd
import sys

a = pd.read_csv(sys.argv[2], delimiter=",", header=None);

b = a.to_numpy();

c= np.sort(np.ndarray.flatten(b))

d, e = np.unique(c, return_counts=True)

print(d)
print(e)
print(e[-2])