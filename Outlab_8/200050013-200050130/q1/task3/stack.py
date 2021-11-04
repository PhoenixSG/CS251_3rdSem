import gdb
'''
You can iterate on the frames using a = gdb.newest_frame()
 then
a  = a.older() 
a.select()

till a is not None

When a.older() gives a None object, a should be the last frame.

Now within the last frame, you can do
val_of_rbp = gdb.execute(<command>, from_tty= True, to_string=True)
'''
def printer(event):
    i=0
    answer=""
    initial_rsp = int(gdb.parse_and_eval("$rsp"))
    rbp = int(gdb.parse_and_eval("$rbp"))
    while(True):
        a = str(gdb.execute("x/8bx $rsp+"+str(8*i), to_string=True, from_tty=True))
        addresses = a.split('0x')
        addresses = addresses[2:]
        answer+="+-------------------------+\n"
        answer+="| "
        for j in range(len(addresses)):
            answer+= addresses[j][:2]+" "
        rsp = int(gdb.parse_and_eval("$rsp+"+str(8*i)))
        print(rsp)
        if i==0:
            answer+="| <- rsp"
            if rsp>=rbp:
                answer+=" rbp\n"
            else:
                answer+="\n"
        elif rsp>=rbp:
            answer+="| <- rbp\n"
            i+=1
            break
        else:
            answer+="|\n"
            
        i+=1
    


    frame = gdb.newest_frame().older()
    while frame!=None :
        frame.select()
        print('x')
        frame = frame.older()

    print('a')
    new_rbp = int(gdb.parse_and_eval("$rbp"))

    print(i)
    while(True):
        a = str(gdb.execute("x/8bx $rsp+"+str(8*i), to_string=True, from_tty=True))
        rsp = int(gdb.parse_and_eval("$rsp+"+str(8*i)))
        if rsp>=new_rbp:
            break
        addresses = a.split('0x')
        addresses = addresses[2:]
        answer+="+-------------------------+\n"
        answer+="| "
        for j in range(len(addresses)):
            answer+= addresses[j][:2]+" "
        print(rsp)
        answer+="|\n"
        i+=1


    print('b')
    print(initial_rsp)
    print(rbp)
    print(new_rbp)
    answer+="+-------------------------+"
    print(answer)

gdb.events.stop.connect(printer)
