import re
import gdb

############################################################################################
# SKELETON FOR PRETTY PRINTING 2D MATRICES
# ITS OPTIONAL TO USE THIS SKELETON.
# Nothing much is given here anyways :p
############################################################################################
class Mat2DPrinter:
    def __init__(self, val):
        self.val = val

    def to_string(self):
        """
        Use the self.val to decode the 2D Matrix into
        the demanded format (check sample output in PS)
        """
        matrix = self.val
        final = str(matrix.type).split('[')
        rows, cols = (int(final[1][:-1]), int(final[2][:-1])) # get the rows and columns from val somehow
        str_list = list()

        str_list.append(f"ROWS: {rows}")
        str_list.append(f"COLUMNS: {cols}")

        for i in range(rows):
            temprow = "" # get the rows into a temporary string
            for j in range(cols):
                temprow += str(matrix[i][j]) + " " 
            str_list.append(temprow)
            
        return '\n' + '\n'.join(str_list) 
        

def array2d_printer(val):
    x = re.findall( "^int \[\d+\]\[\d+\]$" , str(val.type))
    if x != []:  # maybe use regex?
        return Mat2DPrinter(val)

gdb.pretty_printers.append(array2d_printer)
############################################################################################
