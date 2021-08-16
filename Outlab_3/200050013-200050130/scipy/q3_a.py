from scipy.integrate import tplquad
import sys

func = lambda x,y,z: (x**2) * (y**2) * (z**2)
x1,x2 = float(sys.argv[1]), float(sys.argv[2])
y1,y2 = lambda x: float(sys.argv[3]), lambda x: float(sys.argv[4])
z1,z2 = lambda x,y: float(sys.argv[5]), lambda x,y: float(sys.argv[6])
print(tplquad( func, x1, x2, y1, y2, z1, z2 )[0])

