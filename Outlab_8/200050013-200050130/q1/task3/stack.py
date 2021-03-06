import gdb

base_rbp = -1

def printer(event):

    rsp = int(gdb.parse_and_eval("$rsp"))
    rbp = int(gdb.parse_and_eval("$rbp"))
    global base_rbp
    # print(rsp)
    # print(rbp)
    # print(base_rbp)
    # print("")
    answer=""
    if base_rbp==-1:
        if rbp>=rsp:
            base_rbp = rbp
    else:
        i=0
        while(True):
            a = str(gdb.execute("x/8bx $rsp+"+str(8*i), to_string=True))
            addresses = a.split('0x')
            addresses = addresses[2:]
            answer+="+-------------------------+\n"
            answer+="| "
            for j in range(len(addresses)):
                answer+= addresses[j][:2]+" "
            rsp = int(gdb.parse_and_eval("$rsp+"+str(8*i)))
            # print(rsp)
            # print(rbp)
            # print(base_rbp)
            if i==0:
                answer+="| <- rsp"
                if rsp+8>=rbp:
                    answer+=" rbp\n"
                    if rsp>=base_rbp:
                        break
                else:
                    answer+="\n"
            elif rsp>=rbp and rsp-8<rbp:
                answer+="| <- rbp\n"
                if rsp>=base_rbp:
                    break
            else:
                answer+="|\n"
                if rsp>=base_rbp:
                    break
            i+=1
    
        answer+="+-------------------------+"
    print(answer)

gdb.events.stop.connect(printer)
