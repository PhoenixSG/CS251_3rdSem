from scipy.integrate import tplquad
import argparse


p = argparse.ArgumentParser()
p.add_argument('values', nargs = '*', type = float)
args = p.parse_args()

numList = args.values




func = lambda x,y,z: (x**2) * (y**2) * (z**2)
x1,x2 = numList[0], numList[1]
y1,y2 = lambda x: numList[2], lambda x: numList[3]
z1,z2 = lambda x,y: numList[4], lambda x,y: numList[5]
print(tplquad(func, x1, x2, y1, y2, z1, z2)[0])

