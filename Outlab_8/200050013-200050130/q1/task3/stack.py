import gdb

def printer(event):
    i=0
    answer=""
    while(True):
        a = gdb.execute("x/8bx $rsp+"+str(8*i), from_tty=True, to_string=True)
        addresses = a.split('0x')
        addresses = addresses[2:]
        answer+="+-------------------------+\n"
        answer+="| "
        for j in range(len(addresses)):
            answer+= addresses[j][:2]+" "
        rsp = int(gdb.parse_and_eval("$rsp+"+str(8*i)))
        rbp = int(gdb.parse_and_eval("$rbp"))
        if i==0:
            answer+="| <- rsp\n"
        elif rsp>=rbp:
            answer+="| <- rbp\n"
            break
        else:
            answer+="| \n"
            
        i+=1
    
    answer+="+-------------------------+"

    print(answer)
    

gdb.events.stop.connect(printer)
