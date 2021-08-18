import numpy as np
import pandas as pd
import sys

a = pd.read_csv(sys.argv[2], delimiter=",", header=None);

b = a.to_numpy();

c= int(sys.argv[4])

ans = np.zeros((b.shape[0]+2*c, b.shape[1]+2*c))

ans[c:b.shape[0]+c,c:b.shape[1]+c] = b
ans = ans.astype(int)
print(ans)
