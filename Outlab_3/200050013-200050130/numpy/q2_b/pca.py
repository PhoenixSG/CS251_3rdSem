import pandas as pd
import numpy as np
import matplotlib
from numpy import linalg as LA
import matplotlib.pyplot as plt
matplotlib.use('Agg')

import argparse
ap = argparse.ArgumentParser()
ap.add_argument("--path", required = True)
ap.add_argument("--output", required = True)
args = vars(ap.parse_args())

print(str(args['path']))

a = pd.read_csv(str(args['path']), header = None)
a -= a.mean()
eigval, eigvec = LA.eig(np.cov(a.transpose()))
eigvec = eigvec.transpose()
eigval, eigvec = zip(*sorted(zip(eigval, eigvec), reverse = True))

eigval = [c for c in eigval] 
eigval = np.array(eigval)

eigvec_1 = [c for c in eigvec[0]]
eigvec_2 = [c for c in eigvec[1]]



print(eigval)
transformed = a.dot(np.concatenate((eigvec_1, eigvec_2)).reshape((-1, 2), order = 'F'))
transformed = np.array(transformed)


st = str(args['output'])

np.savetxt(st + "transform.csv", transformed, delimiter = ",")

plt.figure().add_subplot(1, 1, 1).scatter(transformed[:,0], transformed[:,1])
sz = 15.0
plt.xlim(-sz, sz)
plt.ylim(-sz, sz)
plt.gca().set_aspect('equal', adjustable = 'box')
f = open(st + 'out.png', 'w')
plt.savefig(st + 'out.png')
