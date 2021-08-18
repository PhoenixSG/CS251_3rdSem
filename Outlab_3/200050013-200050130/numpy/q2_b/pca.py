import pandas as pd
import numpy as np
import matplotlib
from numpy import linalg as LA
import sys
import matplotlib.pyplot as plt
matplotlib.use('Agg')

a = pd.read_csv(str(sys.argv[2]), header = None)
a -= a.mean()
#print(a)
#print(np.cov(a.transpose()))
eigval, eigvec = LA.eig(np.cov(a.transpose()))
#print(eigval)
eigvec = eigvec.transpose()
#print(eigvec)
eigval, eigvec = zip(*sorted(zip(eigval, eigvec), reverse = True))

eigval = [c for c in eigval] 
eigval = np.array(eigval)

eigvec_1 = [c for c in eigvec[0]]
eigvec_2 = [c for c in eigvec[1]]



print(eigval)
#print(eigvec)

#print(np.concatenate((eigvec_1, eigvec_2)).reshape((-1, 2), order = 'F'))
transformed = a.dot(np.concatenate((eigvec_1, eigvec_2)).reshape((-1, 2), order = 'F'))
transformed = np.array(transformed)
#print(transformed.shape)
#print(transformed)


st = str(sys.argv[4])

np.savetxt(st + "transform.csv", transformed, delimiter = ",")

plt.figure().add_subplot(1, 1, 1).scatter(transformed[:,0], transformed[:,1])
sz = 15.0
plt.xlim(-sz, sz)
plt.ylim(-sz, sz)
plt.gca().set_aspect('equal', adjustable = 'box')
#plt.show()
f = open(st + 'out.png', 'w')
plt.savefig(st + 'out.png')

