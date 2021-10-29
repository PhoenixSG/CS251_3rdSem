import gdb

def printer(event):
    rsp = int(gdb.parse_and_eval("$rsp"))
    rbp = int(gdb.parse_and_eval("$rbp"))
    print("+-------------------------+")
    print(rsp)

    print(rbp)
    

gdb.events.stop.connect(printer)
