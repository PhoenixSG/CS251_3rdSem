import numpy as np
import pandas as pd
#import sys

import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path", required = True)
ap.add_argument("--num", required = True)
args = vars(ap.parse_args())


a = pd.read_csv(args['path'], delimiter=",", header=None);

b = a.to_numpy();

c= int(args['num'])

ans = np.zeros((b.shape[0]+2*c, b.shape[1]+2*c))

ans[c:b.shape[0]+c,c:b.shape[1]+c] = b
ans = ans.astype(int)
print(ans)
