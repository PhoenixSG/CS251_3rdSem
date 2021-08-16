import numpy as np
import pandas as pd
import sys

a = pd.read_csv(sys.argv[2], delimiter=",", header=None);

b = a.to_numpy();

print(np.sort(b, axis=0))
print(np.sort(b))
print(np.sort(np.ndarray.flatten(b)))