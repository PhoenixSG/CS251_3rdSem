import os
import sys

def convert (l):
    return [int(elem) for elem in l]

directory = str(sys.argv[2])[:-1]

countries = {}
for filename in os.listdir(directory):   
    filename = directory + '/' + filename 
    with open(filename) as file_in:
        for line in file_in:
            proc = line.split('-')
            c = proc[0]
            if c in countries:
                countries[c] = [a + b for a, b in zip(countries[c], convert(proc[1:]))]
            else:
                countries[c] = convert(proc[1:])
            

print({k: v for k, v in sorted(countries.items(), key = lambda item: (-item[1][0], item[0]))})


