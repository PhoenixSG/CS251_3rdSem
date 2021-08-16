import sys
import numpy as np
from numpy import linalg as LA
from pathlib import Path
import matplotlib.pyplot as plt

a = np.loadtxt(open(sys.argv[2]), delimiter = ",")
a /= LA.norm(a) 
eigval, eigvec = LA.eig(np.cov(a.transpose())) 
eigval, eigvec = zip(*sorted(zip(eigval, eigvec), reverse = True))
eigval = [c for c in eigval] 
print(eigval)
eigvec_1 = [c for c in eigvec[-1]]
eigvec_2 = [c for c in eigvec[-2]]
t = np.concatenate((eigvec_1, eigvec_2)).reshape((-1, 2), order = 'F')
data = a.dot(t)


st = str(sys.argv[4])


np.savetxt(st + "transform.csv", data, delimiter = ",")

plt.figure().add_subplot(1, 1, 1).scatter(data[:,0], data[:,1])
sz = 0.04
plt.xlim(-sz, sz)
plt.ylim(-sz, sz)
plt.gca().set_aspect('equal', adjustable='box')
#plt.show()
f = open(st + 'out.png', 'w')
plt.savefig(st + 'out.png')    
