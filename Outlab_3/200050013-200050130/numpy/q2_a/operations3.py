import numpy as np
import pandas as pd

import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path", required = True)
args = vars(ap.parse_args())


a = pd.read_csv(args['path'], delimiter=",", header=None)

b = a.to_numpy()

print(np.sort(b, axis=0))
print(np.sort(b))
print(np.sort(np.ndarray.flatten(b)))
